// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;

/** Add your docs here. */
public class OneBallAuto extends SequentialCommandGroup {

    public OneBallAuto(Driver driver, Shooter shooter, Feeder feeder, Intake intake){
        AutoShooter shootBall = new AutoShooter(feeder, intake, shooter, () -> ShooterConstants.FALLBACK_RPM);
        DriveByTime driveBack = new DriveByTime(driver, -4, 3); // add time later
        addCommands(shootBall.withTimeout(7), driveBack);
    }
}
