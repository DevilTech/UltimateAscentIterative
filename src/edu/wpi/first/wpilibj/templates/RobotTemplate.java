/*----------------------------------------------------------------------------*/
/* Copyright (c) FIRST 2008. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

//CHANGE NUMBERS SO THAT THE PORTS ARE CORRECT
package edu.wpi.first.wpilibj.templates;


import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.Gyro;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class RobotTemplate extends IterativeRobot 
{ 
    //Drive System
    DriveThread dthread;
    Victor leftMotor; //ATTENTION turn these victors back into jaguars
    Victor rightMotor;
    RobotDrive drive;
    Joystick stick;
    Joystick wheel;
    Joystick copilot;
    
    //Shooter
    JoystickButton shootOn;
    JoystickButton shootOff;
    
    JoystickButton fire;
    Shooter shooter;
    Hopper hopper;
    boolean shooting = false;
    
    //Climbing
    JoystickButton upPart;
    JoystickButton upMax;
    JoystickButton down;
    JoystickButton autoClimb;
    DriverStationEnhancedIO driverStationButtons = DriverStation.getInstance().getEnhancedIO();
    Compressor comp; 
    ClimbingSystem climb;
    
    //Autonomous Crap
    Gyro gyro;
        
    PIDController pid;
    Output out;
    JoystickButton test;
    
    //State variables
    int hopperState = 0;
    int hopperTimeout = 0;

    
    ErrorHandler errHandler = ErrorHandler.getErrorHandler();
    
    
    public void robotInit() 
    {
//        try 
//        {
            //Joystick Constructors
            wheel       = new Joystick(Wiring.WHEEL);
            stick       = new Joystick(Wiring.THROTTLE);
            copilot     = new Joystick(Wiring.COPILOT);
            
//            Drive Constructors
            leftMotor   = new Victor(6);
            rightMotor  = new Victor(5);// ATTENTION switch back to wiring on real robot
            drive       = new RobotDrive(leftMotor, rightMotor);
            dthread     = new DriveThread(this, drive, stick, rightMotor, leftMotor);// JAG CHANGE
            
            //Climber Constructors
            upPart      = new JoystickButton(Wiring.CLIMB_UP_PART);
            upMax       = new JoystickButton(Wiring.CLIMB_UP_MAX);
            down        = new JoystickButton(Wiring.CLIMB_DOWN);
            autoClimb   = new JoystickButton(Wiring.AUTO_CLIMB);
            climb       = new ClimbingSystem(this);
            comp        = new Compressor(8,1);
            comp.start();
//            climb.goDownManual(1); // **ATTENTION** take out at RIT in order to pass rules
            
            //Shooter Constructors
            shootOn     = new JoystickButton(stick, Wiring.XBOX_A_BUTTON);
            shootOff    = new JoystickButton(stick, Wiring.XBOX_B_BUTTON);
            fire        = new JoystickButton(stick, Wiring.XBOX_X_BUTTON);
            shooter     = new Shooter(Wiring.SHOOTER_MOTOR);
            hopper      = new Hopper(Wiring.HOPPER_MOTOR);
            
            //Autonomous Stuff
            gyro        = new Gyro(Wiring.GYRO_ANALOG);
            out         = new Output();
            pid         = new PIDController(Wiring.P, Wiring.I, Wiring.D, gyro, out);
            pid.setAbsoluteTolerance(1);
            test = new JoystickButton(Wiring.XBOX_Y_BUTTON);
            SmartDashboard.putBoolean("Shooter Up To Speed", false);

//        } 
//        catch (CANTimeoutException ex) 
//        {
//            ex.printStackTrace();  //JAG CHANGE
//        }
    }

    public void autonomousInit()
    {
        
    }

    public void autonomousPeriodic() 
    {
        //if(!shooter.atSpeed())
        //{
                shooter.shoot();
                Timer.delay(6);
        //}
            
        //if(shooter.atSpeed())
        //{
                hopper.load();
                Timer.delay(1.5);
                hopper.load();
                Timer.delay(1.5);
                hopper.load();
        //}
    }

    public void teleopInit()
    {
        (new Thread(dthread)).start();
        
    }
    
    public void teleopPeriodic() 
    {
        climb.iterativeCheck();
        shooterCheck();
    }
    
    public void shooterCheck()
    {   
        // logic for shooter motor control
        if (shootOn.debouncedValue())
        {
            shooting = true;
            //shooter.shoot();
        }
        else if (shootOff.debouncedValue())
        {
            //shooter.stop();
            shooting = false;
        }

        // shoot if not already pressed down
        if(shooting)
        {
            //shooter.shoot();
        }
        else
        {
            //shooter.stop();
        }

        //semi automatic shooting system
        //System.out.println("Loading!");
        load(); 
    }
    
    public void disabledPeriodic()
    {
        hopperState = 0;
        hopper.hopper.set(0);
        
    }
    
    public void disabledInit()
    {
        
    }
    
    public boolean shouldAbort() 
    {
        try 
        {
            if(!isEnabled() || !driverStationButtons.getDigital(Wiring.CLIMB_ON) )
            {
                errHandler.error("ABORTING ALL CLIMBING OPERATIONS");
                return true;
            }
            
        }
        catch (DriverStationEnhancedIO.EnhancedIOException ex) 
        {
            ex.printStackTrace();
        }
        return false;
    }
    
    public void load()
    {
        //System.out.println(hopperState);
        switch (hopperState)
        {
            case 0:
                hopper.hopper.set(0);
                if(fire.debouncedValue() /*&& shooting*/)
                {
                    hopperState = 1;
                    hopper.load();
                }
                break;
            case 1:
                hopperTimeout++;
                if(hopper.mag.get() && hopperTimeout < 100)
                {
                    hopperState = 2;
                    hopperTimeout = 0;
                }
                break;
            case 2:
                hopperTimeout++;
                if(!hopper.mag.get() && hopperTimeout < 100)
                {
                    hopperState = 0;
                    hopperTimeout = 0;
                    hopper.hopper.set(0.0);
                }
                break;
            default:
                hopperState = 0;
                break;
        }
    }
}
