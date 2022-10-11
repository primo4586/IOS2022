// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import java.util.HashMap;
import java.util.Map;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import frc.robot.autonomous.CommandSelector;
import frc.robot.commands.autoCommands.OneBallAuto;
import frc.robot.commands.autoCommands.TwoBallAuto;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/** 
 * Similar, to RobotContainer, but includes everything that's relevant for our autonomous routines
 */
public class AutoContainer {
  private CommandSelector autoSelector;
  private Map<String, Command> autoPaths;


  public AutoContainer(Driver driver, Shooter shooter, Feeder feeder, Intake intake, Limelight limelight){
    this.autoPaths = new HashMap<String, Command>();



    OneBallAuto oneBallAuto = new OneBallAuto(driver, shooter, feeder, intake, limelight); // command group
    TwoBallAuto twoBallAuto = new TwoBallAuto(driver, shooter, feeder, intake, limelight);

    autoPaths.put("No Auto", new InstantCommand());
    autoPaths.put("One Ball Auto", oneBallAuto);
    autoPaths.put("Two Ball Auto", twoBallAuto);
    this.autoSelector = new CommandSelector(autoPaths, "Competiton Dashboard");
  }


  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return this.autoSelector.getCommand();
  }
  
}
