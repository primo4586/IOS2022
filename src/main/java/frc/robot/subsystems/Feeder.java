// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Feeder extends SubsystemBase {
  private WPI_TalonFX feederMotor;
  /** Creates a new Feeder. */
  public Feeder() {
    this.feederMotor = new WPI_TalonFX(Constants.FeederConstants.FEEDER_MOTOR_PORT);
    feederMotor.setNeutralMode(NeutralMode.Brake);
  }
    
  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  public void setSpeed(double speed){
    feederMotor.set(speed);
  }
}
