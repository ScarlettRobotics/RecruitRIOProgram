package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class CmdRandom extends CommandGroup {

    public CmdRandom() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    	double speed = -0.75;
    	
    	addSequential(new CmdGyroReset());
    	//addSequential(new CmdRotateToHeading(90, 2.5));
    	addParallel(new CmdDriveWithHeading(speed, 0, 7));
    	addSequential(new CmdElevatorTimed(0.3, 3));
    	
    	
    	
    	
    }
}
