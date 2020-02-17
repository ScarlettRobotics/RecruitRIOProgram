package org.usfirst.frc.team4733.robot.commands;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.command.CommandGroup;


public class CmdRSwitchAuto extends CommandGroup {

    public CmdRSwitchAuto() {
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the arm.


        DriverStation.reportError("Sided Command: ", true);

        DriverStation.reportError("Entering Demo Auto L", true);

        double headingSpeed = -0.6;

        double manouveringSpeed = -0.4;
        double initialAngle = -45;
        double switchTimeOne = 1.5;

        double switchManouveringTimeTwo = 0.7;

        double switchTimeTwo = 1.0;

        double smallClawSpeed = -0.3;
        double smallClawTime = 0.5;

        double elevatorUpSpeed = 0.9;
        double elevatorUpTimeOne = 2.0;

        double clawSpeedIn = 0.9;
        double clawTimeOne = 0.6;



        addSequential(new CmdGyroReset());
        DriverStation.reportError("Executed", true);
        addSequential(new CmdDriveWithHeading(manouveringSpeed, initialAngle, switchTimeOne));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(manouveringSpeed, -initialAngle, switchManouveringTimeTwo));
        addSequential(new CmdDriveWithHeading(headingSpeed, -initialAngle, switchTimeTwo));
        addParallel(new CmdClawSetTimed(smallClawSpeed, smallClawTime));
        addSequential(new CmdElevatorTimed(elevatorUpSpeed, elevatorUpTimeOne));
        addSequential(new CmdClawSetTimed(clawSpeedIn, clawTimeOne));
        addSequential(new CmdClawSetTimed(0, 0.1));
        //One Cube Auto Total Time: 6.1

        double elevatorDownSpeed = -0.8;
        double elevatorDownTime = 1.5;

        double rotateToCubeAngle = -90;
        double rotateToCubeTime = 1.4;

        double toCubeTimeOne = 1.1;

        double centerCubeInTime = 1.5;

        double backFromCubesAngle = 90;
        double backFromCubesTime = 1.7;

        double toSwitchAngle = 7;
        double toSwitchTime = 1.5;

        double elevatorUpTwo = 1.5;
        double clawTimeTwo = 0.7;

        //Two Cube Auto
        addSequential(new CmdElevatorTimed(elevatorDownSpeed, elevatorDownTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(-manouveringSpeed, rotateToCubeAngle, rotateToCubeTime));
        addParallel(new CmdDriveWithHeading(headingSpeed, -rotateToCubeAngle, toCubeTimeOne));
        addSequential(new CmdClawSetTimed(-clawSpeedIn, centerCubeInTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(-headingSpeed, backFromCubesAngle, backFromCubesTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(headingSpeed, toSwitchAngle, toSwitchTime));
        addSequential(new CmdElevatorTimed(elevatorUpSpeed, elevatorUpTwo));
        addSequential(new CmdGyroReset());
        addSequential(new CmdClawSetTimed(clawSpeedIn, clawTimeTwo));
        //Two Cube Auto Time:  10.2 //NOT POSSIBLE WITH TIME INTERVALS | Tot time: 16.3

        //Ambitious Plan for 3 cube auto XD
        addSequential(new CmdElevatorTimed(elevatorDownSpeed, elevatorDownTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(-manouveringSpeed, rotateToCubeAngle, rotateToCubeTime));
        addParallel(new CmdDriveWithHeading(headingSpeed, -rotateToCubeAngle, toCubeTimeOne));
        addSequential(new CmdClawSetTimed(-clawSpeedIn, centerCubeInTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(-headingSpeed, backFromCubesAngle, backFromCubesTime));
        addSequential(new CmdGyroReset());
        addSequential(new CmdDriveWithHeading(headingSpeed, toSwitchAngle, toSwitchTime));
        addSequential(new CmdElevatorTimed(elevatorUpSpeed, elevatorUpTwo));
        addSequential(new CmdGyroReset());
        addSequential(new CmdClawSetTimed(clawSpeedIn, clawTimeTwo));
        //Two Cube Auto Time:  10.2 //NOT POSSIBLE WITH TIME INTERVALS | Tot time: 16.3

    }
}
