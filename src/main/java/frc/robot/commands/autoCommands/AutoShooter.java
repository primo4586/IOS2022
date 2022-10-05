// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class AutoShooter extends CommandBase {
  private Feeder feeder;
  private Intake intake;
  private Shooter shooter;
  /** Creates a new AutoShooter. */
  public AutoShooter(Feeder feeder, Intake intake, Shooter shooter) {
    this.feeder = feeder;
    this.intake = intake;
    this.shooter = shooter;

    addRequirements(this.feeder);
    addRequirements(this.intake);
    addRequirements(this.shooter);
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    shooter.setSpeed(0.5); 
    feeder.setFeederVolt(0.5); 
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    if(shooter.getShooterRPM() == 3190){
      feeder.changeFeederSolenoidState(); // solenoid is open ???
    }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setSpeed(0);
    feeder.setFeederVolt(0);
    feeder.changeFeederSolenoidState();
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
