/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

/**
 * RULES FOR MODIFYING
 * 
 * CONTOLLERS/JOYSTICKS can ONLY be accessed and used in disablePeriodic or teleopPeriodic.
 * ALL access to motors MUST be done through methods.
 * 		Direct usage of a TalonSRX.set() method is NOT valid
 */

package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.first.wpilibj.*;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
<<<<<<< HEAD
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.GenericHID.Hand;
=======
import edu.wpi.first.wpilibj.CameraServer;
>>>>>>> refs/heads/master

public class Robot extends TimedRobot {
	// Motors
	// TL;DR: Double braces after a new object let you run commands on object
	// immediately after the object is constructed.
	// Longer answer: Anonymous default constructor for anonymous class implementing
	// given class
	private TalonSRX driveLA = new TalonSRX(0) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX driveLB = new TalonSRX(15) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX driveRA = new TalonSRX(1) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX driveRB = new TalonSRX(14) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX elevator = new TalonSRX(2) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX rollieL = new TalonSRX(3) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX rollieR = new TalonSRX(12) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};
	private TalonSRX wrist = new TalonSRX(4) {
		{
			setNeutralMode(NeutralMode.Brake);
		}
	};

	// Solenoids
	private DoubleSolenoid mouseTrap = new DoubleSolenoid(0, 1);
	private Solenoid shifter = new Solenoid(2);
	private Solenoid grabberClaw = new Solenoid(3);

	// Servos
	private Servo armDeployer = new Servo(0);

	// Sensors
	AHRS navX = new AHRS(SPI.Port.kMXP, (byte) 20);
	Encoder driveLeftEncoder = new Encoder(0, 1, true, EncodingType.k1X);
	Encoder driveRightEncoder = new Encoder(2, 3, true, EncodingType.k1X);
	Encoder elevatorEncoder = new Encoder(4, 5, true, EncodingType.k2X);
	Encoder wristEncoder = new Encoder(8, 9, true, EncodingType.k2X);

	DigitalInput elevatorBottomLimitSwitch = new DigitalInput(6);
	DigitalInput elevatorTopLimitSwitch = new DigitalInput(7);

	// Joysticks
	private Joystick driveController = new Joystick(0);
	private XboxController functionController = new XboxController(1);

	// Global Variables
	int autoStep = 0;
	int autoRoutine = 0;
	int autoLoopCounter = 0;
<<<<<<< HEAD
	Timer autoTimer = new Timer();
=======
    	Timer  autoTimer    = new Timer();
>>>>>>> refs/heads/master

<<<<<<< HEAD
	boolean newPID = true;
=======
	double  turnSum     = 0;
	double  lastOffYaw  = 0;
	boolean newPID      = true;
	double  rampUpPower = 0;
	boolean newTime     = true;
>>>>>>> refs/heads/master

	// Output Storage
	String statusMessage = "We use this to know what the status of the robot is";
	String shifterStatus = "drive";

	double driveOutputLeft = 0.0, driveOutputRight = 0.0, elevatorOutput = 0.0, rolliesOutput = 0.0, wristOutput = 0.0;

	// GameData Storage
	String gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;

	// PID Controllers

	// Drive
	double straightP = 0.08, straightI = 0.0005, straightD = 0;
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

	double turnP = 0.05, turnI = 0, turnD = 0.03;
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

	// Elevator
	double elevP = 0.05, elevI = 0.03, elevD = 0.03;
	PIDCorrection elevatorCorrection = new PIDCorrection();
<<<<<<< HEAD
	PIDController elevatorPID = new PIDController(turnP, turnI, turnD, navX, turnRobotCorrection, 0.020) {
		{
			setInputRange(-200, 10000);
			setOutputRange(-1.0, 1.0);
			setAbsoluteTolerance(100);
			setContinuous(false);
			disable();
		}
	};
