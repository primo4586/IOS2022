// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

public class AutoShooter extends CommandBase {
  private Feeder feeder;
  private Intake intake;
  private Shooter shooter;
  private DoubleSupplier setpointSupplier;
  private double inRangeTime = 0;

  private ShuffleboardTab tab;
  private NetworkTableEntry speed, setpoint, reachedSetpoint;
  /** Creates a new AutoShooter. */
  public AutoShooter(Feeder feeder, Intake intake, Shooter shooter, DoubleSupplier setpointSupplier) {
    this.feeder = feeder;
    this.intake = intake;
    this.shooter = shooter;
    this.setpointSupplier = setpointSupplier;


    addRequirements(this.feeder);
    addRequirements(this.intake);
    addRequirements(this.shooter);
    
    // Use addRequirements() here to declare subsystem dependencies.
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    this.inRangeTime = 100000000;
    shooter.setSpeed(0.5); 
    feeder.setFeederVolt(6);
    intake.setIntakeSpeed(0.3);

  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    feeder.setFeederVolt(5); 
    shooter.setSpeedVelocity(setpointSupplier.getAsDouble());
    
    System.out.println("Shooter Reported RPM: " + shooter.getShooterRPM());
    System.out.println("Setpoint RPM: " + setpointSupplier.getAsDouble());
    System.out.println("Difference: " + (setpointSupplier.getAsDouble() - shooter.getShooterRPM()));
    System.out.println("-----------------------------------");
    if(shooter.isRPMinRange(setpointSupplier.getAsDouble()) && inRangeTime == 100000000){
      System.out.println("Reached setpoint");
      feeder.setFeederSolenoid(true);  
      // inRangeTime = Timer.getFPGATimestamp();
    }
    // if(Timer.getFPGATimestamp() >= inRangeTime + 1 && inRangeTime != 100000000) {
    //   System.out.println("Feeder open");
           
    // }
    
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    shooter.setSpeed(0);
    feeder.setFeederVolt(0);
    feeder.setFeederSolenoid(false);
    intake.setIntakeSpeed(0);

  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
