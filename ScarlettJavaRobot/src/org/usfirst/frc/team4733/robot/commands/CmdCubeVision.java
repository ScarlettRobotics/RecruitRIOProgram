package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import org.usfirst.frc.team4733.robot.Robot;


public class CmdCubeVision extends Command {

    double actThrottle, time;

    public CmdCubeVision(double throttle, double maxTime) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.m_driveTrain);

        actThrottle = throttle;
        time = maxTime;
    }


    /**
     * The initialize method is called just before the first time
     * this Command is run after being started.
     */
    @Override
    protected void initialize() {
        setTimeout(time);
    }


    /**
     * The execute method is called repeatedly when this Command is
     * scheduled to run until this Command either finishes or is canceled.
     */
    @Override
    protected void execute()
    {
        //Robot.m_claw.clawSet(-0.9);
        //if(Robot.visionWorking)
        //{
            double localCenterX;
            synchronized (Robot.imgLock) {
                localCenterX = Robot.getCenterXValue();
            }
            double turn = localCenterX - (Robot.IMG_WIDTH / 2);
            //DriverStation.reportWarning("Turn Value: " + turn, true);
			//DriverStation.reportError("Center X Value: " + centerX, true);
			//DriverStation.reportError("Vision Working Status: " + Robot.visionWorking, true);

            SmartDashboard.putNumber("CenterX: ", Robot.centerX);
            SmartDashboard.putNumber("Frame Count", Robot.frameCnt);
           // SmartDashboard.putBoolean("VisionWorking? " Robot.visionWorking)
            
            //Robot.m_driveTrain.ArcadeDrive(-0.6, (turn * 0.005));
        //}
        /*else
        {
            //This is what should execute when NO targets are found
            double adjust = -0.5;
            Robot.m_driveTrain.TankDrive(-adjust, adjust);
        }
        */
    }


    /**
     * <p>
     * Returns whether this command is finished. If it is, then the command will be removed and
     * {@link #end()} will be called.
     * </p><p>
     * It may be useful for a team to reference the {@link #isTimedOut()}
     * method for time-sensitive commands.
     * </p><p>
     * Returning false will result in the command never ending automatically. It may still be
     * cancelled manually or interrupted by another command. Returning true will result in the
     * command executing once and finishing immediately. It is recommended to use
     * {@link edu.wpi.first.wpilibj.command.InstantCommand} (added in 2017) for this.
     * </p>
     *
     * @return whether this command is finished.
     * @see Command#isTimedOut() isTimedOut()
     */
    @Override
    protected boolean isFinished() {
        // TODO: Make this return true when this Command no longer needs to run execute()
        return isTimedOut();
    }


    /**
     * Called once when the command ended peacefully; that is it is called once
     * after {@link #isFinished()} returns true. This is where you may want to
     * wrap up loose ends, like shutting off a motor that was being used in the
     * command.
     */
    @Override
    protected void end() {
        Robot.m_driveTrain.TankDrive(0,0);
        Robot.m_claw.clawSet(0);

    }


    /**
     * <p>
     * Called when the command ends because somebody called {@link #cancel()} or
     * another command shared the same requirements as this one, and booted it out. For example,
     * it is called when another command which requires one or more of the same
     * subsystems is scheduled to run.
     * </p><p>
     * This is where you may want to wrap up loose ends, like shutting off a motor that was being
     * used in the command.
     * </p><p>
     * Generally, it is useful to simply call the {@link #end()} method within this
     * method, as done here.
     * </p>
     */
    @Override
    protected void interrupted() {
        super.interrupted();
    }
}
