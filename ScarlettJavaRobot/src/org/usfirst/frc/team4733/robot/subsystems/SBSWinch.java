/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.subsystems;

import org.usfirst.frc.team4733.robot.commands.CmdTeleopWinch;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SBSWinch extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	public SpeedController m_winch = new Spark(4);
	
	public void setWinch(double winchSpeed)
	{
		if(winchSpeed > 1 || winchSpeed < -1)
		{
			DriverStation.reportError("WINCH INPUTS EXCCEDED", true);
		}
		else
		{
			m_winch.set(winchSpeed);
		}
	}
	
	public void logToDashboardWinch()
	{
		SmartDashboard.putNumber("Winch", m_winch.get());
	}
	

	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CmdTeleopWinch());
	}
}
