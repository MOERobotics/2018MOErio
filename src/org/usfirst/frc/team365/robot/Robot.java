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

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends TimedRobot {
	// Motors
	// TL;DR: Double braces after a new object let you run commands on object
	// immediately after the object is constructed.
	// Longer answer: Anonymous default constructor for anonymous class implementing
	// given class
	TalonSRX driveLA = new TalonSRX(12) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	TalonSRX driveLB = new TalonSRX(13) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	TalonSRX driveLC = new TalonSRX(14) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	TalonSRX driveRA = new TalonSRX(1) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	TalonSRX driveRB = new TalonSRX(2) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	TalonSRX driveRC = new TalonSRX(3) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};

	TalonSRX collector = new TalonSRX(0);
	TalonSRX indexer = new TalonSRX(5);

	// Sensors
	AHRS navX = new AHRS(SPI.Port.kMXP, (byte) 50);
	AnalogInput readSonar = new AnalogInput(1);
	DigitalInput ballSensor = new DigitalInput(7);
	Encoder distanceL = new Encoder(0, 1, false, EncodingType.k1X);
	Encoder distanceR = new Encoder(2, 3, true, EncodingType.k1X);
	
	Timer autoTimer = new Timer();

	// Joysticks
	Joystick driveStick = new Joystick(0);

	// Global Variables
	int autoStep = 0;
	int autoRoutine = 0;
	

	int autoLoopCounter = 0;

	double turnSum = 0;
	double lastOffYaw = 0;
	boolean newPID = true;
	double rampUpPower = 0;

	// GameData Stuff
	String gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;

	// Output Storage
	String statusMessage = "We use this to know what the status of the robot is";

	double driveOutputLeft = 0.0, driveOutputRight = 0.0;

	// PID Controllers
	double straightP = 0.04, straightI = 0.0003, straightD = .01;
	PIDCorrection driveStraightCorrection = new PIDCorrection();
	PIDController driveStraight = new PIDController(straightP, straightI, straightD, navX, driveStraightCorrection,
			0.020) {
		{
			setInputRange(-180.0, 180.0);
			setOutputRange(-1.0, 1.0);
			setContinuous();
			disable();
		}
	};

	double turnP = 0.04, turnI = 0, turnD = 0.02;
	PIDCorrection turnRobotCorrection = new PIDCorrection();
	PIDController turnRobot = new PIDController(turnP, turnI, turnD, navX, turnRobotCorrection, 0.020) {
		{
			setInputRange(-180.0, 180.0);
			setOutputRange(-0.6, 0.6);
			setAbsoluteTolerance(3);
			setContinuous();
			disable();
		}
	};

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
		statusMessage = "Everything is good!"; // If this isn't still good when you print it again, we did something
												// bad.
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
		gameData = DriverStation.getInstance().getGameSpecificMessage();
		switchLeft = gameData.charAt(0) == 'L';
		scaleLeft = gameData.charAt(0) == 'L';
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
		case 3:
			// Right_Switch_Cube_Plus.run(this);
			break;
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
		SmartDashboardUtil.getFromSmartDashboard(this); // force update
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

	/******************
	 * Vasista's Auto Simplificatorator *
	 ******************/
	public static final double INCHES_TO_ENCTICKS = 110;
	public static final double FEET_TO_ENCTICKS = 12 * INCHES_TO_ENCTICKS;

	int turnOnTargetCount = 0;

	public void goStraight(double ticks, double setPoint, double power) {
		if (newPID) {
			resetEncoders();
			driveStraight.reset();
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			newPID = false;
		}

		if (Math.abs(getEncoderMax()) > ticks) {
			driveRobot(0, 0);
			driveStraight.reset();
			autoTimer.reset();
			autoStep++;
			newPID = true;
		} else {
			driveRobot(power + driveStraightCorrection.correctionValue,
					power - driveStraightCorrection.correctionValue);
		}
	}

	public void turnToAngle(double angle, double maxPower, double tolerance) {
		if (newPID) {
			turnRobot.setAbsoluteTolerance(tolerance);
		}
		turnToAngle(angle, maxPower);
	}

	public void turnToAngle(double angle, double maxPower) {
		if (newPID) {
			resetEncoders();
			turnRobot.reset();
			turnRobot.setSetpoint(angle);
			turnRobot.setOutputRange(-Math.abs(maxPower), Math.abs(maxPower));
			turnRobot.enable();
			turnOnTargetCount = 0;
			newPID = false;
		}

		if (turnRobot.onTarget()) {
			turnOnTargetCount++;
		}

		if (turnOnTargetCount > 3) {
			resetEncoders();
			driveRobot(0, 0);
			turnOnTargetCount = 0;
			turnRobot.reset();
			autoTimer.reset();
			autoStep++;
			newPID = true;
		} else {
			driveRobot(turnRobotCorrection.correctionValue, -turnRobotCorrection.correctionValue);
		}
	}


	public void turnToAngle(double angle) {
		turnToAngle(angle, .6);
	}

	public void resetEncoders() {
		distanceL.reset();
		distanceR.reset();
	}

	public double getEncoderMax() {
		return distanceL.getRaw() > distanceR.getRaw() ? distanceL.getRaw() : distanceR.getRaw();
	}

	public double getStraightPower() {
		return (driveOutputLeft + driveOutputRight) / 2.0;
	}

	public void pause(double seconds) {
		if (autoTimer.get() > seconds) {
			autoStep++;
			autoTimer.reset();
		}
	}

	public void halfTurnLeft(double angle, double power) {
		if (Math.abs(navX.getYaw() - angle) < 3) {
			driveRobot(0, 0);
			autoStep++;
		} else {
			driveRobot(0, power);
		}
	}

	public void halfTurnRight(double angle, double power) {
		if (Math.abs(navX.getYaw() - angle) < 3) {
			driveRobot(0, 0);
			autoStep++;
		} else {
			driveRobot(power, 0);
		}
	}

	/**
	 * 
	 * Below is a modification to goStraight that will ramp up and ramp down the
	 * speed of the bot to minimize tipping. The 4 doubles at the start of
	 * goStraightSS need to be tweaked for goodness - especially the last 2.
	 */

	
	public void goStraightSS(double ticks, double setPoint, double maxPower) {
		double deltaSpeedIncrease = .01;
		double deltaSpeedDecrease = .01;
		double distAwayFromTargetToStartBraking = 24 * INCHES_TO_ENCTICKS;
		double maxOKBrakingPower = .3;

		if (newPID) {
			resetEncoders();
			driveStraight.reset();
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			newPID = false;
		}

		if (Math.abs(getEncoderMax()) > ticks) {
			driveRobot(0, 0);
			driveStraight.reset();
			autoTimer.reset();
			autoStep++;
			newPID = true;
		} else {
			if (ticks - Math.abs(getEncoderMax()) < distAwayFromTargetToStartBraking
					&& getStraightPower() > maxOKBrakingPower) {
				driveRobot(getStraightPower() - deltaSpeedDecrease + driveStraightCorrection.correctionValue,
						getStraightPower() - deltaSpeedDecrease - driveStraightCorrection.correctionValue);
			}

			else if (getStraightPower() < maxPower) {
				driveRobot(getStraightPower() + deltaSpeedIncrease + driveStraightCorrection.correctionValue,
						getStraightPower() + deltaSpeedIncrease - driveStraightCorrection.correctionValue);
			} else {
				driveRobot(maxPower + driveStraightCorrection.correctionValue,
						maxPower - driveStraightCorrection.correctionValue);
			}
		}

	}

	public void autoPIDTurn(double desiredYaw) {
		double currentYaw = navX.getYaw();
		double offYaw = desiredYaw - currentYaw;

		// if driving near 180 need to correct offYaw
		if (offYaw > 180)
			offYaw = offYaw - 360;
		else if (offYaw < -180)
			offYaw = offYaw + 360;

		// initialize values during first loop
		if (newPID) {
			turnSum = 0;
			newPID = false;
			lastOffYaw = offYaw;
			turnOnTargetCount = 0;
			driveRobot(0, 0);
		} else {

			// re-zero the error sum when turn past yaw setpoint
			if (offYaw * lastOffYaw <= 0) {
				turnSum = 0;
			}

			// determine if within yaw tolerance
			if (offYaw > 2 || offYaw < -2) {
				// only add to error sum when close to target value
				if (offYaw < 20 && offYaw > -20) {
					if (offYaw > 0)
						turnSum = turnSum + 0.01;
					else
						turnSum = turnSum - 0.01;
				}
				// calculate new correction value
				double newPower = turnP * offYaw + turnSum + turnD * (offYaw - lastOffYaw);

				// limit output power
				if (newPower > 0.6)
					newPower = 0.6;
				else if (newPower < -0.6)
					newPower = -0.6;
				driveRobot(newPower, -newPower);
			}
			// if robot is within yaw tolerance stop robot and increase onCount
			else {
				turnOnTargetCount++;
				if (turnOnTargetCount > 3) {
					turnOnTargetCount = 0;
					autoStep++;
					driveRobot(0, 0);
					newPID = true;
				}

			}
			lastOffYaw = offYaw;
		}
	}
	
	void autoPIDStraight(double ticks, double setPoint, double power) {
//		double currentYaw = navX.getYaw();
//		double offYaw = setPoint - currentYaw;
		

		
//   initialize needed value for first loop		
		if (newPID) {
//			driveStraight.reset();
			rampUpPower = 0.4;
			newPID = false;
			driveRobot(0,0);
			resetEncoders();
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			
		}
		
		
			else if (getEncoderMax() > ticks) {
			driveRobot(0, 0);
			driveStraight.reset();
//			resetEncoders();
			autoPauseTimer.reset();
//			autoPauseTimer.start();
			autoStep++;
			newPID = true;
		}
		
		else {
			rampUpPower = rampUpPower + 0.06;
			if (rampUpPower < power) {
				driveRobot(rampUpPower + driveStraightCorrection.correctionValue,  rampUpPower - driveStraightCorrection.correctionValue);
				rampUpPower = power;
			}
			else if (getEncoderMax() > ticks - 600) power = 0.4;
			driveRobot(power + driveStraightCorrection.correctionValue,
					power - driveStraightCorrection.correctionValue);
		}
		

	}

}
