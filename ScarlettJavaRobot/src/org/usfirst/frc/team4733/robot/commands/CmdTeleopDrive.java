/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import com.scarlettrobotics.frc.epslib.Settings;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.command.Command;

//import com.scarlettrobotics.frc.epslib.Settings;

/**
 * An example command.  You can replace me with your own command.
 */
@SuppressWarnings("unused")
public class CmdTeleopDrive extends Command {
	public CmdTeleopDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//Make sensitivity label
		/*
		if(!SmartDashboard.containsKey("Sensitivity: "))
		{
			SmartDashboard.putNumber("Sensitivity: ", 1.0);
		}
		
		if(!SmartDashboard.containsKey("Squared? "))
		{
			SmartDashboard.putBoolean("Squared? ", false);
		}
		*/
		
		Settings.putDefault("Sensitivity: ", 1.0);
		Settings.putDefault("Squared? ", false);
		
		Settings.putDefault("Main Controller?", 1);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//double Sensitivity = SmartDashboard.getNumber("Sensitivity: ", 1.0);
		double Sensitivity = Settings.get("Sensitivity: ", 1.0);
		double mainController = Settings.get("Main Controller?", 1);
		
		boolean squaredInputs = Settings.get("Squared? ", false);
		
		if(Sensitivity > 1 || Sensitivity < -1)
		{
			Sensitivity = 1;
		}
		
		if(mainController == 1)
		{
			
		
			double leftRawAxis = (Robot.m_oi.getXbox().getRawAxis(1));
			double rightRawAxis = (Robot.m_oi.getXbox().getRawAxis(5));
		
			double leftAxis = Robot.m_oi.normalize(leftRawAxis, 0.07);
			double rightAxis = Robot.m_oi.normalize(rightRawAxis, 0.07);
		
			if(squaredInputs)
			{
				if(leftAxis >= 0)
				{
					leftAxis = leftAxis * leftAxis;
				}
				else
				{
					leftAxis = leftAxis * leftAxis * -1;
				}
			
				if(rightAxis >= 0)
				{
					rightAxis = rightAxis * rightAxis;
				}
				else
				{
					rightAxis = rightAxis * rightAxis * -1;
				}
			}	
		
			
		
		
			Robot.m_driveTrain.TankDrive(leftAxis * Sensitivity, rightAxis * Sensitivity);
		
		
			
		}
		else if(mainController == 2)
		{
			double yValue = Robot.m_oi.normalize(Robot.m_oi.m_atk3.getRawAxis(1), 0.07);
	    	double xValue = Robot.m_oi.normalize(Robot.m_oi.m_atk3.getRawAxis(0), 0.07) * -1;
	    	
	    	if(squaredInputs)
			{
	    		if(yValue >= 0)
	    		{
	    			yValue = yValue * yValue;
	    		}
	    		else
	    		{
	    			yValue = yValue * yValue * -1;
	    		}
	    		
	    		if(xValue >=0)
	    		{
	    			xValue = xValue * xValue;
	    		}
	    		else
	    		{
	    			xValue = xValue * xValue * -1;
	    		}
			}
	    	
	    	/*
	    	if(yValue < 0.07)
	    	{
	    		xValue = xValue * -1;
	    	}
	    	*/

	    	yValue = yValue * Sensitivity;
	    	xValue = xValue * Sensitivity;

	    	Robot.m_driveTrain.ArcadeDrive(yValue, xValue);
	    	
			double rightRawAxis2 = (Robot.m_oi.getXbox().getRawAxis(5));
		
			double rightAxis2 = Robot.m_oi.normalize(rightRawAxis2, 0.07);
			
			if(rightAxis2 < -0.5)
			{
				Robot.m_driveTrain.setPneumatics(1);
			}
			else if(rightAxis2 > 0.5)
			{
				Robot.m_driveTrain.setPneumatics(2);
			}
			else
			{
				Robot.m_driveTrain.setPneumatics(3);
			}
	    	
		}
		else
		{
			DriverStation.reportError("NO VALID VALUE SELECTED, DEFAULTING", true);
			mainController = 1;
		}
		
		if(Robot.m_oi.getXbox().getRawButtonPressed(2))
		{
			Robot.m_driveTrain.resetSensors();
		}
		
		double yAccl = Robot.m_driveTrain.m_accelerometer.getY();
		double xAccl = Robot.m_driveTrain.m_accelerometer.getZ();
		
		Robot.m_oi.getXbox().setRumble(RumbleType.kRightRumble, Math.abs(yAccl)/2);
		Robot.m_oi.getXbox().setRumble(RumbleType.kLeftRumble, Math.abs(xAccl)/2);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		
		Robot.m_driveTrain.TankDrive(0, 0);
		Robot.m_oi.getXbox().setRumble(RumbleType.kRightRumble, 0);
		Robot.m_oi.getXbox().setRumble(RumbleType.kLeftRumble, 0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
