package org.usfirst.frc.team4733.robot.commands;

import org.usfirst.frc.team4733.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class CmdRotateToHeading extends Command {

	double desiredHeading;
	double maxTime;
	
    public CmdRotateToHeading(double angle, double time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.m_driveTrain);
    	desiredHeading = angle;
    	maxTime = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	setTimeout(maxTime);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	
    	//Adapted from 2018 c++ code (CommandRotateHeading)
    	double heading = Robot.m_driveTrain.getHeading();
    	
    	if(heading > 180) 
    	{
			heading = heading - 360;
    	}
    	if(heading < -180) 
    	{
			heading = heading + 360;
    	}
    	
    	double adjust = (desiredHeading - heading) / 180;
    	
    	if(adjust > 0 && adjust < 0.15) 
    	{
    		adjust = 0.15;
    	}
   
    	if(adjust < 0 && adjust > -0.15) 
    	{
    		adjust = -0.15;
    	}
    	
    	Robot.m_driveTrain.TankDrive(-adjust, adjust);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.m_driveTrain.TankDrive(0, 0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
