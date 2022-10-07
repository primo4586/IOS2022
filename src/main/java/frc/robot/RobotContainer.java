// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.ManualClawA;
import frc.robot.commands.ManualClawB;
import frc.robot.commands.AccelerateShooter;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ManualFeeder;
import frc.robot.commands.ManualRotateChain;
import frc.robot.commands.TuneShooter;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;
import frc.robot.subsystems.Driver;

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

  private Joystick driverJoystick;
  private Joystick o_joystick;
  
  // Opeartor Buttons:
  private JoystickButton START_Operator; // Enable/Disable Climb Control
  private JoystickButton B_Operator; // Release level 2
  private JoystickButton X_Operator; // Release level 3
  private JoystickButton RB_Operator; // Manual Control A side (2&4)
  private JoystickButton LB_Operator; // Manual Control B side (3)

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Climb climb, Feeder feeder, Shooter shooter, Driver driver) {
    this.climb = climb;
    this.shooter = shooter;
    this.feeder = feeder;
    this.driver = driver;

	  this.driverJoystick = new Joystick(0);
	  this.o_joystick = new Joystick(1);

    buildDefaultCommands();

    // Configure the button bindings
    buildButtons();
    configureButtonBindings();
  }

  private void buildButtons(){
    this.START_Operator = new JoystickButton(o_joystick, XboxController.Button.kStart.value);
    this.B_Operator = new JoystickButton(o_joystick, XboxController.Button.kB.value);
    this.X_Operator = new JoystickButton(o_joystick, XboxController.Button.kX.value);
    this.RB_Operator = new JoystickButton(o_joystick, XboxController.Button.kRightBumper.value);
    this.LB_Operator = new JoystickButton(o_joystick, XboxController.Button.kLeftBumper.value);
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    JoystickButton bButton = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    bButton.whenHeld(new AccelerateShooter(shooter , 0.5));
    // bButton.whenHeld(new TuneShooter(shooter, feeder));

    JoystickButton yButton = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    yButton.whenHeld(new ManualFeeder(feeder, 0.5));

  	START_Operator.whenPressed(new InstantCommand(() -> climb.setEnabled(), climb));
    RB_Operator.whenPressed(new ManualClawA(climb));
    LB_Operator.whenPressed(new ManualClawB(climb));
    B_Operator.whenPressed(new ManualClawA(climb));
    X_Operator.whenPressed(new ManualClawB(climb));
   
   }

  public void buildDefaultCommands() {
    // Default commands stay active all the time, if no other command is running.
    
    driver.setDefaultCommand(new ArcadeDrive(driver, () -> driverJoystick.getRawAxis(XboxController.Axis.kLeftY.value), () -> driverJoystick.getRawAxis(XboxController.Axis.kRightX.value)));
    climb.setDefaultCommand(new ManualRotateChain(climb, () -> o_joystick.getRawAxis(XboxController.Axis.kRightY.value)));
  }


}
