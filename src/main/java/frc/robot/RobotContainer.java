// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.ManualRotateChain;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Driver;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // joystick setup
  private Joystick d_Controller;

  // subsystems setup
  private Climb climb;
  private Driver drive;

 
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Climb climb, Driver drive) {
    this.climb = climb;
    this.drive = drive;
    this.d_Controller = new Joystick(0);
    

    buildDefaultCommands();
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    // driveTrain controls
    drive.setDefaultCommand(new ArcadeDrive(drive, () -> d_Controller.getRawAxis(XboxController.Axis.kLeftY.value), () -> d_Controller.getRawAxis(XboxController.Axis.kRightX.value)));
  }

  public void buildDefaultCommands() {
    // Default commands stay active all the time, if no other command is running.
    // TODO: Add joystick input to the command.
    climb.setDefaultCommand(new ManualRotateChain(climb, () -> 0));
  }


}
