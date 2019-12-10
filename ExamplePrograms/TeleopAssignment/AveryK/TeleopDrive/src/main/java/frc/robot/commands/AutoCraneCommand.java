package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class AutoCraneCommand extends Command {

    double wantedValue;

    double LoopGainConstEncoder = 1.2;

    public CraneCommand() {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.crane);
    }

    // Called just before this Command runs the first time
    @Override
    protected void initialize() {
        wantedValue = Robot.crane.turnAngle();
    }

    // Called repeatedly when this Command is scheduled to run
    @Override
    protected void execute() {

        double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
        double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

        //Crane Turn
        //runCraneTurn(encoderValue);
        if(tv == 1.0) {
            runCraneLift(ty);
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    @Override
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    @Override
    protected void end() {}

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    @Override
    protected void interrupted() {}

    //For now we are just lifting the elevator to the correct height
    private void runCraneTurn() {
        double encoderValue = Robot.crane.turnAngle();

        if(turn > 0.1 || turn < -0.1) { //If turning currently
            Robot.crane.Turn(turn);
            wantedValue = Robot.crane.turnAngle();
        } else { //Otherwise turn back to current position
            double fixValue = (wantedValue - encoderValue) / 4096 * LoopGainConstEncoder;
            Robot.crane.Turn(fixValue);
        }
    }

    private void runCraneLift(double speed) {
        Robot.crane.Lift(speed);
    }
}