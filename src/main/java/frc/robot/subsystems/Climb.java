package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.ClimbConstants;

public class Climb extends SubsystemBase {

  private WPI_TalonFX m_climbRight;
  private WPI_TalonFX m_climbleft;
  
  private boolean enabled = false;

  public Climb() {
    this.m_climbRight = new WPI_TalonFX(ClimbConstants.RIGHT_MOTOR_PORT);
    this.m_climbleft = new WPI_TalonFX(ClimbConstants.LEFT_MOTOR_PORT);
    this.m_climbleft.setInverted(true);

  }

  public void setVoltage(double voltage){
    m_climbRight.setVoltage(voltage);
    m_climbleft.setVoltage(voltage);
  }

  public boolean isEnabled() {
      return enabled;
  }

}
