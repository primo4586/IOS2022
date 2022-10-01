// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Limelight extends SubsystemBase {
  /** Creates a new Limelight. */
  private NetworkTable table;
  private double xAxis;
  private double yAxis;
  private double area;
  private double isThereTarget;

  public Limelight() {
  table = NetworkTableInstance.getDefault().getTable("limelight");
  }

  public void tableUpdate(){
    NetworkTableEntry tx = table.getEntry("tx");
    NetworkTableEntry ty = table.getEntry("ty");
    NetworkTableEntry ta = table.getEntry("ta");
    NetworkTableEntry tv = table.getEntry("tv");

    xAxis = tx.getDouble(0.0);
    yAxis = ty.getDouble(0.0);
    area = ta.getDouble(0.0);
    isThereTarget = tv.getDouble(0.0);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
