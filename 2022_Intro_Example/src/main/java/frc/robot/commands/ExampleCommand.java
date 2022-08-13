// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.Constants.Joysticks;
import frc.robot.subsystems.DriveSubsystem;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj2.command.CommandBase;

/** An example command that uses the drive subsystem. */
public class ExampleCommand extends CommandBase {

	private final DriveSubsystem driveSubsystem;

	private final double speed = 0.5;

	/**
	 * Creates a new ExampleCommand.
	 *
	 * @param subsystem The subsystem used by this command.
	 */
	public ExampleCommand(DriveSubsystem driveSubsystem) {
		this.driveSubsystem = driveSubsystem;
		// Use addRequirements() here to declare subsystem dependencies.
		addRequirements(driveSubsystem);
	}

	// Called when the command is initially scheduled.
	@Override
	public void initialize() {
	}

	// Called every time the scheduler runs while the command is scheduled.
	@Override
	public void execute() {
		// Get the input from the controller (x-axis). Goes from -1 to 1
		double controllerInput = Joysticks.xboxController.getRawAxis(0);
		DriverStation.reportWarning("This is a test: " + controllerInput, false);
		// Tell the drive train to turn based on the speed and the controller input
		driveSubsystem.drive(speed * controllerInput, -speed * controllerInput);
	}

	// Called once the command ends or is interrupted.
	@Override
	public void end(boolean interrupted) {
	}

	// Returns true when the command should end.
	@Override
	public boolean isFinished() {
		return false;
	}
}
