// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;

public class TuneShooter extends CommandBase {
  /** Creates a new TuneShooter. */

  private Shooter shooter;
  private Feeder feeder;
  private ShuffleboardTab tab;
  private NetworkTableEntry speed, setpoint;

  public TuneShooter(Shooter shooter, Feeder feeder) {

    tab = Shuffleboard.getTab("ShooterTune");
    speed = tab.add("Speed", 0).getEntry();
    setpoint = tab.add("setpoint", 0).getEntry();
    this.shooter = shooter;
    this.feeder = feeder;

    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    feeder.setFeederSolenoid(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {

    feeder.setFeederVolt(5);
    shooter.setSpeedVelocity(setpoint.getDouble(0));
    speed.setNumber(shooter.getShooterRPM());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    feeder.setFeederSolenoid(false);
    feeder.setFeederSpeed(0);
    shooter.setSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
