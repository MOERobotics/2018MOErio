/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.*;

public class Robot extends TimedRobot {
	// Motors
	// TL;DR: Double braces after a new object let you run commands on object
	// immediately after the object is constructed.
	// Longer answer: Anonymous default constructor for anonymous class implementing
	// given class
	TalonSRX driveLA = new TalonSRX(12) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveLB = new TalonSRX(13) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveLC = new TalonSRX(14) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRA = new TalonSRX( 1) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRB = new TalonSRX( 2) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRC = new TalonSRX( 3) {{ setNeutralMode(NeutralMode.Brake); }};

	TalonSRX collector = new TalonSRX(0);
	TalonSRX indexer   = new TalonSRX(5);

	// Sensors
	AHRS         navX       = new AHRS(SPI.Port.kMXP, (byte) 50);
	AnalogInput  readSonar  = new AnalogInput(1);
	DigitalInput ballSensor = new DigitalInput(7);
	Encoder      distanceL  = new Encoder(0, 1, false, EncodingType.k1X);
	Encoder      distanceR  = new Encoder(2, 3, true, EncodingType.k1X);
	
	Timer autoTimer = new Timer();

	// Joysticks
	Joystick driveStick = new Joystick(0);

	// Global Variables
	int autoStep    = 0;
	int autoRoutine = 0;
	

	int autoLoopCounter = 0;

	double  turnSum     = 0;
	double  lastOffYaw  = 0;
	boolean newPID      = true;
	double  rampUpPower = 0;

	// GameData Stuff
	String  gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;

	// Output Storage
	String statusMessage = "We use this to know what the status of the robot is";

	double driveOutputLeft = 0.0, driveOutputRight = 0.0;

	// PID Controllers
	double straightP = 0.04, straightI = 0.0003, straightD = .01;
	PIDCorrection driveStraightCorrection = new PIDCorrection();
	PIDController driveStraight = new PIDController(
		straightP,
		straightI,
		straightD,
		navX,
		driveStraightCorrection,
		0.020
	) {{
		setInputRange(-180.0, 180.0);
		setOutputRange(-1.0, 1.0);
		setContinuous();
		disable();
	}};

	double turnP = 0.04, turnI = 0, turnD = 0.02;
	PIDCorrection turnRobotCorrection = new PIDCorrection();
	PIDController turnRobot = new PIDController(
		turnP,
		turnI,
		turnD,
		navX,
		turnRobotCorrection,
		0.020
	) {{
		setInputRange(-180.0, 180.0);
		setOutputRange(-0.6, 0.6);
		setAbsoluteTolerance(3);
		setContinuous();
		disable();
	}};
	int turnOnTargetCount = 0;

	/**********
	 * Global *
	 **********/

	@Override
	public void robotInit() {
		driveRA.setInverted(true);
		driveRB.setInverted(true);
		driveRC.setInverted(true);

		SmartDashboardUtil.dashboardInit(this);
	}

	@Override
	public void robotPeriodic() {
		SmartDashboardUtil.dashboardPeriodic(this);
		// If this isn't still good when you print it again, we did something bad.
		statusMessage = "Everything is good!";
	}

	/************
	 * Disabled *
	 ************/

	@Override
	public void disabledInit() {

		autoTimer.start();
	//	autoPauseTimer.start();
	}

	@Override
	public void disabledPeriodic() {
		if (driveStick.getRawButton(2)) {
			resetEncoders();
			navX.zeroYaw();
		}

		if (driveStick.getRawButton(6))
			autoRoutine = 1;
		if (driveStick.getRawButton(8))
			autoRoutine = 2;
		if (driveStick.getRawButton(10))
			autoRoutine = 3;
		if (driveStick.getRawButton(12))
			autoRoutine = 4;
		if (driveStick.getRawButton(11))
			autoRoutine = 5;
	}

	/**************
	 * Autonomous *
	 **************/

	@Override
	public void autonomousInit() {
		gameData      = DriverStation.getInstance().getGameSpecificMessage();
		switchLeft    = gameData.charAt(0) == 'L';
		scaleLeft     = gameData.charAt(0) == 'L';
		oppSwitchLeft = gameData.charAt(0) == 'L';

		autoLoopCounter = 0;

		autoStep = 1;

		navX.zeroYaw();
		resetEncoders();

		autoTimer.reset();
		autoTimer.start();

		driveStraight.reset();
		turnRobot.reset();

		SmartDashboardUtil.getFromSmartDashboard(this); // force update

	}

	@Override
	public void autonomousPeriodic() {

		autoLoopCounter++;
		switch (autoRoutine) {
		case 1:
			RightLeftScaleCube.run(this);
			break;
		case 2:
			RightSwitchThenCube.run(this);
			break;
		//case 3:
			// Right_Switch_Cube_Plus.run(this);
			//break;
		case 4:
			RightScaleSwitch.run(this);
			break;
		case 5:
			GoStraightAutonomous.autoGoStraightTurnTest(this);
			break;
		default:
			statusMessage = "WARNING: We tried to run an invalid autonomous program!";
			break;

		}

	}

	/*****************
	 * Tele-Operated *
	 *****************/

	@Override
	public void teleopInit() {
		// force update
		SmartDashboardUtil.getFromSmartDashboard(this);
	}

	@Override
	public void teleopPeriodic() {
		double yJoy = -driveStick.getY();
		double xJoy = driveStick.getX();

		if (driveStick.getTrigger()) {
			driveRobot(yJoy, yJoy);
		} else if (driveStick.getRawButton(2)) { // turn robot left
			driveRobot(-0.3, 0.3);
		} else if (driveStick.getRawButton(4)) {
			driveRobot(0.3, -0.3);
		} else {
			double left = yJoy + xJoy;
			double right = yJoy - xJoy;
			driveRobot(left, right);
		}

	}

	/******************
	 * Misc Functions *
	 ******************/

	// blah blah extension functions blah blah use a better language
	static void resetPIDController(PIDController pid) {
		pid.reset();
		pid.enable();
	}

	void driveRobot(double leftPower, double rightPower) {
		driveOutputLeft = leftPower;
		driveOutputRight = rightPower;
		driveLA.set(ControlMode.PercentOutput, leftPower);
		driveLB.set(ControlMode.PercentOutput, leftPower);
		driveLC.set(ControlMode.PercentOutput, leftPower);
		driveRA.set(ControlMode.PercentOutput, rightPower);
		driveRB.set(ControlMode.PercentOutput, rightPower);
		driveRC.set(ControlMode.PercentOutput, rightPower);
	}
	
	public void resetEncoders() {
		distanceL.reset();
		distanceR.reset();
	}

	public double getEncoderMax() {
		return distanceL.getRaw() > distanceR.getRaw() ? distanceL.getRaw() : distanceR.getRaw();
	}	
}
