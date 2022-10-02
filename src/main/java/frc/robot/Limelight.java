// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
/** Add your docs here. */
public class Limelight {
    private NetworkTable table;
    private double xAxis;
    private double yAxis;
    private double area;
    private double isThereTarget;
  
    public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    }
    
    public void tableUpdate(){
      NetworkTableEntry tv = table.getEntry("tv");
      isThereTarget = tv.getDouble(0.0);
      
      if(isThereTarget>0.1){
        NetworkTableEntry tx = table.getEntry("tx");
        NetworkTableEntry ty = table.getEntry("ty");
        NetworkTableEntry ta = table.getEntry("ta");

        xAxis = tx.getDouble(0.0);
        yAxis = ty.getDouble(0.0);
        area = ta.getDouble(0.0);
      }
    }
}
