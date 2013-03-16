package edu.wpi.first.wpilibj.templates;

import edu.wpi.first.wpilibj.PIDOutput;

public class Output implements PIDOutput
{
    double pidOut;
    
    public void pidWrite(double d)
    {
        pidOut = d;
    }
    
    public double getPidOut()
    {
        return pidOut;
    }
    
}
