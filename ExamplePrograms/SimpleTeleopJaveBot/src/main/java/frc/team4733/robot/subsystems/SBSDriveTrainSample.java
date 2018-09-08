package main.java.frc.team4733.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.VictorSP;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import main.java.frc.team4733.robot.commands.CmdTeleopTankDrive;

/**
 *
 */
public class SBSDriveTrainSample extends Subsystem {

    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	
	public SpeedController DTmotorFL = new Talon(2);
	public SpeedController DTmotorFR = new Talon(0);
	public SpeedController DTmotorRL = new VictorSP(3);
	public SpeedController DTmotorRR = new VictorSP(1);
	
	public SpeedControllerGroup leftMotors = new SpeedControllerGroup(DTmotorFL, DTmotorRL);
	public SpeedControllerGroup rightMotors = new SpeedControllerGroup(DTmotorFR, DTmotorRR);
	
	private DifferentialDrive DTtransmission = new DifferentialDrive(leftMotors, rightMotors);
	public ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	double masterSensitivity = 1.0;
	
	public void tankDrive(double left, double right)
	{
		DTtransmission.tankDrive(left * masterSensitivity, right * masterSensitivity);
	}
	
	public double getHeading()
	{
		return gyro.getAngle();
	}
	
	public void resetSensors()
	{
		gyro.reset();
	}

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    	setDefaultCommand(new CmdTeleopTankDrive());
    }
}

