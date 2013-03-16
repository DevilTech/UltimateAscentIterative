package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO;
import edu.wpi.first.wpilibj.DriverStationEnhancedIO.EnhancedIOException;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Timer;

//THE AUTHOR IS SAM!, THE ONE WHO CASTS PI TO AN INTEGER!!!
//NEVER FORGET
//#ULTIMATEASCENT
//#yoloswagalicious

public class JoystickButton
{
    Joystick joy;
    DriverStationEnhancedIO digital = DriverStation.getInstance().getEnhancedIO();
    int button;
    Timer time;
    boolean flag = true;
    boolean flag1 = true;
    boolean flag2 = true;
    
    public JoystickButton(int JoystickNum, int Button)
    {
        joy = new Joystick(JoystickNum);
        button = Button;
        time = new Timer();
        time.reset();
    }
    
    public JoystickButton(int DigitalChannel)
    {
        button = DigitalChannel;
        time = new Timer();
        time.reset();
    }
    
    public JoystickButton(Joystick joy, int Button)
    {
        this.joy = joy;
        button = Button;
        time = new Timer();
        time.reset();
    }
    
    public boolean debouncedValue()
    {
        //toggle logic: check to see if it's already pressed
        if(joy.getRawButton(button))
        {
            if(flag)
            {
                flag = false;
                return true;
            }
        }
        else
        {
            flag = true;
        }
        return false;
    }
    
    public boolean debouncedValueDigital()
    {
        try {
            //toggle logic: check to see if it's already pressed
            //System.out.println("Button State:  " + digital.get());
            //System.out.println("Button" +button+ " : " + digital.getDigital(button) + "Flag: " + flag1);
            
            if(!digital.getDigital(button))
            {
                if(flag1)
                {
                    flag1 = false;
                    //System.out.println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$ RETURNING TRUE $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    return true;
                }
            }
            else
            {
                flag1 = true;
            }

        } 
        catch (EnhancedIOException ex) 
        {
            System.out.println("FAILED");
            ex.printStackTrace();
        }
        return false;
    }
    
    public boolean holdButtonTime(double pauseTime)
    {
        double curTime = 0.0;
        if(joy.getRawButton(button))
        {
            if(flag2)
            {
                flag2 = false;
                curTime = time.get();
            }
            else if(time.get() > curTime + pauseTime)
            {
                return true;
            }
        }
        else
        {
            time.stop();
            time.reset();
            flag2 = true;
        }
        return false;
    }
    public boolean holdButtonTimeDigital(double pauseTime)
    {
        try {
            double curTime = 0.0;
            if(!digital.getDigital(button))
            {
                if(flag2)
                {
                    flag2 = false;
                    curTime = time.get();
                }
                else if(time.get() > curTime + pauseTime)
                {
                    return true;
                }
            }
            else
            {
                time.stop();
                time.reset();
                flag2 = true;
            }
            
        } catch (EnhancedIOException ex) {
            ex.printStackTrace();
        }
        return false;
    }
}
