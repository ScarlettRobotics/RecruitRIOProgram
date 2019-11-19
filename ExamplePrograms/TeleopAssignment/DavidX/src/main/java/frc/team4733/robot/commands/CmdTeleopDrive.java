/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package main.java.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import main.java.frc.team4733.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class CmdTeleopDrive extends Command {
	public CmdTeleopDrive() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_driveTrain);
	}

	// Called just before this Command runs the first time
	@Override
	
	
	protected void initialize() {
		//Make sensitivity label
		if(!SmartDashboard.containsKey("Sensitivity: "))
		{
			SmartDashboard.putNumber("Sensitivity: ", 1.0);
		}
		
		if(!SmartDashboard.containsKey("Squared? "))
		{
			SmartDashboard.putBoolean("Squared? ", false);
		}
		
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		double Sensitivity = SmartDashboard.getNumber("Sensitivity: ", 1.0);
		boolean squaredInputs = SmartDashboard.getBoolean("Squared? ", false);
		
		if(Sensitivity > 1 || Sensitivity < 0)
		{
			Sensitivity = 1;
		}
		
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
		
		Robot.m_driveTrain.TankDrive(-leftAxis * Sensitivity, -rightAxis * Sensitivity);
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
		
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
