/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.InstantCommand;

import org.usfirst.frc.team4733.robot.Robot;

/**
 * Close the claw.
 *
 * <p>NOTE: It doesn't wait for the claw to close since there is no sensor to
 * detect that.
 */
public class CloseClaw extends InstantCommand {
	public CloseClaw() {
		requires(Robot.collector);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.collector.close();
	}
}
