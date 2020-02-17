package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdClawSetTimed extends Command {

	double clawSpeedACT;
	double clawTimeACT;
    
	public CmdClawSetTimed(double clawSpeed, double clawTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
		requires(Robot.m_claw);
		clawSpeedACT = clawSpeed;
		clawTimeACT = clawTime;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(clawTimeACT); //Timeout command for the time set
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	Robot.m_claw.clawSet(clawSpeedACT);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_claw.clawSet(0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
