/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import com.scarlettrobotics.frc.epslib.Settings;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;

//import com.scarlettrobotics.frc.epslib.Settings;

/**
 * An example command.  You can replace me with your own command.
 */
@SuppressWarnings("unused")
public class CmdTeleopClaw extends Command {
	public CmdTeleopClaw() {
		// Use requires() here to declare subsystem dependencies
		requires(Robot.m_claw);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		
		/*
		if(!SmartDashboard.containsKey("Claw Speed: "))
		{
			SmartDashboard.putNumber("Claw Speed: ", 0.9);
		}
		*/
		
		Settings.putDefault("Claw Speed: ", 0.9);
	}

	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() 
	{
		//double clawSpeed = SmartDashboard.getNumber("Claw Speed: ", 0.9);
		double clawSpeed = Settings.get("Claw Speed: ", 0.9);
		double elevatorUpSpeed = 0.9;
		double elevatorDownSpeed = -0.8;
		
		
		
		//Enable Both 1 & 2 player controllers
		if(Robot.m_oi.getAtk3().getTrigger())
		{
			Robot.m_claw.clawSet(clawSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(3))
		{
			Robot.m_claw.clawSet(-clawSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(4))
		{
			double atk3YAxis = Robot.m_oi.getAtk3().getRawAxis(1);
			Robot.m_claw.clawSet((Robot.m_oi.normalize(atk3YAxis, 0.08)) * -clawSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(5))
		{
			double atk3YAxis = Robot.m_oi.getAtk3().getRawAxis(1);
			Robot.m_claw.clawSet((Robot.m_oi.normalize(atk3YAxis, 0.08)) * clawSpeed);
		}
		else
		{
			double leftTriggerRawAxis = Robot.m_oi.getXbox().getRawAxis(2);
			double rightTriggerRawAxis = Robot.m_oi.getXbox().getRawAxis(3);
			
			double normalizedRightTriggerAxis = Robot.m_oi.normalize(rightTriggerRawAxis, 0.08);
			double normalizedLeftTriggerAxis = Robot.m_oi.normalize(leftTriggerRawAxis, 0.08);
			
			Robot.m_claw.clawSet(normalizedRightTriggerAxis - normalizedLeftTriggerAxis);
		}
		
		if(Robot.m_oi.getAtk3().getRawButton(6))
		{
			Robot.m_claw.elevatorSet(elevatorUpSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(7))
		{
			Robot.m_claw.elevatorSet(elevatorDownSpeed);
		}
		else if(Robot.m_oi.getAtk3().getRawButton(2))
		{
			double atk3YAxis = Robot.m_oi.getAtk3().getRawAxis(1);
			Robot.m_claw.elevatorSet((Robot.m_oi.normalize(atk3YAxis, 0.08)) * elevatorUpSpeed);
		}
		else if(Robot.m_oi.getXbox().getRawButton(6))
		{
			Robot.m_claw.elevatorSet(elevatorUpSpeed);
		}
		else if(Robot.m_oi.getXbox().getRawButton(5))
		{
			Robot.m_claw.elevatorSet(elevatorDownSpeed);
		}
		else
		{
			Robot.m_claw.elevatorSet(0);
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
		Robot.m_claw.clawSet(0);
		Robot.m_claw.elevatorSet(0);
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
	}
}
