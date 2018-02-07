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
import edu.wpi.first.wpilibj.CounterBase.EncodingType;

public class Robot extends IterativeRobot {
	//Motors
	//TL;DR: Double braces after a new object let you run commands on object immediately after the object is constructed.
	//Longer answer: Anonymous default constructor for anonymous class implementing given class
	TalonSRX driveLA = new TalonSRX(12) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveLB = new TalonSRX(13) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveLC = new TalonSRX(14) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRA = new TalonSRX( 1) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRB = new TalonSRX( 2) {{ setNeutralMode(NeutralMode.Brake); }};
	TalonSRX driveRC = new TalonSRX( 3) {{ setNeutralMode(NeutralMode.Brake); }};

	TalonSRX collector = new TalonSRX(0);
	TalonSRX indexer   = new TalonSRX(5);

	//Sensors
	AHRS         navX       = new AHRS(SPI.Port.kMXP, (byte) 50);
	AnalogInput  readSonar  = new AnalogInput(1);
	DigitalInput ballSensor = new DigitalInput(7);
	Encoder      distanceL  = new Encoder(0, 1, false, EncodingType.k1X);
	Encoder      distanceR  = new Encoder(2, 3,  true, EncodingType.k1X);

	//Joysticks
	Joystick driveStick = new Joystick(0);

	//Global Variables
	int autoStep    = 0;
	int autoRoutine = 0;
	Timer autoTimer = new Timer();

	//PID Controllers
	double
		straightP = 0,
		straightI = 0,
		straightD = 0;
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
		enable();
	}};


	double
		turnP = 0,
		turnI = 0,
		turnD = 0;
	PIDCorrection turnRobotCorrection = new PIDCorrection();
	PIDController turnRobot  = new PIDController(
			turnP,
			turnI,
			turnD,
			navX,
			turnRobotCorrection,
			0.020
	) {{
		setInputRange(-180.0, 180.0);
		setOutputRange(-0.6, 0.6);
		setContinuous();
		enable();
	}};



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
	}


	/************
	 * Disabled *
	 ************/

	@Override
	public void disabledInit() {

		autoTimer.start();
	}

	@Override
	public void disabledPeriodic() {
		if (driveStick.getRawButton(2)) {
			distanceR.reset();
			distanceL.reset();
			navX.zeroYaw();
		}
		if (autoTimer.get() > 0.5) {
			SmartDashboardUtil.printToSmartDashboard(this);
			autoTimer.reset();
		}
		if (driveStick.getRawButton(6)) autoRoutine = 1;
		if (driveStick.getRawButton(8)) autoRoutine = 2;
	}



	/**************
	 * Autonomous *
	 **************/

	@Override
	public void autonomousInit() {
		autoStep = 1;
		autoTimer.reset();
		autoTimer.start();
		SmartDashboardUtil.getFromSmartDashboard(this); //force update
		driveStraight.setPID(
			straightP,
			straightI,
			straightD
		);
		turnRobot.setPID(
			turnP,
			turnI,
			turnD
		);
	}

	@Override
	public void autonomousPeriodic() {
		switch(autoRoutine) {
		case 1:
			GoStraightAutonomous.autoGoStraightTest(this);
		case 3:
			DoNothingAutonomous.doNothingRoutine(this);
		}

	}

	/*****************
	 * Tele-Operated *
	 *****************/

	@Override
	public void teleopInit() {
		SmartDashboardUtil.getFromSmartDashboard(this); //force update
		driveStraight.setPID(
			straightP,
			straightI,
			straightD
		);
		turnRobot.setPID(
			turnP,
			turnI,
			turnD
		);
	}

	@Override
	public void teleopPeriodic() {
		double yJoy = -driveStick.getY();
		double xJoy =  driveStick.getX();


		if (driveStick.getRawButton(6)) {
			if (driveStick.getRawButtonPressed(6)) {
				turnRobot.setSetpoint(45);
				resetPIDController(turnRobot);
			}
			driveRobot(
				0.0 + (turnRobotCorrection.correctionValue * 0.3),
				0.0 - (turnRobotCorrection.correctionValue * 0.3)
			);
		}
		else if (driveStick.getRawButton(8)) {
			if (driveStick.getRawButtonPressed(8)) {
				turnRobot.setSetpoint(0);
				resetPIDController(turnRobot);
			}
			driveRobot(
				0.0 + (turnRobotCorrection.correctionValue * 0.3),
				0.0 - (turnRobotCorrection.correctionValue * 0.3)
			);
		}
		else if (driveStick.getTrigger()) {
			if (driveStick.getRawButtonPressed(8)) {
				turnRobot.setSetpoint(0);
				resetPIDController(turnRobot);
			}
			driveRobot(
				0.3 + (driveStraightCorrection.correctionValue * 0.1),
				0.3 - (driveStraightCorrection.correctionValue * 0.1)
			);
		}
		else if (driveStick.getRawButton(2)) {  //turn robot left
			driveRobot(-0.3,0.3);
		}
		else if (driveStick.getRawButton(4)) {
			driveRobot(0.3,-0.3);
		}
		else {
			double left = yJoy + xJoy;
			double right = yJoy - xJoy;
			driveRobot(left, right);
		}

	}


	/******************
	 * Misc Functions *
	 ******************/
	static void resetPIDController(PIDController pid) {
		pid.reset();
		pid.enable();
	}

	void driveRobot(double leftPower, double rightPower) {
		driveLA.set(ControlMode.PercentOutput,  leftPower);
		driveLB.set(ControlMode.PercentOutput,  leftPower);
		driveLC.set(ControlMode.PercentOutput,  leftPower);
		driveRA.set(ControlMode.PercentOutput, rightPower);
		driveRB.set(ControlMode.PercentOutput, rightPower);
		driveRC.set(ControlMode.PercentOutput, rightPower);
	}

}
