/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team4733.robot;


import edu.wpi.cscore.VideoSource;
import edu.wpi.first.wpilibj.vision.VisionThread;
import org.opencv.core.Rect;
import org.opencv.imgproc.Imgproc;
import org.usfirst.frc.team4733.robot.commands.*;
import org.usfirst.frc.team4733.robot.subsystems.SBSClaw;
import org.usfirst.frc.team4733.robot.subsystems.SBSDriveTrain;
import org.usfirst.frc.team4733.robot.subsystems.SBSWinch;

import com.scarlettrobotics.frc.epslib.Settings;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.sql.Driver;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
@SuppressWarnings("unused")
public class Robot extends TimedRobot {
	//public static ExampleSubsystem m_subsystem = new ExampleSubsystem();
	public static int frameCnt = 0;
	public static SBSDriveTrain m_driveTrain;
	public static SBSClaw m_claw;
	public static SBSWinch m_winch;
	public static OI m_oi;
	Command CmdNothing;
	char sidedCommand;

	//Command m_autonomousCommand;
	SendableChooser<Command> m_chooser = new SendableChooser<>();
	Command selectedCommand;

	SendableChooser<Integer> m_switchChooser = new SendableChooser<>();

	Command RSwitchAuto;
	Command LSwitchAuto;
	Command finalAutoCommand;

	public boolean reportError = true;



	//Vision Setup:
    UsbCamera camera;
	public static VisionThread visionThread;
	public static double centerX = 0.0;
	//public static boolean visionWorking;
	public final static Object imgLock = new Object();
	public static int IMG_WIDTH = 384;
	public static int IMG_HEIGHT = 288;

	
	public static double getCenterXValue()
    {
        return centerX;
    }
    

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {
		
		//establish Subsystem Linking & make them into codeable objects
		DriverStation.reportError("[Peach] Awakening", reportError);
		
		DriverStation.reportError("Constructing Claw SBS...", reportError);
		m_claw = new SBSClaw();
		DriverStation.reportError("[Claw] Constructed", reportError);
		
		DriverStation.reportError("Constructing Winch SBS...", reportError);
		m_winch = new SBSWinch();
		DriverStation.reportError("[Winch] Constructed", reportError);
		
		DriverStation.reportError("Constructing DriveTrain SBS... (Takes Time)", reportError);
		m_driveTrain = new SBSDriveTrain();
		DriverStation.reportError("[DriveTrain] Constructed", reportError);
		
		DriverStation.reportError("Constructing OI Class...", reportError);
		m_oi = new OI();
		DriverStation.reportError("[OI] Constructed", reportError);
		
		camera = CameraServer.getInstance().startAutomaticCapture(); //Create the camera
		camera.setResolution(IMG_WIDTH, IMG_HEIGHT); //Set Resolution & FPS (Note to Future Code Team: TURN THESE VALUES DOWN to 640 x 480 and 10fps)
		camera.setFPS(23);
		
		DriverStation.reportError("Inverting Motors", reportError);
		setMotorsInvertedOnBoot();
		
		DriverStation.reportError("Configuring Settings", reportError);
		Settings.purge();
		//Settings.putDefault("Auto Mode? ", 1.0);

		CmdNothing = new CmdNothing();
		
		
		//SmartDashboard.putNumber("Auto Mode? ", 1.0);


		m_chooser.addDefault("Default Auto", new CmdNothing()); //Create autonomous
		m_chooser.addObject("FWD Auto", new CmdFwdAuto());
		m_chooser.addObject("Vision Auto", new CmdCubeVision(-0.4, 60));
		m_chooser.addObject("Demo Auto", new CmdRandom());

		m_switchChooser.addDefault("AutoChooser Auto", 1);
		m_switchChooser.addObject(" Center Auto", 2);

		LSwitchAuto = new CmdLSwitchAuto();
		RSwitchAuto = new CmdRSwitchAuto();

		SmartDashboard.putData("Auto mode", m_chooser);
		SmartDashboard.putData("Auto Chooser 2: ", m_switchChooser);

		/*
		DriverStation.reportError("Starting Vision Boot", reportError);
		visionWorking = false;
		visionThread = new VisionThread(camera, new CmdGripPowerCubeWithContour(), pipeline -> {
			frameCnt++;			
			if (!pipeline.filterContoursOutput().isEmpty())
			{
				Rect r = Imgproc.boundingRect(pipeline.filterContoursOutput().get(0));
				synchronized (imgLock) {
					centerX = r.x + (r.width / 2);

					visionWorking = true;
				}
			}
			
				
				//visionWorking = false;
				//DriverStation.reportError("BEEP", reportError);
			
		});
		visionThread.start();
		*/

		
		Robot.m_driveTrain.setPneumatics(4);
		//Start the Compressor
		
		/*****************************************************************************************************************************/
		DriverStation.reportError("***Peach Robot & Subsystems Constructed***", reportError); //Report SBS's are constructed
		//ROBOT SETUP DONE

	
	}

