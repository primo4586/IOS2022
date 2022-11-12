// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands.autoCommands;

import edu.wpi.first.math.interpolation.Interpolatable;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.Limelight;
import frc.robot.Constants.ShooterConstants;
import frc.robot.subsystems.Driver;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Shooter;
import vision.InterpolateUtil;

/** Add your docs here. */
public class ThreeBallAuto extends SequentialCommandGroup {

    public ThreeBallAuto(Driver driver, Shooter shooter, Feeder feeder, Intake intake, Limelight limelight){
        AutoShooter shootBall = new AutoShooter(feeder, intake, shooter, () -> InterpolateUtil.interpolate(ShooterConstants.SHOOTER_VISION_MAP, limelight.getDistance()));
        ParallelRaceGroup takeBall = new ParallelRaceGroup(new OpenAndRotate(intake), new DriveByTime(driver, -2, 5)).withTimeout(5); // add time later
        SequentialCommandGroup shootAgain = new SequentialCommandGroup(new DriveByTime(driver, 2, 5), new AutoShooter(feeder, intake, shooter, () -> InterpolateUtil.interpolate(ShooterConstants.SHOOTER_VISION_MAP, limelight.getDistance())).withTimeout(3)); // add time later
        addCommands(shootBall.withTimeout(4.5), takeBall, shootAgain);
}
}
