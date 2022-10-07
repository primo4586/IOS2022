// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;

public class ManualFeeder extends CommandBase {
  /** Creates a new ManualFeeder. */
  private Feeder feeder;
  private double feederVoltage;
  public ManualFeeder(Feeder feeder, double feederVoltage) {
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(feeder);
    this.feeder = feeder;
    this.feederVoltage = feederVoltage;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.setFeederVolt(feederVoltage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.setFeederSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
