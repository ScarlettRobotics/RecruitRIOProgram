/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team4733.robot.Robot;

/**
 * This command allows PS3 joystick to drive the robot. It is always running
 * except when interrupted by another command.
 */
public class DriveWithJoystick extends Command {
	public DriveWithJoystick() {
		requires(Robot.drivetrain);
	}

	@Override
	protected void execute() {
		Robot.drivetrain.tankDrive(Robot.oi.getJoystick());
	}

	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.drivetrain.stop();
	}
}
