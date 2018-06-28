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
 * This command sets the collector rollers spinning at the given speed. Since
 * there is no sensor for detecting speed, it finishes immediately. As a result,
 * the spinners may still be adjusting their speed.
 */
public class SetCollectionSpeed extends InstantCommand {
	private double m_speed;

	public SetCollectionSpeed(double speed) {
		requires(Robot.collector);
		this.m_speed = speed;
	}

	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		Robot.collector.setSpeed(m_speed);
	}
}
