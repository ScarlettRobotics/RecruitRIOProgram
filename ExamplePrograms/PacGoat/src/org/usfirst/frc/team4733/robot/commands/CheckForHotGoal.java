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
 * This command looks for the hot goal and waits until it's detected or timed
 * out. The timeout is because it's better to shoot and get some autonomous
 * points than get none. When called sequentially, this command will block until
 * the hot goal is detected or until it is timed out.
 */
public class CheckForHotGoal extends Command {
	public CheckForHotGoal(double time) {
		setTimeout(time);
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		return isTimedOut() || Robot.shooter.goalIsHot();
	}
}
