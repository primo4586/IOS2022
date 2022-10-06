// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Driver;

public class DriveByTime extends CommandBase {
  private Driver driver;
  private double time;
  private double speedVoltage;
  private Timer timer = new Timer();


  /** Creates a new DriveByTime. */
  public DriveByTime(Driver driver, double speedVoltage, double time) {
    this.driver = driver;
    this.speedVoltage = speedVoltage;
    this.time = time;
    addRequirements(driver);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.timer.start();
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    driver.driveVoltage(this.speedVoltage, this.speedVoltage);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    driver.driveVoltage(0, 0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return timer.hasElapsed(time);
  }
}
