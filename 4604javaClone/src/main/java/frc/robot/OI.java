package frc.robot;

import edu.wpi.first.wpilibj.Joystick;

public final class OI
{
    public static Joystick joystick1 = new Joystick(Constants.main_controller);
    
    
    public static double normalize(double input, double deadzone) //Normalize inputs to prevent deadzone errors
	{
		if(input < deadzone && input > -deadzone) {
            return 0;
        }
		else { 
            return input;
        }
	}

}