=======
	PIDController elevatorPID = new PIDController(
        turnP,
        turnI,
        turnD,
        navX,
        elevatorCorrection,
        0.020
    ) {{
        setInputRange(-200, 10000);
        setOutputRange(-1.0, 1.0);
        setAbsoluteTolerance(100);
        setContinuous(false);
        enable();
	}};
	int turnOnTargetCount = 0;
	public static final double INCHES_TO_ENCTICKS = 110;
	public static final double FEET_TO_ENCTICKS = 12 * INCHES_TO_ENCTICKS;
>>>>>>> refs/heads/master

	/**********
	 * Global *
	 **********/

	@Override
	public void robotInit() {
		driveRA.setInverted(true);
		driveRB.setInverted(true);
		rollieL.setInverted(true);

<<<<<<< HEAD
		
		
=======
		// Uncomment to stream video from the camera.
		// Documentation here on setting modes: https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/669166-using-the-cameraserver-on-the-roborio
		// CameraServer.getInstance().startAutomaticCapture();

>>>>>>> refs/heads/master
		System.out.println("Itsa me, MOERio!");
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

		closeGrabber();
		shiftIntoDrive();
		autoTimer.start();
	}

	@Override
	public void disabledPeriodic() {
		if (driveController.getRawButton(2)) {
			resetEncoders();
			navX.zeroYaw();
		}
		if (driveController.getRawButton(6))
			autoRoutine = 1;
		if (driveController.getRawButton(8))
			autoRoutine = 2;
		if (driveController.getRawButton(10))
			autoRoutine = 3;
		if (driveController.getRawButton(12))
			autoRoutine = 4;
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

		newPID = true;

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
			// GoStraightAutonomous.autoGoStraightTest(this);
			break;
		case 3:
			DoNothingAutonomous.doNothingRoutine(this);
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

		// Driving & Climbing
		double yJoy = -driveController.getY();
		double xJoy = driveController.getX();

		if (shifter.get()) {
			yJoy = -Math.abs(yJoy);
			driveRobot(yJoy, yJoy);
		} else {

			if (driveController.getTrigger()) {
				driveRobot(yJoy, yJoy);

				/*
				 * if(driveController.getTriggerPressed()) {
				 * 
				 * driveStraight.reset(); driveStraight.setSetpoint(navX.getYaw());
				 * driveStraight.enable(); }
				 * 
				 * driveRobot(yJoy + driveStraightCorrection.correctionValue, yJoy -
				 * driveStraightCorrection.correctionValue);
				 */
			} else if (driveController.getRawButton(2)) { // turn robot left
				driveRobot(-0.3, 0.3);
				newPID = true;
			} else if (driveController.getRawButton(4)) {
				driveRobot(0.3, -0.3);
				newPID = true;
			} else {
				double left = yJoy + xJoy;
				double right = yJoy - xJoy;
				driveRobot(left, right);
				newPID = true;
			}

		}

		// shifting
		if (driveController.getRawButton(11)) {
			shiftIntoClimb();
		} else if (driveController.getRawButton(12)) {
			shiftIntoDrive();
		}

		if (driveController.getRawButton(14)) {
			openMouseTrap();
		}

		// XBOX CONTROLLER

		// grabber claw
		if (functionController.getXButton()) {
			closeGrabber();
		} else if (functionController.getYButton()) {
			openGrabber();
		}

		// grabber rotation
		if (functionController.getBumper(Hand.kLeft)) {
			driveWrist(-.4);
		} else if (functionController.getBumper(Hand.kRight)) {
			driveWrist(.4);
		} else {
			driveWrist(0);
		}

		// grabber rollies
		if (functionController.getAButton()) {
			driveRollies(-.5);
		} else if (functionController.getBButton()) {
			driveRollies(.5);
		} else {
			driveRollies(0);
		}

		// elevator
		// this takes whichever Trigger value is greater and runs with that

		driveElevator(functionController.getTriggerAxis(Hand.kLeft) > functionController.getTriggerAxis(Hand.kRight)
				? -functionController.getTriggerAxis(Hand.kLeft)
				: functionController.getTriggerAxis(Hand.kRight));

		// testingservo

		if (functionController.getBackButton()) {
			armDeployer.set(0);
		} else if (functionController.getStartButton()) {
			armDeployer.set(180);
		} else {
			armDeployer.set(90);
		}

	}

	/*******************
	 * Robot Functions *
	 *******************/

	// Drivetrain
	void driveRobot(double leftPower, double rightPower) {
		driveOutputLeft = leftPower;
		driveOutputRight = rightPower;
		driveLA.set(ControlMode.PercentOutput, leftPower);
		driveLB.set(ControlMode.PercentOutput, leftPower);
		driveRA.set(ControlMode.PercentOutput, rightPower);
		driveRB.set(ControlMode.PercentOutput, rightPower);
	}
