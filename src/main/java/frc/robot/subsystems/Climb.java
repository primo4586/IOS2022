package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.Constants.ClimbConstants;

public class Climb extends SubsystemBase {

  private Solenoid solenoidA;
  private Solenoid solenoidB; 

  private boolean aSideState; 
  private boolean bSideState;

  private WPI_TalonFX m_climbRight;
  private WPI_TalonFX m_climbleft;
  
  private boolean enabled = false;

  public Climb() {
    //
    this.m_climbRight = new WPI_TalonFX(ClimbConstants.RIGHT_MOTOR_PORT);
    this.m_climbleft = new WPI_TalonFX(ClimbConstants.LEFT_MOTOR_PORT);
    this.m_climbleft.setInverted(true);

    this.solenoidA = new Solenoid(Constants.ClimbConstants.pcmPort, PneumaticsModuleType.CTREPCM,
    Constants.ClimbConstants.climbSolenoidA);
        
    this.solenoidB = new Solenoid(Constants.ClimbConstants.pcmPort, PneumaticsModuleType.CTREPCM,
    Constants.ClimbConstants.climbSolenoidB);

    this.enabled = false;
    this.aSideState = true;
    this.bSideState = true;
  }

  public void changePClimbA(boolean state){
    this.aSideState= state;
    this.solenoidA.set(state);
  }

  public void changePClimbB(boolean state){
    this.bSideState= state;
    this.solenoidB.set(state);
  }

  public boolean getStateA(){
    return this.aSideState;
  }

  public boolean getStateB(){
    return this.bSideState;
  }

  public void setVoltage(double voltage){
    m_climbRight.setVoltage(voltage);
    m_climbleft.setVoltage(voltage);
  }

  public boolean isEnabled() {
      return enabled;
  }

  public void setEnabled( ) {
    this.enabled=true;
  }

}
