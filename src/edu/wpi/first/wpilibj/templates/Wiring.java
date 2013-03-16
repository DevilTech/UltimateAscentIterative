
package edu.wpi.first.wpilibj.templates;

public class Wiring
{
    // Climb buttons / GusBus Inputs
    static public final int CLIMB_UP_PART               = 13;
    static public final int CLIMB_UP_MAX                = 11;
    static public final int CLIMB_DOWN                  = 15;
    static public final int AUTO_CLIMB_FIRST            = 12;
    static public final int AUTO_CLIMB                  = 16;
    static public final int FORWARD_BACK                = 9; // FORWARD IS OFF, BACK IS ON
    static public final int CLIMB_ON                    = 7;
    
    // Joystick
    static public final int TRIGGER                     = 1;
    static public final int SHOOTER_MOTOR_ON            = 3;
    static public final int SHOOTER_MOTOR_OFF           = 2;
    
    // CANJaguars
    static public final int WINCH_MOTOR                 = 8; //ATTENTION for testing, change to 5 for real robot
    static public final int LEFT_WHEEL                  = 7;
    static public final int RIGHT_WHEEL                 = 6;
    static public final int SHOOTER_MOTOR               = 4;
    
    // Victors
    static public final int HOPPER_MOTOR                = 10; //ATTENTION for testing, change to 5 for robot
 
    // solenoid
    static public final int CLIMB_SOLENOID_UP           = 2;
    static public final int CLIMB_SOLENOID_DOWN         = 4;
    static public final int CLIMBING_SOLENOID_FORWARD   = 3;
    static public final int CLIMBING_SOLENOID_BACKWARD  = 1;

    // Analog Digital Sidecar
    static public final int GYRO_ANALOG                 = 2;
    
    //Digital I/O Digital Sidecar
    static public final int HOPPER_MAGNET               = 2;
    static public final int CYLINDER_HOME               = 4;
    static public final int CYLINDER_PART               = 5;
    static public final int CYLINDER_MAX                = 6;
    
    // Joysticks
    static public final int WHEEL                       = 2;
    static public final int THROTTLE                    = 1;
    static public final int COPILOT                     = 3;
    
    // MISC
    static public final double TURN_TOLERANCE           = 0.75;
    static public final double TURN_DELAY               = 1.0/16.0;
    static public final double P_SPEED                  = 0.76;
    static public final double I_SPEED                  = 0.046;
    static public final double D_SPEED                  = 0.0;
    static public final double P                        = 0.8;
    static public final double I                        = 0.02;
    static public final double D                        = 0.4;
    static public final int TICKSPERREV                 = 360;
    static public final int WHEELSPROCKET               = 42;
    static public final int DRIVESPROCKET               = 38;
    static public final int MAXJAGVOLTAGE               = 12;
    static public final double RAMP_VOLTS_PER_SECOND    = 3.0;
    
    // wheel buttons
    static public final int WHEEL_X_BUTTON              = 1;
    static public final int SQUARE_BUTTON               = 2;
    static public final int CIRCLE_BUTTON               = 3;
    static public final int TRIANGLE_BUTTON             = 4;
    static public final int R1_BUTTON                   = 5;
    static public final int L1_BUTTON                   = 6;
    static public final int R2_BUTTON                   = 7;
    static public final int L2_BUTTON                   = 8;
    static public final int SELECT_BUTTON               = 9;
    static public final int START_BUTTON                = 10;
    static public final int R3_BUTTON                   = 11;
    static public final int L3_BUTTON                   = 12;
    
    // XBox
    static public final int XBOX_A_BUTTON               = 1;
    static public final int XBOX_B_BUTTON               = 2;
    static public final int XBOX_X_BUTTON               = 3;
    static public final int XBOX_Y_BUTTON               = 4;
    static public final int XBOX_LEFT_BUMPER            = 5;
    static public final int XBOX_RIGHT_BUMPER           = 6;
}