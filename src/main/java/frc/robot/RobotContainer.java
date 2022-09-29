// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.AccelerateShooter;
import frc.robot.commands.ManualFeeder;
import frc.robot.commands.ManualRotateChain;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

  private Climb climb;
  private Shooter shooter;
  private Feeder feeder;

  private Joystick driverJoystick;

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer(Climb climb, Feeder feeder, Shooter shooter) {
    this.climb = climb;
    this.shooter = shooter;
    this.feeder = feeder;

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
    this.driverJoystick = new Joystick(0);
    JoystickButton bButton = new JoystickButton(driverJoystick, XboxController.Button.kB.value);
    bButton.whenHeld(new AccelerateShooter(shooter , 0.5));

    JoystickButton yButton = new JoystickButton(driverJoystick, XboxController.Button.kY.value);
    yButton.whenHeld(new ManualFeeder(feeder, 0.5) );
  }

  public void buildDefaultCommands() {
    // Default commands stay active all the time, if no other command is running.
    // TODO: Add joystick input to the command.
    climb.setDefaultCommand(new ManualRotateChain(climb, () -> 0));
  }


}
