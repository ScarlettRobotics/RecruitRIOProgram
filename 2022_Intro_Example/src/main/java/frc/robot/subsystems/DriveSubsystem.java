// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorPorts;

public class DriveSubsystem extends SubsystemBase {
	//Here we define our motors
	public final SpeedController m_motorFL = new VictorSP(MotorPorts.kMotorFLPort);
	public final SpeedController m_motorRL = new WPI_VictorSPX(MotorPorts.kCanMotorRLPort);

	public final SpeedController m_motorFR = new VictorSP(MotorPorts.kMotorFRPort);
	public final SpeedController m_motorRR = new WPI_VictorSPX(MotorPorts.kCanMotorRRPort);

	//Here we define our motor groups, left and right
	public final SpeedControllerGroup m_LMotors = new SpeedControllerGroup(m_motorFL, m_motorRL);
	public final SpeedControllerGroup m_RMotors = new SpeedControllerGroup(m_motorFR, m_motorRR);

	//Here we create our drive train
	private final DifferentialDrive m_transmission = new DifferentialDrive(m_LMotors, m_RMotors);

	/**
	 * Moves the robot using tank drive
	 * @param left The speed of the left motors
	 * @param right The speed of the right motors
	 */
	public void drive(double left, double right) {
		m_transmission.tankDrive(left, right);
	}
}
