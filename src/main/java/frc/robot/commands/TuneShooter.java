// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Limelight;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;
import vision.InterpolateUtil;

public class TuneShooter extends CommandBase {
  /** Creates a new TuneShooter. */

  private Shooter shooter;
  private Feeder feeder;
  private ShuffleboardTab tab;
  private NetworkTableEntry speedEntry, setpoint;
  private Limelight limelight;

  public TuneShooter(Shooter shooter, Feeder feeder, Limelight limelight) {

    tab = Shuffleboard.getTab("ShooterTune");
    speedEntry = tab.add("Speed", 0).getEntry();
    setpoint = tab.add("setpoint", 0).getEntry();
    this.shooter = shooter;
    this.limelight = limelight;
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

    double speed = InterpolateUtil.interpolate(ShooterConstants.SHOOTER_VISION_MAP, limelight.getDistance());

    feeder.setFeederVolt(5);
    shooter.setSpeedVelocity(speed);
    setpoint.setNumber(speed);
    speedEntry.setNumber(shooter.getShooterRPM());
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
