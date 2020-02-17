/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import frc.robot.OI;
import frc.robot.subsystems.DriveTrainSubsystem;
import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

/**
 * An example command that uses an example subsystem.
 */
public class driveTeleopCommand extends CommandBase {
  @SuppressWarnings({ "PMD.UnusedPrivateField", "PMD.SingularField" })
  private final DriveTrainSubsystem m_DriveTrain;
  private double ArmSpeed = 0.7;
  private double RoboSpeed = 0.7;

  /**
   * Creates a new ExampleCommand.
   *
   * @param subsystem The subsystem used by this command.
   */
  public driveTeleopCommand(DriveTrainSubsystem subsystem) {
    m_DriveTrain = subsystem;
    // Use addRequirements() here to declare subsystem dependencies.
    addRequirements(subsystem);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {


    double xSpeed = OI.normalize(OI.joystick1.getRawAxis(0), 0.07); //NORMALIZE: is intended to eliminate joystick drift. This gets the raw axis mapped on axis 0 of the controller and stores it as the X Axis.
    double zRotation = OI.normalize(OI.joystick1.getRawAxis(1), 0.07);

    m_DriveTrain.ArcadeDrive(xSpeed, zRotation);

    if(OI.joystick1.getRawButton(1)) //if the first button is pressed, do this. No, you can't use a case switch as a result of the getRawbutton function :(
    {
      m_DriveTrain.setClimber(ArmSpeed, -RoboSpeed);
    }
    else if(OI.joystick1.getRawButton(2)) //I have no idea what these functions do, but you get the idea.
    {
      m_DriveTrain.setClimber(-ArmSpeed, RoboSpeed);
    }
    else
    {
      m_DriveTrain.setClimber(0, 0);
    }
    

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
