/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.TimedCommand;

import org.usfirst.frc.team4733.robot.Robot;

/**
 * Extend the shooter and then retract it after a second.
 */
public class ExtendShooter extends TimedCommand {
	public ExtendShooter() {
		super(1);
		requires(Robot.shooter);
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.shooter.extendBoth();
	}

	// Called once after isFinished returns true
	@Override
	protected void end() {
		Robot.shooter.retractBoth();
	}
}
