// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.FeederConstants;
import frc.robot.Constants.PneumaticConstants;


public class Feeder extends SubsystemBase {
  private WPI_TalonFX feederMotor;
  private Solenoid feederSol;
  private boolean solState;
  /** Creates a new Feeder. */
  public Feeder() {
    this.feederMotor = new WPI_TalonFX(FeederConstants.FEEDER_MOTOR_PORT);
    this.feederSol =  new Solenoid(PneumaticConstants.PCM_PORT,  PneumaticsModuleType.CTREPCM, FeederConstants.FEEDER_PORT);
    this.solState = true; 
  }
    
  @Override
  public void periodic() {

    // This method will be called once per scheduler run
  }

  public void setFeederSpeed(double speed){
    feederMotor.set(speed);
  }

  public void setFeederVolt(double volt){
    feederMotor.setVoltage(volt);
  }

  

  public void changeFeederSolenoidState(){
      this.solState = !this.solState;
      this.feederSol.set(solState);
  }

  public boolean getFeederSolenoidState(){
    return this.solState;
  }

  //Wasn't sure if neccessary, but it wouldn't hurt.
  public void setFeederSolenoid(boolean state){
    this.solState = state;
    this.feederSol.set(state);
  }


}
