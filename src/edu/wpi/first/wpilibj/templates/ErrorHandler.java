package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class ErrorHandler
{
    Timer time;
    static ErrorHandler errorHandler;
    
    public static ErrorHandler getErrorHandler()
    {
        if(errorHandler == null)
        {
            errorHandler = new ErrorHandler();
        }
        return errorHandler;
    }
    
    public ErrorHandler() {
        time = new Timer();
        time.start();
    }
    
    public void error(String err)
    {
        time.reset();
        clear();
        //SmartDashboard.putString("Error!", " " + err);
    }
    
    public void refresh()
    {

        
        if (time.get() > 5) {      
            clear();
        }
    }
    
    public void clear()
    {
        //SmartDashboard.putString("Error!", " ");
    }
}
