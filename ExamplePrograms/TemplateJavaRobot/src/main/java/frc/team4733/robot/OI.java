/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main.java.frc.team4733.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
	//// CREATING BUTTONS
	// One type of button is a joystick button which is any button on a
	//// joystick.
	// You create one by telling it which joystick it's on and which button
	// number it is.
	// Joystick stick = new Joystick(port);
	// Button button = new JoystickButton(stick, buttonNumber);

	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.

	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:

	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());

	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());

	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	
	//Create Joysticks
	public Joystick m_xbox = new Joystick(0); //Create a new joystick on port 0 (DriverStation Port)
	public Joystick m_atk3 = new Joystick(1);
	
	//public double xboxLeftY = normalize(m_xbox.getRawAxis(2)); //assign raw values to axis's
	//public double xboxRightY = normalize(m_xbox.getRawAxis(5)); 
	//public double xboxTrigger = normalize(m_xbox.getRawAxis(3));
	
	public char sidedCommand = 'L'; //Used by Autonomous in order to determine side
	
	public void setSidedCommand(char gameData)
	{
		sidedCommand = gameData; //Used by Robot AutonomousInit
	}
	
	public double normalize(double input, double deadzone) //Normalize inputs to prevent deadzone errors
	{
		if(input < deadzone && input > -deadzone)
		{
			return 0;
		}
		else
		{
			return input;
		}
	}
	
	public Joystick getXbox() //Gets the controller
	{
		return m_xbox;
	}
	
	public Joystick getAtk3() //gets the Atk3 Controller
	{
		return m_atk3;
	}
	
	public void logToDashboardOI()
	{
		SmartDashboard.putNumber("xbox L Adj: ", normalize(m_xbox.getRawAxis(2), 0.07));
		SmartDashboard.putNumber("xbox R Adj: ", normalize(m_xbox.getRawAxis(5), 0.07));
		
		SmartDashboard.putNumber("Atk3 Y Axis Adj: ", normalize(m_atk3.getRawAxis(1), 0.08));
	}
	
}