	/**
	 * This function is called once each time the robot enters Disabled mode.
	 * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
	 */
	@Override
	public void disabledInit() {

	}

	@Override
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
		logToDumbDashboard();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString code to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional commands to the
	 * chooser code above (like the commented example) or additional comparisons
	 * to the switch structure below with additional strings & commands.
	 */
	@Override
	public void autonomousInit() {

		/*
		 * String autoSelected = SmartDashboard.getString("Auto Selector",
		 * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
		 * = new MyAutoCommand(); break; case "Default Auto": default:
		 * autonomousCommand = new ExampleCommand(); break; }
		 */


		String FMSGameData = DriverStation.getInstance().getGameSpecificMessage(); //Get Game message from FMS
		
		char sidedCommand = 'L';
		if(FMSGameData.length() > 0)
		{
			if(FMSGameData.charAt(0) == 'R')
			{
				sidedCommand = 'R';
			}
			else
			{
				sidedCommand = 'L';
			}
		}
		

		DriverStation.reportError("Commencing Auto with: " + FMSGameData, reportError);
		
		//double selectedMode = SmartDashboard.getNumber("Auto Mode? ", 1);
		
		DriverStation.reportError("AUTO COMMAND EXECUTING", reportError);
		selectedCommand = m_chooser.getSelected();

		if(m_switchChooser.getSelected() == 1)
		{
		    finalAutoCommand = selectedCommand;
		    DriverStation.reportError("Starting Command Selected...", reportError);
		}
		else if(m_switchChooser.getSelected() == 2)
		{
			if (sidedCommand == 'L')
			{
			    finalAutoCommand = LSwitchAuto;
				DriverStation.reportError("Starting LSwitchAuto", reportError);
			}
			else
			{
			    finalAutoCommand = RSwitchAuto;
			    DriverStation.reportError("Starting RSwitchAuto", reportError);
			}
		}
		else
		{
			DriverStation.reportError("No Valid Input Selected", true); //should NEVER execute
		}

		if(finalAutoCommand != null)
		{
			finalAutoCommand.start();
		}



		
		/*
		// schedule the autonomous command (example)
		if (m_autonomousCommand != null) {
			m_autonomousCommand.start();
		}
		*/
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic()
	{
		Scheduler.getInstance().run();
		logToDumbDashboard();

	}

	@Override
	public void teleopInit()
	{
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		/*
		if (m_autonomousCommand != null) {
			m_autonomousCommand.cancel();
		}
		*/
		
		if(finalAutoCommand != null)
		{
			finalAutoCommand.cancel();
		}


	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() 
	{
		Scheduler.getInstance().run(); //Runs commands
		logToDumbDashboard();

		/*
		//Make a disable command that doesn't involve e-Stopping the robot. 
		if(Robot.m_oi.getXbox().getRawButtonPressed(1) || Robot.m_oi.getAtk3().getRawButtonPressed(8))
		{
			if(CmdNothing != null)
			{
				CmdNothing.start();
			}
		}
		else
		{
			if(CmdNothing != null)
			{
				CmdNothing.cancel();
			}
		}
		*/
	}
	
	
	public void logToDumbDashboard() //Log to smartdashboard/shuffleboard (DumbDashboard = inside joke)
	{
		//SmartDashboard.putNumber("Match Time: ", DriverStation.getInstance().getMatchTime());
		Robot.m_claw.logToDashboardClaw();
		Robot.m_driveTrain.logToDashboardDT();
		Robot.m_oi.logToDashboardOI();
		Robot.m_winch.logToDashboardWinch();
       // SmartDashboard.putNumber("CenterX from Robot", centerX);
        //SmartDashboard.putBoolean("Fiter Empty? ", visionWorking);
	}
	
	private void setMotorsInvertedOnBoot()
	{
		Robot.m_claw.m_clawRight.setInverted(true); //ClawRight is to be inverted to draw claw in/out correctly
		Robot.m_driveTrain.m_LMotors.setInverted(true); //DriveTrain is Inverted
		Robot.m_driveTrain.m_RMotors.setInverted(true);
	}

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}
}