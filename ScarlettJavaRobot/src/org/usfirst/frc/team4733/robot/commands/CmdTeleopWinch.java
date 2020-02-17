/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class CmdTeleopWinch extends Command {
	public CmdTeleopWinch() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_winch);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		double winchSpeed = 0.75;
		
		//Make winch commands
		if(Robot.m_oi.getAtk3().getRawButton(11))
		{
			Robot.m_winch.setWinch(winchSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(10))
		{
			Robot.m_winch.setWinch(-winchSpeed);
		}
		else if(Robot.m_oi.getXbox().getRawButton(4))
		{
			Robot.m_winch.setWinch(winchSpeed);
		}
		else if(Robot.m_oi.getXbox().getRawButton(3))
		{
			Robot.m_winch.setWinch(-winchSpeed);
		}
		else
		{
			Robot.m_winch.setWinch(0);
		}
		
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.m_winch.setWinch(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
