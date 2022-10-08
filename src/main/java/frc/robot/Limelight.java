// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;
import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Constants.LimelightConstants;
/** Add your docs here. */
public class Limelight {
    private NetworkTable table;
    private double xAxis;
    private double yAxis;
    private double area;
    private double isThereTarget;
    private double distance;
  
    private ShuffleboardTab debugTab;
    private NetworkTableEntry distanceEntry, angleXEntry, angleYEntry;

    public Limelight() {
    table = NetworkTableInstance.getDefault().getTable("limelight");
    debugTab = Shuffleboard.getTab("Vision debug");
    distanceEntry = debugTab.add("Distance", 0).getEntry();
    angleXEntry = debugTab.add("Angle X", 0).getEntry();
    angleYEntry = debugTab.add("Angle Y", 0).getEntry();

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

        Double angleToGoal=(yAxis+Constants.LimelightConstants.LIMELIGHT_Y_ANGLE)*(Math.PI / 180.0);
        distance=(Constants.LimelightConstants.TARGET_HEIGHT - Constants.LimelightConstants.LIMELIGHT_HEIGHT)/Math.tan(angleToGoal);
        distanceEntry.setNumber(distance);
        angleXEntry.setNumber(xAxis);
        angleYEntry.setNumber(yAxis);
      }

    }

    public double getDistance() {
        return distance;
    }

     public boolean getIsThereTarget() {
      return (isThereTarget == 1);
    }

    public boolean isDistanceInRange(){
      return (distance < 2 && distance > 1.1);
    }

}
