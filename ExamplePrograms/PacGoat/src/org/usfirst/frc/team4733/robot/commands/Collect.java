/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc.team4733.robot.subsystems.Collector;
import org.usfirst.frc.team4733.robot.subsystems.Pivot;

/**
 * Get the robot set to collect balls.
 */
public class Collect extends CommandGroup {
	public Collect() {
		addSequential(new SetCollectionSpeed(Collector.kForward));
		addParallel(new CloseClaw());
		addSequential(new SetPivotSetpoint(Pivot.kCollect));
		addSequential(new WaitForBall());
	}
}
