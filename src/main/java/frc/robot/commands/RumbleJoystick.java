// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class RumbleJoystick extends CommandBase {
  /** Creates a new RumbleJoystick. */
  private Joystick joystick;
  private DoubleSupplier rumble;
  public RumbleJoystick(Joystick joystick, DoubleSupplier rumble) {
    // Use addRequirements() here to declare subsystem dependencies.
    this.joystick=joystick;
    this.rumble=rumble;
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.joystick.setRumble(RumbleType.kLeftRumble, rumble.getAsDouble());
    this.joystick.setRumble(RumbleType.kRightRumble, rumble.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    this.joystick.setRumble(RumbleType.kLeftRumble, 0);
    this.joystick.setRumble(RumbleType.kRightRumble, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return true;
  }
}
