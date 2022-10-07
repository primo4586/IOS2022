// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.PneumaticConstants;

public class Intake extends SubsystemBase {


  private WPI_TalonSRX intakeMotor;
  private Solenoid intakeJoint;



  /** Creates a new Intake. */
  public Intake() {

    this.intakeMotor = new WPI_TalonSRX(Constants.IntakeConstants.INTAKE_MOTOR);
    this.intakeJoint = new Solenoid(PneumaticConstants.PCM_PORT, PneumaticsModuleType.CTREPCM, Constants.IntakeConstants.INTAKE_SOLENOID);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public boolean getSolanoidState(){
    return intakeJoint.get();
  }

// changes solenoid state from F -> T or T->F
  public void toggleSolenoidState (){
    
     intakeJoint.set(!intakeJoint.get());

  }

  public void setSolenoidState (boolean state){
    
    intakeJoint.set(state);

  }

  // motor speed control

  public void setIntakeSpeed(double speed){
    this.intakeMotor.set(speed);
  }




}
