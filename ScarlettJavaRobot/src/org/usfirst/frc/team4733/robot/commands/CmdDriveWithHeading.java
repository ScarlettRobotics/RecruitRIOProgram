/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;

/**
 * An example command.  You can replace me with your own command.
 */
public class CmdDriveWithHeading extends Command {
	private  double m_throttle, m_angle, m_timer;
	
	public CmdDriveWithHeading(double throttle, double angle, double timer) {
		// Use requires() here to declare subsystem dependencies
		//requires(Robot.m_subsystem);
		requires(Robot.m_driveTrain);
		m_throttle = throttle;
		m_angle = angle;
		m_timer = timer;
		DriverStation.reportError("Test2", true);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		DriverStation.reportError("TimeOut init", true);
		setTimeout(m_timer);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//DriverStation.reportError("TestPing 1", true);
		//const loop gain is 0.03
		double heading = Robot.m_driveTrain.getHeading();
		double loopGainConst = 0.03;
		double adjust;
		
		
		//Code from c++ base
		if(heading > 180) 
		{
			heading = heading - 360;
		}
		
		if(heading < -180) 
		{
			heading = heading + 360;
		}
		
		if(m_throttle < 0) 
		{ 
			// going forward
			adjust = m_angle - heading; // default behaviour
		} 
		else 
		{ 
			// going backward
			adjust = heading - m_angle; // reversed behaviour to adjust correctly
		}
		
		
		Robot.m_driveTrain.CurveDrive(m_throttle, adjust * loopGainConst, false);
		
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut();
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
