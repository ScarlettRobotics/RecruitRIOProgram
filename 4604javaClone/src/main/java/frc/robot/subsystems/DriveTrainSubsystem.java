/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants.MotorMappings;

public class DriveTrainSubsystem extends SubsystemBase {
  /**
   * Creates a new ExampleSubsystem.
   */
  public DriveTrainSubsystem() { //Init function, not going to toss anything here for now.

  }

  public final SpeedController left_front = new Talon(MotorMappings.left_front); //Uses the motormappings class to create new talons.
  public final SpeedController left_back = new Talon(MotorMappings.left_back);
  public final SpeedController right_front = new Talon(MotorMappings.right_front);
  public final SpeedController right_back = new Talon(MotorMappings.right_back);
  public final SpeedControllerGroup left_motors = new SpeedControllerGroup(left_front, left_back);
  public final SpeedControllerGroup right_motors = new SpeedControllerGroup(right_front, right_back);
  public final DifferentialDrive drive_motors = new DifferentialDrive(left_motors, right_motors);

  public final ADXRS450_Gyro drive_gyro = new ADXRS450_Gyro();
  public final Encoder drive_encoder = new Encoder(7,8, false, EncodingType.k4X);
  public final Encoder drive_encoder2 = new Encoder(9,0, false, EncodingType.k4X);


    
  public void TankDrive(double leftSpeed,  double rightSpeed) { //This is to allow a tank drive that uses a left and right input to do its magic
    drive_motors.tankDrive(leftSpeed, rightSpeed);
  } 

  public void ArcadeDrive(double xSpeed, double zRotation)
  {
    drive_motors.arcadeDrive(xSpeed, zRotation);
  }


  //Climber Code. I'm just lazy so everything is in one subsystem.

  public final SpeedController climbMotorArm = new Talon(5);
  public final SpeedController climbMotorRobot = new Talon(6);
  public final Encoder EncoderArm = new Encoder(0,1);
  public final Encoder EncoderRobot = new Encoder(2,3);
  



  public void stopClimber()
  {
    climbMotorArm.set(0);
    climbMotorRobot.set(0);
  }
  
  public void setClimber(double climbMotorRobotSpeed, double climbMotorArmSpeed) //Note: it's always better to take variables in to set than doing variables inside the subsystem. RobotPreferences can do a lot of the work for you.
  {
    climbMotorRobot.set(climbMotorRobotSpeed);
    climbMotorArm.set(climbMotorArmSpeed);
  }





  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    SmartDashboard.putNumber("Gyro Angle", drive_gyro.getAngle());
  }
}
