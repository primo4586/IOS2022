// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants. This class should not be used for any other purpose. All constants should be declared
 * globally (i.e. public static). Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {

    public static final class ClimbConstants {
        public static final int RIGHT_MOTOR_PORT = 10;
        public static final int LEFT_MOTOR_PORT = 9;
        
        public static final double CHAIN_VOLTAGE = 6.5;

        public static final int CLIMB_SOLENOID_A = 6;
        public static final int CLIMB_SOLENOID_B = 7;
    }

    public static final class LimelightConstants{
        public static final double LIMELIGHT_Y_ANGLE = 40;
        
        public static final double LIMELIGHT_HEIGHT = 0.70;
        public static final double TARGET_HEIGHT = 2.59;
    }

    public static final class ShooterConstants {
        public static final int SHOOTER_MOTOR_PORT = 15;
    }
    
    public static final class FeederConstants {
        public static final int FEEDER_MOTOR_PORT = 6;
        public static final int FEEDER_PORT = 0; 
    }
    
    public static final class IntakeConstants{
        public static final int INTAKE_MOTOR =8;
        public static final int INTAKE_SOLENOID = 5;
    }

    public static final class PneumaticConstants {
        public static final int PCM_PORT = 8;
    }
    
     public static final class DriverConstants{
        // motors
        public static final int LEFT_LEADER_MOTOR_PORT = 2;
        public static final int RIGHT_LEADER_MOTOR_PORT = 0;
        public static final int LEFT_FOLLOWER_MOTOR_PORT = 1;
        public static final int RIGHT_FOLLOWER_MOTOR_PORT = 3;

        // joysticks
        public static final int D_CONTROLLER_PORT = 0;
        
        // arcadeDrive limiters
        public static final double SPEED_LIMITER = 0.6;
        public static final double ROTATION_LIMITER = 0.6;

    }
}
