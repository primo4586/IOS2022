// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.FollowerType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Driver extends SubsystemBase {

    // driver train motors
    private WPI_TalonFX m_leftLeader;
    private WPI_TalonFX m_leftFollower;
    private WPI_TalonFX m_rightLeader;
    private WPI_TalonFX m_rightFollower;

    // for motors to work together
    private DifferentialDrive diffDrive;

  /** Creates a new Driver. */
  public Driver() {
    // motors setup
    this.m_leftLeader = new WPI_TalonFX(Constants.DriverConstants.LEFT_LEADER_MOTOR_PORT);
    this.m_leftFollower = new WPI_TalonFX(Constants.DriverConstants.LEFT_FOLLOWER_MOTOR_PORT);
    this.m_rightLeader = new WPI_TalonFX(Constants.DriverConstants.RIGHT_LEADER_MOTOR_PORT);
    this.m_leftFollower = new WPI_TalonFX(Constants.DriverConstants.LEFT_FOLLOWER_MOTOR_PORT);

    // followers setup
    // follow by percent
    this.m_rightFollower.follow(m_rightLeader, FollowerType.PercentOutput); 
    this.m_leftFollower.follow(m_leftLeader, FollowerType.PercentOutput);

    //creates new diffrentialDrive
    this.diffDrive = new DifferentialDrive(m_leftLeader, m_rightLeader);
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
    this.diffDrive.arcadeDrive(speed, -rotation);//give it speeeeeeeeed
  }
}
