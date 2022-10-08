// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.StartEndCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ManualClawA;
import frc.robot.commands.ManualClawB;
import frc.robot.Constants.FeederConstants;
import frc.robot.Constants.IntakeConstants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.commands.AccelerateShooter;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ManualFeeder;
import frc.robot.commands.ManualRoller;
import frc.robot.commands.ManualRotateChain;
import frc.robot.commands.ManualSolenoid;
import frc.robot.commands.RumbleJoystick;
import frc.robot.commands.SpinOutBalls;
import frc.robot.commands.TuneShooter;
import frc.robot.commands.autoCommands.AutoShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import vision.InterpolateUtil;
import frc.robot.subsystems.Driver;
import edu.wpi.first.cscore.UsbCamera;
import frc.robot.util.CameraHandler;
import edu.wpi.first.cameraserver.CameraServer;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  // subsystems setup
  private Climb climb;
  private Shooter shooter;
  private Feeder feeder;
  private Driver driver;
  private Intake intake;

  private Limelight limelight;

  private Joystick driverJoystick;
  private Joystick operatorJoystick;
  
  private JoystickButton B_Driver; // Auto-shooting
  private JoystickButton Y_Driver; // Reverse roller 
  private JoystickButton RB_Driver; // Change drivetrain direction & change cameras
  private JoystickButton LB_Driver; // Open Intake Joint & Spin roller
  private JoystickButton X_Driver; // Auto-shooting only within range
  private JoystickButton A_Driver; // open piston and spin roller
  private Trigger LT_TRIGGER_Driver;

  // Opeartor Buttons:
  private JoystickButton START_Operator; // Enable/Disable Climb Control
  private JoystickButton B_Operator; // Release level 2
  private JoystickButton X_Operator; // Release level 3
  private JoystickButton RB_Operator; // Manual Control A side (2&4)
  private JoystickButton LB_Operator; // Manual Control B side (3)
  private JoystickButton Y_Operator; // Manual Shooter

  private UsbCamera forward;
  private UsbCamera backward;
  private CameraHandler camHandler;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Climb climb, Feeder feeder, Shooter shooter, Driver driver, Intake intake, Limelight limelight) {
    this.climb = climb;
    this.shooter = shooter;
    this.feeder = feeder;
    this.intake = intake;
    this.limelight = limelight;
    this.driver = driver;

	  this.driverJoystick = new Joystick(0);
	  this.operatorJoystick = new Joystick(1);

    buildDefaultCommands();

    // Configure the button bindings
    buildButtons();
    configureButtonBindings();

    buildCameras();
  }

  private void buildButtons(){
    this.LB_Driver = new JoystickButton(driverJoystick, XboxController.Button.kLeftBumper.value);
    this.Y_Driver = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    this.RB_Driver = new JoystickButton(driverJoystick, XboxController.Button.kRightBumper.value);
    this.LB_Driver = new JoystickButton(driverJoystick, XboxController.Button.kLeftBumper.value);
    this.X_Driver = new JoystickButton(driverJoystick, XboxController.Button.kX.value);
    this.A_Driver = new JoystickButton(driverJoystick, XboxController.Button.kA.value);
    this.B_Driver = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    this.LT_TRIGGER_Driver = new Trigger(() -> driverJoystick.getRawAxis(XboxController.Axis.kLeftTrigger.value) > 0.3);


    this.START_Operator = new JoystickButton(operatorJoystick, XboxController.Button.kStart.value);
    this.B_Operator = new JoystickButton(operatorJoystick, XboxController.Button.kB.value);
    this.X_Operator = new JoystickButton(operatorJoystick, XboxController.Button.kX.value);
    this.RB_Operator = new JoystickButton(operatorJoystick, XboxController.Button.kRightBumper.value);
    this.LB_Operator = new JoystickButton(operatorJoystick, XboxController.Button.kLeftBumper.value);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    RumbleJoystick rumble = new RumbleJoystick(driverJoystick, () -> limelight.getDistance() * 0.1);

    this.RB_Driver.whenPressed(new InstantCommand(() -> {
      driver.setForward(!driver.isForward());
      camHandler.switchCamera();   
    }, driver));

    // Shooter Control
    // Manual Control with static setpoint
    LT_TRIGGER_Driver.whileActiveOnce(new AutoShooter(feeder, intake, shooter, () -> ShooterConstants.FALLBACK_RPM));

    // Using vision data for shooting
    B_Driver.whenHeld(
              new SequentialCommandGroup(rumble.until(() -> limelight.getIsThereTarget() && limelight.isDistanceInRange()), 
              new AutoShooter(feeder, intake, shooter, () -> InterpolateUtil.interpolate(ShooterConstants.SHOOTER_VISION_MAP, limelight.getDistance()))));

    // Manual Feeder Control
    X_Driver.whenHeld(new SpinOutBalls(feeder, -FeederConstants.FEEDER_VOLTAGE));
    // Intake control
    Y_Driver.whileHeld(new ManualRoller(intake, -IntakeConstants.ROLLER_VOLTAGE)); // outaking "plita" (spinning roller in reverse)
    LB_Driver.whenPressed(new ManualSolenoid(intake)); // Toggles open/closes the intake, also toggles the roller 

    // Climb controls
  	START_Operator.whenPressed(new InstantCommand(() -> climb.setEnabled(), climb));
    RB_Operator.whenPressed(new ManualClawA(climb));
    LB_Operator.whenPressed(new ManualClawB(climb));
    B_Operator.whenPressed(new ManualClawA(climb));
    X_Operator.whenPressed(new ManualClawB(climb));
   
   }

  public void buildDefaultCommands() {
    // Default commands stay active all the time, if no other command is running.
    intake.setDefaultCommand(new ManualRoller(intake, IntakeConstants.ROLLER_VOLTAGE));
    // shooter.setDefaultCommand(new AccelerateShooter(shooter, ShooterConstants.DEFAULT_CONSTANT_SPEED));
    driver.setDefaultCommand(new ArcadeDrive(driver,
         () -> driverJoystick.getRawAxis(XboxController.Axis.kLeftY.value),
         () -> driverJoystick.getRawAxis(XboxController.Axis.kRightX.value),
         () -> driverJoystick.getRawAxis(XboxController.Axis.kRightTrigger.value) > 0.3,
         () -> false));
    climb.setDefaultCommand(new ManualRotateChain(climb, () -> operatorJoystick.getRawAxis(XboxController.Axis.kRightY.value)));
  }

  private void buildCameras() {
    this.forward = CameraServer.startAutomaticCapture("Forward", 0);
    this.backward = CameraServer.startAutomaticCapture("Backward", 1);

    this.camHandler = new CameraHandler(forward, backward);
  }
}

