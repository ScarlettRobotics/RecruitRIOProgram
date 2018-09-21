package main.java.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import main.java.frc.team4733.robot.Robot;

/**
 *
 */
public class CmdTeleopTankDrive extends Command {

	double sensitivity;
	
    public CmdTeleopTankDrive() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_sampleDriveTrain);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	
    	sensitivity = 1.0;
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	double leftAxis = Robot.m_oi.normalize(Robot.m_oi.xboxController.getRawAxis(1), 0.07);
    	double rightAxis = Robot.m_oi.normalize(Robot.m_oi.xboxController.getRawAxis(5), 0.07);
    	
    	Robot.m_sampleDriveTrain.tankDrive(leftAxis, rightAxis);

        /*
        Arcade Drive
    	double yValue = Robot.m_oi.normalize(Robot.m_oi.atk3.getRawAxis(1), 0.07);
    	double xValue = Robot.m_oi.normalize(Robot.m_oi.atk3.getRawAxis(0), 0.07) * -1;
    	
    	Robot.m_sampleDriveTrain.arcadeDrive(yValue, xValue);
    	*/
    }
    

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_sampleDriveTrain.tankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