<<<<<<< HEAD

	// Elevator
	void driveElevator(double power) {
		// if (elevatorBottomLimitSwitch.get()) power = Math.max(power, 0);
		// if (elevatorTopLimitSwitch.get()) power = Math.min(power, 0);

		elevatorOutput = power;
		elevator.set(ControlMode.PercentOutput, power);
	}

	void setElevator(double setPoint) {
		// insert PID with elevatorEncoder
	}

	// Grabber Rotation
	void driveWrist(double power) {
		wrist.set(ControlMode.PercentOutput, power);
	}

	void setWrist(double setPoint) {
		// insert PID with wristEncoder
	}

	// Grabber Claw
	void openGrabber() {
		grabberClaw.set(true);
	}

	void closeGrabber() {
		grabberClaw.set(false);
	}

	// Rollies
	void driveRollies(double power) {
		rollieL.set(ControlMode.PercentOutput, power);
		rollieR.set(ControlMode.PercentOutput, power);
	}

	// Shifting
	void shiftIntoDrive() {
		shifter.set(false);
		shifterStatus = "drive";
	}

	void shiftIntoClimb() {
		shifter.set(true);
		shifterStatus = "climb";
	}

	// mouseTrap
	void openMouseTrap() {
		mouseTrap.set(DoubleSolenoid.Value.kForward);
	}

	void closeMouseTrap() {
		mouseTrap.set(DoubleSolenoid.Value.kReverse);
	}

	void openFlySwatter() {
		openMouseTrap();
	}

	void closeFlySwatter() {
		closeMouseTrap();
	}

	/******************
	 * Misc Functions *
	 ******************/

	// blah blah extension functions blah blah use a better language
	static void resetPIDController(PIDController pid) {
		pid.reset();
		pid.enable();
	}

	public void resetEncoders() {
		driveLeftEncoder.reset();
		driveRightEncoder.reset();
	}

	public double getEncoderMax() {
		return driveLeftEncoder.getRaw() > driveRightEncoder.getRaw() ? driveLeftEncoder.getRaw()
				: driveRightEncoder.getRaw();
	}

	public double getStraightPower() {
		return (driveOutputLeft + driveOutputRight) / 2.0;
	}

	/******************
	 * Vasista's Auto Simplificatorator *
	 ******************/

	// MISC

	public void pause(double seconds) {
		if (autoTimer.get() > seconds) {
			autoStep++;
			autoTimer.reset();
		}
	}

	//

	// DRIVING AUTO

	public static final double INCHES_TO_ENCTICKS = 42.7;
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

	double turnSum = 0;
	double offYaw = 0;
	double lastOffYaw = 0;

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

=======
	
	public void resetEncoders() {
		distanceL.reset();
		distanceR.reset();
	}

	public double getEncoderMax() {
		return Math.abs(distanceL.getRaw()) > Math.abs(distanceR.getRaw()) ? Math.abs(distanceL.getRaw()) : Math.abs(distanceR.getRaw());
	}	
>>>>>>> refs/heads/master
}
