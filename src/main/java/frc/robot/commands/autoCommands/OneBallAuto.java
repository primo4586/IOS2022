// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Limelight;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import vision.InterpolateUtil;

/** Add your docs here. */
public class OneBallAuto extends SequentialCommandGroup {

    public OneBallAuto(Driver driver, Shooter shooter, Feeder feeder, Intake intake, Limelight limelight){
        AutoShooter shootBall = new AutoShooter(feeder, intake, shooter, () -> InterpolateUtil.interpolate(ShooterConstants.SHOOTER_VISION_MAP, limelight.getDistance()));
        DriveByTime driveBack = new DriveByTime(driver, -2, 7); // add time later
        addCommands(shootBall.withTimeout(4), driveBack);
    }
}
