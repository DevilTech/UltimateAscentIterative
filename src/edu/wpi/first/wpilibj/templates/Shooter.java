package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.CANJaguar;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.can.CANTimeoutException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Shooter 
{
    Victor shoot;
    boolean hasSeenMax = false;
    final double aCHigh = 25;
    final double aCLow  = 19;
    boolean atMax;
    
    public Shooter(int port)
    {
        shoot = new Victor(port);
    }
    
    public void shoot()
    {
        System.out.println("Shooting");
        //try 
        //{
            shoot.set(1.0);

        /*}
        catch (CANTimeoutException ex)
        {
            ex.printStackTrace();
        }
        try 
        {
            if(hasSeenMax && shoot.getOutputCurrent() < aCLow)
            {
                SmartDashboard.putBoolean("Shooter Up To Speed", true);
                atMax = true;
            }
            else if (shoot.getOutputCurrent() > aCHigh)
            {
                hasSeenMax = true;
            }
        } 
        catch (CANTimeoutException ex) {
            ex.printStackTrace();
        }*/
    }
    
    public void stop()
    {
        shoot.set(0);
        //hasSeenMax = false;
        //atMax = false;
        SmartDashboard.putBoolean("Shooter Up To Speed", false);
    }
    public boolean atSpeed(){
        if(!hasSeenMax){
            atMax = false;
        }
        return atMax;
    }
}
