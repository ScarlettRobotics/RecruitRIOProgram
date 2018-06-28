/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4733.robot.subsystems.Collector;

/**
 * Shoot the ball at the current angle.
 */
public class Shoot extends CommandGroup {
	public Shoot() {
		addSequential(new WaitForPressure());
		addSequential(new SetCollectionSpeed(Collector.kStop));
		addSequential(new OpenClaw());
		addSequential(new ExtendShooter());
	}
}
