package main.java.frc.team4733.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import main.java.frc.team4733.robot.commands.CmdTeleopDrive;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.DriverStation;

/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class SBSDriveTrain extends Subsystem {
	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	
	//Define Speed Controllers & ports for the DriveTrain
	public SpeedController m_motorFL = new Talon(2); //Create a new Talon on PWM port 2
	public SpeedController m_motorFR = new Talon(0);
	public SpeedController m_motorRL = new VictorSP(3); //swapped victor SP's after Houston
	public SpeedController m_motorRR = new VictorSP(1); 
	
	//Create L & R motor groups in order to make tank drive input easier
	public SpeedControllerGroup m_LMotors = new SpeedControllerGroup(m_motorFL, m_motorRL);
	public SpeedControllerGroup m_RMotors = new SpeedControllerGroup(m_motorFR, m_motorRR);
			
	private DifferentialDrive m_transmission = new DifferentialDrive(m_LMotors, m_RMotors); //Create a tank drive object
	public ADXRS450_Gyro m_gyro = new ADXRS450_Gyro(); //Create a gyroscope
	
	
	public void DriveTrain() 
	{
		//no clue what functions to put in here yet
		/*
		//addChild gives it a sendable tag so we can use smartDashBoard
		addChild("MotorFL", (Sendable) m_motorFL);
		addChild("MotorFR", (Sendable) m_motorFR);
		addChild("MotorRL", (Sendable) m_motorRL);
		addChild("MotorRR", (Sendable) m_motorRR);
		*/
	
	}
	
	double masterSensitivity = 1.0;
	
	public void TankDrive(double left, double right)
	{
		if(left > 1 || left < -1 || right > 1 || right < -1)
		{
			DriverStation.reportError("TANK DRIVE INPUTS EXCEEDED", true);
		}
		else
		{
			m_transmission.tankDrive((left * masterSensitivity), (right * masterSensitivity));
		}
	}
	
	public void arcadeDrive(double throttle, double angle)
	{
		if(throttle > 1 || throttle < -1 || angle > 1 || angle < -1)
		{
			DriverStation.reportError("ARCADE DRIVE INPUTS EXCCEDED", true);
		}
		else
		{
			m_transmission.arcadeDrive(throttle * masterSensitivity, angle * masterSensitivity);
		}
	}
	
	
	//get values for Smart Dashboard:
	
	public double getHeading()
	{
		return m_gyro.getAngle();
	}
	
	
	public void resetSensors()
	{
		//Reset all sensors
		m_gyro.reset();
	}
	
	public void logToDashboardDT()
	{
		SmartDashboard.putNumber("motorFL: ", m_motorFL.get());
		SmartDashboard.putNumber("motorFR: ", m_motorFR.get());
		SmartDashboard.putNumber("motorRL: ", m_motorRL.get());
		SmartDashboard.putNumber("motorRR: ", m_motorRR.get());
		SmartDashboard.putNumber("Orientation: ", getHeading());
	}
	
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand())
		setDefaultCommand(new CmdTeleopDrive());
	}
}