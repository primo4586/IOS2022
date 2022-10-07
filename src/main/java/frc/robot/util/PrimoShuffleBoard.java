// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.util;

import java.util.function.BooleanSupplier;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import frc.robot.Limelight;
import frc.robot.RobotContainer;
import frc.robot.subsystems.Climb;
import frc.robot.subsystems.Feeder;

/** Add your docs here. */
public class PrimoShuffleBoard {
    private ShuffleboardTab tab;
    private NetworkTableEntry feederSolenoidState;
    private NetworkTableEntry matchTime;
    private NetworkTableEntry matchTimeState;
    private NetworkTableEntry isTargetInLL;
    private NetworkTableEntry isInShootingRange;
    private NetworkTableEntry isClimbEnabled;

    private double timeSaved;
    private boolean timeStateSaved;

    public void primoShuffleBoard() {
        tab = Shuffleboard.getTab("Compatition shuffle board");
        feederSolenoidState = tab.add("Feeder Solenoid State", 0).getEntry();
        matchTime = tab.add("Time left",0).getEntry();
        matchTimeState = tab.add("Time left warning",0).getEntry();
        isTargetInLL = tab.add("Is the target in Limelight range",0).getEntry();
        isInShootingRange = tab.add("Is in shooting range",0).getEntry();
        isClimbEnabled = tab.add("Is climb enabled", 0).getEntry();
        
        timeSaved = 0;
        timeStateSaved = false;
    }

    public void updateCompShuffleBoard(Feeder feeder, Limelight limelight, Climb climb) {
        feederSolenoidState.forceSetBoolean(feeder.getFeederSolenoidState());
        matchTime.forceSetDouble(Timer.getMatchTime());
        matchTimeState.forceSetBoolean(this.getMatchTimeState());
        isTargetInLL.forceSetBoolean(limelight.getIsThereTarget());
        isInShootingRange.forceSetBoolean(limelight.isDistanceInRange());
        isClimbEnabled.forceSetBoolean(climb.isEnabled());
        
    }

    public boolean getMatchTimeState() {
        if (Timer.getMatchTime() < 30) {
            timeStateSaved = false;
        }
        else if (Timer.getMatchTime() < 40) {
            if (Timer.getFPGATimestamp() >= timeSaved + 0.5) {
                timeStateSaved = !timeStateSaved;
                timeSaved = Timer.getFPGATimestamp();
            }
        }
        else {
            timeStateSaved = true;
        }

        return timeStateSaved;
    }
}

