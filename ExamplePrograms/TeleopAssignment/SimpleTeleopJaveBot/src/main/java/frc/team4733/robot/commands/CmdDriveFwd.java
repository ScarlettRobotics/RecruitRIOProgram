package main.java.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import main.java.frc.team4733.robot.Robot;

/**
 *
 */
public class CmdDriveFwd extends Command {
	
	double throttle, time;
	
    public CmdDriveFwd(double throttle, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_sampleDriveTrain);
    	throttle = this.throttle;
    	time = this.time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(time);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.m_sampleDriveTrain.tankDrive(throttle, throttle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
