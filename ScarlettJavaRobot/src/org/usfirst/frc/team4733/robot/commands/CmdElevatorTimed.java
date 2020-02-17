package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdElevatorTimed extends Command {

	double elevatorSpeed, timeACT;
	
    public CmdElevatorTimed(double speed, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_claw);
    	elevatorSpeed = speed;
    	timeACT = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(timeACT);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.m_claw.elevatorSet(elevatorSpeed);
    
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_claw.elevatorSet(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
