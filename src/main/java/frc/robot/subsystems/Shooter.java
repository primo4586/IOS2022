// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.math.controller.SimpleMotorFeedforward;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ShooterConstants;
import frc.robot.util.PIDConfig;

public class Shooter extends SubsystemBase {
  private WPI_TalonFX shooterMotor;

  /** Creates a new Shooter. */
  public Shooter() {
    this.shooterMotor = new WPI_TalonFX(Constants.ShooterConstants.SHOOTER_MOTOR_PORT);
    this.shooterMotor.setInverted(true);
    setConfig(ShooterConstants.SHOOTER_CONFIG);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed) {
    shooterMotor.set(speed);
  }

  public void setSpeedVelocity(double speedVelocity) {
    this.shooterMotor.set(ControlMode.Velocity, (speedVelocity / 600) * 2048);
  }

  public void setConfig(PIDConfig config) {
    this.shooterMotor.config_kP(0, config.getKp());
    this.shooterMotor.config_kI(0, config.getKi());
    this.shooterMotor.config_kD(0, config.getKd());
    this.shooterMotor.config_kF(0, config.getKf());
  }

  public boolean isRPMinRange(double targetRPM) {
    return Math.abs(targetRPM - getShooterRPM()) <= ShooterConstants.READY_RPM_TOLERANCE;
  }



  public double getShooterRPM() {
    return ((shooterMotor.getSelectedSensorVelocity() * 10 * 60) / 2048);
  }

}
