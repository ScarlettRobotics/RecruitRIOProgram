package main.java.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */


//To make anything new, go to the appropriate package and right click -> new -> WPILib Java Development -> <Whatever you want to make>

public class ExampleCommand extends Command {

    public ExampleCommand() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
