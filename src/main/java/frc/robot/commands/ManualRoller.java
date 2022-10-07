// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
//  this command turn on motor on speed
import frc.robot.subsystems.Intake;

public class ManualRoller extends CommandBase {

  private Intake intake; 
  private double speed; 

  /** Creates a new ManualRoller. */
  public ManualRoller(Intake intake, double speed) {
    // Use addRequirements() here to declare subsystem dependencies.

    addRequirements(intake);
    this.speed=speed;
    this.intake = intake;

  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute()
  {
    if(intake.getSolanoidState())
      intake.setIntakeSpeed(speed);
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    intake.setIntakeSpeed(0);
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
