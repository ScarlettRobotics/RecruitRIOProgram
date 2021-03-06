package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

/**
 * An example command.  You can replace me with your own command.
 */
public class CraneCommand extends Command {

    double wantedValue;

    double LoopGainConstEncoder = 1.2;

    boolean isAuto = false;

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
        if(Robot.oi.controller.getRawButton(1)) {
            isAuto = !isAuto;
        }
        
        double encoderValue = Robot.crane.turnAngle();

        //Crane Lift
        runCraneLift();

        //Crane Turn
        runCraneTurn(encoderValue);
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

    private void runCraneTurn(double encoderValue) {
        double turn;
        if(!isAuto) {
            boolean turningUp = Robot.oi.controller.getRawButton(6); //Bumper
            boolean turningDown = Robot.oi.controller.getRawButton(5); //Bumper

            if(turningUp && !turningDown) { //If we should be going up
                turn = 0.5;
            } else if (turningDown && !turningUp) { //If we should be going down
                turn = -0.5;
            } else {
                turn = 0;
            }
        } else {
            double ty = NetworkTableInstance.getDefault().getTable("limelight").getEntry("ty").getDouble(0);
            double tv = NetworkTableInstance.getDefault().getTable("limelight").getEntry("tv").getDouble(0);

            if(tv == 1.0) {
                turn = ty;
            }
        }

        if(turn > 0.1 || turn < -0.1) { //If turning currently
            Robot.crane.Turn(turn);
            wantedValue = Robot.crane.turnAngle();
        } else {
            double fixValue = (wantedValue - encoderValue) / 4096 * LoopGainConstEncoder;
            Robot.crane.Turn(fixValue);
        }
    }

    private void runCraneLift() {
        double liftDown = Robot.oi.controller.getRawAxis(2); //Trigger
        double liftUp = Robot.oi.controller.getRawAxis(3); //Trigger

        Robot.crane.Lift(liftUp - liftDown); //Should give value between -1 and 1...
    }
}