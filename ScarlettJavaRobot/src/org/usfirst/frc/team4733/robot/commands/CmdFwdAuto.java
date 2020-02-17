/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * An example command.  You can replace me with your own command.
 */
public class CmdFwdAuto extends CommandGroup {
	public CmdFwdAuto() {
	
		//addSequential(new commandname()) to do a command
		//addParallel(new commandname()) to do a command in parallel with the next command
		
		double headingSpeed = -0.6;
		
		addSequential(new CmdGyroReset());
		addSequential(new CmdDriveWithHeading(headingSpeed, 0, 1.7)); //Caution with the Speed!
		addSequential(new CmdGyroReset());
		
		
		
	}
}
