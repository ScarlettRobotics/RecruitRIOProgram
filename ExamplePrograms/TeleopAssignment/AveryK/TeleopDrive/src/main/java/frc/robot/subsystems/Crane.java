/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.Talon;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import frc.robot.commands.CraneCommand;


/**
 * An example subsystem.  You can replace me with your own Subsystem.
 */
public class Crane extends Subsystem {
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    TalonSRX turn = new TalonSRX(0); //TODO: Port Number
    SpeedController lift = new Spark(0); //TODO: Port Number


    public Crane() {}

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
        //Remove comment for manual crane control
        //setDefaultCommand(new CraneCommand());
    }

    public void Lift(double speed) {
        lift.set(speed);
    }

    public void Turn(double speed) {
        turn.set(ControlMode.PercentOutput, speed);
    }
    public double turnAngle() {
        return turn.getSelectedSensorPosition();
    }
}