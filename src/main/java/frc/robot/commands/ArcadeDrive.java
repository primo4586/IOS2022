// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.Constants.DriverConstants;
import frc.robot.subsystems.Driver;

public class ArcadeDrive extends CommandBase {
  private Driver driver;
  private double speedAsDouble;
  private double rotationAsDouble;
  private DoubleSupplier speed;
  private DoubleSupplier rotation;
  private BooleanSupplier isBoosting;
  private BooleanSupplier isSlowing;

  /** Creates a new ArcadeDrive. */
  public ArcadeDrive(Driver driver, DoubleSupplier speed, DoubleSupplier rotation, BooleanSupplier isBoosting, BooleanSupplier isSlowing) {
    this.driver = driver;
    this.speed = speed;
    this.rotation = rotation;
    this.isBoosting = isBoosting;
    this.isSlowing = isSlowing;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(driver);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    this.speedAsDouble = this.speed.getAsDouble() * Constants.DriverConstants.SPEED_LIMITER;
    this.rotationAsDouble = this.rotation.getAsDouble() * Constants.DriverConstants.ROTATION_LIMITER;

    if(isBoosting.getAsBoolean())
        this.speedAsDouble *= DriverConstants.BOOST_MULTIPLIER;
    else if(isSlowing.getAsBoolean())
        this.speedAsDouble *= DriverConstants.SLOW_MULTIPLIER;    

    driver.d_control(this.speedAsDouble ,this.rotationAsDouble);

  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
