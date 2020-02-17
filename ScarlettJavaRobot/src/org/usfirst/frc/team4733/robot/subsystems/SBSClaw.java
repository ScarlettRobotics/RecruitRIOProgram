/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.subsystems;

import org.usfirst.frc.team4733.robot.commands.CmdTeleopClaw;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SBSClaw extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public SpeedController m_elevator = new Spark(5);
	public SpeedController m_clawLeft = new Spark(6);
	public SpeedController m_clawRight = new Spark(7);
	
	
	private SpeedControllerGroup clawMotors = new SpeedControllerGroup(m_clawLeft, m_clawRight);
	
	public void clawSet(double clawSpeed)
	{
		if(clawSpeed > 1 || clawSpeed < -1)
		{
			DriverStation.reportError("CLAW INPUTS EXCCEDED", true);
		}
		else
		{
			clawMotors.set(clawSpeed);
		}
	}
	
	public void elevatorSet(double elevatorSpeed)
	{
		if(elevatorSpeed > 1 || elevatorSpeed < -1)
		{
			DriverStation.reportError("ELEVATOR INPUTS EXCEEDED", true);
		}
		else
		{
			m_elevator.set(elevatorSpeed);
		}
	}
	
	public void logToDashboardClaw()
	{
		SmartDashboard.putNumber("Elevator: ", m_elevator.get());
		SmartDashboard.putNumber("Claw Left: ", m_clawLeft.get());
		SmartDashboard.putNumber("Claw Right: ", m_clawRight.get());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
		setDefaultCommand(new CmdTeleopClaw());

	
	}
	
}
