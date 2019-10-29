/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.TeleopDriveCommand;
import edu.wpi.first.wpilibj.VictorSP;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class DriveTrain extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  SpeedController frountLeft = new VictorSP(2);
  SpeedController frountRight = new VictorSP(0);
  SpeedController backLeft = new VictorSP(3);
  SpeedController backRight = new VictorSP(1);

  SpeedControllerGroup right = new SpeedControllerGroup(frountLeft, backLeft);
  SpeedControllerGroup left = new SpeedControllerGroup(frountRight, backRight);

  DifferentialDrive drive = new DifferentialDrive(left, right);

  public DriveTrain() {
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    setDefaultCommand(new TeleopDriveCommand());
  }

  public void Move(double left, double right) {
    drive.tankDrive(left, right);
  }
}
