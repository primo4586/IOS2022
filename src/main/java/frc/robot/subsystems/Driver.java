// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Driver extends SubsystemBase {

    // driver train motors
    private WPI_TalonFX m_leftLeader;
    private WPI_TalonFX m_leftFollower;
    private WPI_TalonFX m_rightLeader;
    private WPI_TalonFX m_rightFollower;

    private boolean forward;

    // for motors to work together
    private DifferentialDrive diffDrive;

  /** Creates a new Driver. */
  public Driver() {
    // motors setup
    this.m_leftLeader = new WPI_TalonFX(Constants.DriverConstants.LEFT_LEADER_MOTOR_PORT);
    this.m_leftFollower = new WPI_TalonFX(Constants.DriverConstants.LEFT_FOLLOWER_MOTOR_PORT);
    this.m_rightLeader = new WPI_TalonFX(Constants.DriverConstants.RIGHT_LEADER_MOTOR_PORT);
    this.m_rightFollower = new WPI_TalonFX(Constants.DriverConstants.RIGHT_FOLLOWER_MOTOR_PORT);


    this.m_leftLeader.setNeutralMode(NeutralMode.Brake);
    this.m_rightLeader.setNeutralMode(NeutralMode.Brake);
    this.m_rightFollower.setNeutralMode(NeutralMode.Brake);
    this.m_leftFollower.setNeutralMode(NeutralMode.Brake);

    this.m_leftLeader.setInverted(true);
    this.m_leftFollower.setInverted(true);

    // followers setup
    // follow by percent
    this.m_rightFollower.follow(m_rightLeader, FollowerType.PercentOutput); 
    this.m_leftFollower.follow(m_leftLeader, FollowerType.PercentOutput);

    //creates new diffrentialDrive
    this.diffDrive = new DifferentialDrive(m_leftLeader, m_rightLeader);
    this.forward = true;
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
  
  // sets the speed value to right motors and the rotation to left motors
  // gets double speed, double rotation
  // how arcadeDrive works:
  // right motors: yAxessValue - xAxessValue
  // left motors: max(yAxessValue, xAxessValue)
  
  public void d_control(double speed, double rotation){

    if(!forward)
      speed *= -1;

    this.diffDrive.arcadeDrive(speed,-rotation);
  }

  public void driveLeft(double voltage) {
    this.m_leftLeader.setVoltage(voltage);
    this.m_leftFollower.setVoltage(voltage);
    this.diffDrive.feed();
  }

  public void driveRight(double voltage) {
    this.m_rightLeader.setVoltage(voltage);
    this.m_rightFollower.setVoltage(voltage);
    this.diffDrive.feed();
  }

  // sets motors by voltage
  public void driveVoltage(double rightVolatge, double leftVoltage){
    this.m_leftLeader.setVoltage(leftVoltage);
    this.m_rightLeader.setVoltage(rightVolatge);
  }

  public void setForward(boolean forward) {
    this.forward = forward;
  }

  public boolean isForward() {
      return forward;
  }

  
}
