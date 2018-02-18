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
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;

public class Robot extends IterativeRobot {
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

	// Joysticks
	Joystick driveStick = new Joystick(0);

	// Global Variables
	int autoStep = 0;
	int autoRoutine = 0;
	Timer autoTimer = new Timer();
	Timer autoPauseTimer = new Timer();

	int autoLoopCounter = 0;
	int onCount;
	double kProp = 0.04;
	double kInt = 0.0003;

	double kDer = 0;
	double turnProp = 0.04;
	
	
	
	double PIDCorrection = 0;

	double startPower = .5;
	
	double turnSum = 0;
	double lastOffYaw = 0;
	boolean newPID = true;

	// Output Storage
	String statusMessage = "We use this to know what the status of the robot is";

	double driveOutputLeft = 0.0, driveOutputRight = 0.0;

	// PID Controllers
	double straightP = 0.04, straightI = 0.0003, straightD = .00;
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

	double turnP = 0.04, turnI = 0, turnD = 0.00;
	PIDCorrection turnRobotCorrection = new PIDCorrection();
	PIDController turnRobot = new PIDController(turnP, turnI, turnD, navX, turnRobotCorrection, 0.020) {
		{
			setInputRange(-180.0, 180.0);
			setOutputRange(-1.0, 1.0);
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
	}

	/**************
	 * Autonomous *
	 **************/

	@Override
	public void autonomousInit() {
		autoLoopCounter = 0;

		autoStep = 1;

		navX.zeroYaw();
		resetEncoders();

		autoTimer.reset();
//		autoTimer.start();

		autoPauseTimer.reset();
//		autoPauseTimer.start();
		
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
			Right_Switch_Cube_Plus.run(this);
			break;
		case 4:
			RightScaleSwitch.run(this);
			//CenterLeftSwitchThenCube.run(this);
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
			driveRobot(yJoy,yJoy);
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
		if (Math.abs(getEncoderMax()) > ticks) {
			driveRobot(0, 0);
			driveStraight.reset();
			resetEncoders();
			autoPauseTimer.reset();
			autoPauseTimer.start();
			autoStep++;
		} else {
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			driveRobot(power + driveStraightCorrection.correctionValue,
					power - driveStraightCorrection.correctionValue);
		}
	}
	
	void autoPIDStraight(double ticks, double setPoint, double power) {
		double currentYaw = navX.getYaw();
		double offYaw = setPoint - currentYaw;
		

		
//   initialize needed value for first loop		
		if (newPID) {
			driveStraight.reset();
			newPID = false;
			driveRobot(0,0);
			resetEncoders();
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			
		}
		
		else if (Math.abs(getEncoderMax()) > ticks) {
			driveRobot(0, 0);
			driveStraight.reset();
//			resetEncoders();
			autoPauseTimer.reset();
//			autoPauseTimer.start();
			autoStep++;
			newPID = true;
		}
		
		else {
			driveRobot(power + driveStraightCorrection.correctionValue,
					power - driveStraightCorrection.correctionValue);
		}
		

	}

	public void turnToAngle(double angle, double maxPower) {
		if (turnRobot.onTarget()) {
			turnOnTargetCount++;
		}
		if (turnOnTargetCount > 3) {
			resetEncoders();
			driveRobot(0, 0);
			turnOnTargetCount = 0;
			autoStep++;
			turnRobot.reset();
			autoPauseTimer.reset();
			autoPauseTimer.start();
		} else {
			turnRobot.setSetpoint(angle);
			turnRobot.enable();
			driveRobot(turnRobotCorrection.correctionValue * Math.abs(maxPower),
					-turnRobotCorrection.correctionValue * Math.abs(maxPower));
		}
	}
	
	void autoPIDTurn(double desiredYaw) {
		double currentYaw = navX.getYaw();
		double offYaw = desiredYaw - currentYaw;
		
//   if driving near 180 need to correct offYaw				
		if (offYaw > 180) offYaw = offYaw - 360;
		else if (offYaw < -180) offYaw = offYaw + 360;
		
//   initialize values during first loop		
		if (newPID) {
			turnSum = 0;
			newPID = false;
			lastOffYaw = offYaw;
			onCount = 0;
//			driveRobot(0,0);
		}
		
//   re-zero the error sum when turn past yaw setpoint
		if (offYaw * lastOffYaw <= 0) {
			turnSum = 0;
		}

//  determine if within yaw tolerance
		if (offYaw > 3 || offYaw < -3) {     
//  only add to error sum when close to target value			
			if (offYaw < 20 && offYaw > -20) {   
				if (offYaw > 0) turnSum = turnSum + 0.01;
				else turnSum = turnSum - 0.01;
			}
//  calculate new correction value			
			double newPower = turnP*offYaw + turnSum + turnD*(offYaw - lastOffYaw);
			
//  limit output power
			if (newPower > 0.5) newPower = 0.5;
			else if (newPower < -0.5) newPower = -0.5;
			driveRobot(newPower, -newPower);
		}
//  if robot is within yaw tolerance stop robot and increase onCount
		else {			
			onCount++;
			if (onCount > 3) {
				autoStep++;
				driveRobot(0,0);
				newPID = true;
			}
			
		}
		lastOffYaw = offYaw;
	}
	


	public void turnToAngle(double angle) {
		turnToAngle(angle, 1);
	}

	public void resetEncoders() {
		distanceL.reset();
		distanceR.reset();
	}

	public double getEncoderMax() {
		return (distanceL.getRaw() + distanceR.getRaw()) / 2.0;
	}

	public double getStraightPower() {
		return (driveOutputLeft + driveOutputRight) / 2.;
	}
	
	
	public void pause(double seconds) {
		if(autoPauseTimer.get() > seconds) {
			autoStep++;
			autoPauseTimer.reset();
			autoPauseTimer.start();
		}
	}
	
	/**
	 * 
	 * Below is a modification to goStraight that will ramp up and ramp down the speed of the bot to minimize tipping.
	 * The 4 doubles at the start of goStraightSS need to be tweaked for goodness - especially the last 2.
	 */
	
	public void goStraightSS(double ticks, double setPoint, double power) {
		double deltaSpeedIncrease = .01;
		double deltaSpeedDecrease = .01;
		double distAwayFromTargetToStartBraking = 24 * INCHES_TO_ENCTICKS;
		double maxOKBrakingPower = .3;

		if (Math.abs(getEncoderMax()) > ticks) {
			driveRobot(0, 0);
			resetEncoders();
			driveStraight.reset();
			autoStep++;
		} else {
			driveStraight.setSetpoint(setPoint);
			driveStraight.enable();
			if (Math.abs(getEncoderMax()) - ticks < distAwayFromTargetToStartBraking
					&& getStraightPower() > maxOKBrakingPower) {
				driveRobot(power - deltaSpeedDecrease + driveStraightCorrection.correctionValue,
						power - deltaSpeedDecrease - driveStraightCorrection.correctionValue);
			}

			else if (getStraightPower() < power) {
				driveRobot(power + deltaSpeedIncrease + driveStraightCorrection.correctionValue,
						power + deltaSpeedIncrease - driveStraightCorrection.correctionValue);
			} else {
				driveRobot(power + driveStraightCorrection.correctionValue,
						power - driveStraightCorrection.correctionValue);
			}
		}
	}

	
	
	
	/**
	 * Below is test code to make the robot drive along a circle.
	 * In goCircle, the driveTrainWidth and Radius may need to be tweaked for accuracy
	 */
	
	public enum DriveSide {
		Left, Right
	}
	
	public enum DriveDirection{
		Forward, Reverse
	}

	public void goCircle(double radiusInTicks, DriveSide d, double angle) {
		goCircle(radiusInTicks, d, DriveDirection.Forward, angle);
	}
	
	public void goCircle(double radiusInTicks, DriveSide d, DriveDirection f, double angle) {
		double o = (d == DriveSide.Right)?driveOutputLeft:driveOutputRight;
		goCircle(radiusInTicks, d, f, angle, o);
	}
	
	public void goCircle(double radiusInTicks, DriveSide d, DriveDirection f, double angle, double outerPower) {
		double navxTolerance = 3;
		double driveTrainWidth = 24 * INCHES_TO_ENCTICKS;
		double innerPower = outerPower * (radiusInTicks - driveTrainWidth / 2.)
				/ (radiusInTicks + driveTrainWidth / 2.);

		double left = 0;
		double right = 0;

		left = (d == DriveSide.Right) ? outerPower : innerPower;
		right = (left == outerPower) ? innerPower : outerPower;
		
		if(f == DriveDirection.Reverse) {
			left = -left;
			right = -right;
		}
		
		if (Math.abs(navX.getYaw() - angle) < navxTolerance) {
			resetEncoders();
			driveRobot(0, 0);
			autoStep++;
		} else {
			driveRobot(left, right);
		}
	}
	
	
	
	
}
