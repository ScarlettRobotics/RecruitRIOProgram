/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.Victor;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * The claw subsystem is a simple system with a motor for opening and closing.
 * If using stronger motors, you should probably use a sensor so that the motors
 * don't stall.
 */
public class Claw extends Subsystem {
	private Victor m_motor = new Victor(7);
	private DigitalInput m_contact = new DigitalInput(5);

	public Claw() {
		super();

		// Let's name everything on the LiveWindow
		addChild("Motor", m_motor);
		addChild("Limit Switch", m_contact);
	}

	@Override
	public void initDefaultCommand() {
	}

	public void log() {
	}

	/**
	 * Set the claw motor to move in the open direction.
	 */
	public void open() {
		m_motor.set(-1);
	}

	/**
	 * Set the claw motor to move in the close direction.
	 */
	public void close() {
		m_motor.set(1);
	}

	/**
	 * Stops the claw motor from moving.
	 */
	public void stop() {
		m_motor.set(0);
	}

	/**
	 * Return true when the robot is grabbing an object hard enough to trigger
	 * the limit switch.
	 */
	public boolean isGrabbing() {
		return m_contact.get();
	}
}
