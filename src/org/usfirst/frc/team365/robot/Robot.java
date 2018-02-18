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
	private TalonSRX driveLA  = new TalonSRX( 0) {{ setNeutralMode(NeutralMode.Brake); }};
	private TalonSRX driveLB  = new TalonSRX(15) {{ setNeutralMode(NeutralMode.Brake); }};
	private TalonSRX driveRA  = new TalonSRX( 1) {{ setNeutralMode(NeutralMode.Brake); }};
	private TalonSRX driveRB  = new TalonSRX(14) {{ setNeutralMode(NeutralMode.Brake); }};
	private TalonSRX elevator = new TalonSRX( 2) {{ setNeutralMode(NeutralMode.Brake); }};

	//Solenoids
	private DoubleSolenoid shifter = new DoubleSolenoid(0,1);

	//Sensors
	AHRS         navX       = new AHRS(SPI.Port.kMXP, (byte) 20);
	Encoder      distanceL  = new Encoder(0, 1, false, EncodingType.k1X);
	Encoder      distanceR  = new Encoder(2, 3,  true, EncodingType.k1X);

	//Joysticks
	private Joystick driveStick = new Joystick(0);

	//Global Variables
	int    autoStep      = 0;
	int    autoRoutine   = 0;
	int    autoLoopCounter = 0;
	Timer  autoTimer     = new Timer();

	
	
	//Output Storage
	String statusMessage = "We use this to know what the status of the robot is";
	String shifterStatus = DoubleSolenoid.Value.kOff.name();

	double
		driveOutputLeft  = 0.0,
		driveOutputRight = 0.0,
		elevatorOutput   = 0.0;

	//GameData Storage
	String gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;

	//PID Controllers
	double
		straightP = 0.08,
		straightI = 0.0005,
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
		turnP = 0.05,
		turnI = 0,
		turnD = 0.03;
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
		setOutputRange(-1.0, 1.0);
		setAbsoluteTolerance(3);
		setContinuous();
		enable();
	}};



	/**********
	 * Global *
	 **********/

	@Override
	public void robotInit() {
		driveLA.setInverted(true);
		driveLB.setInverted(true);

		System.out.println("Itsa me, MOERio!");
		SmartDashboardUtil.dashboardInit(this);
	}

	@Override
	public void robotPeriodic() {
		SmartDashboardUtil.dashboardPeriodic(this);
		statusMessage = "Everything is good!"; //If this isn't still good when you print it again, we did something bad.
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
		if (driveStick.getRawButton(6)) autoRoutine = 1;
		if (driveStick.getRawButton(8)) autoRoutine = 2;
		if (driveStick.getRawButton(10)) autoRoutine = 3;
		if (driveStick.getRawButton(12)) autoRoutine = 4;
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
		
		SmartDashboardUtil.getFromSmartDashboard(this); //force update

	}

	@Override
	public void autonomousPeriodic() {
		switch(autoRoutine) {
			case 1:
				GoStraightAutonomous.autoGoStraightTest(this);
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
		SmartDashboardUtil.getFromSmartDashboard(this); //force update
	}

	@Override
	public void teleopPeriodic() {
		double yJoy = driveStick.getY();
		double xJoy = driveStick.getX();


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
			double left  = yJoy - xJoy;
			double right = yJoy + xJoy;
			driveRobot(left, right);
		}

	}


	/******************
	 * Misc Functions *
	 ******************/

	//blah blah extension functions blah blah use a better language
	static void resetPIDController(PIDController pid) {
		pid.reset();
		pid.enable();
	}

	void driveRobot(double leftPower, double rightPower) {
		driveOutputLeft  = leftPower;
		driveOutputRight = rightPower;
		driveLA.set(ControlMode.PercentOutput,  leftPower);
		driveLB.set(ControlMode.PercentOutput,  leftPower);
		driveRA.set(ControlMode.PercentOutput, rightPower);
		driveRB.set(ControlMode.PercentOutput, rightPower);
	}

	void driveElevator(double power) {
		elevatorOutput = power;
		elevator.set(ControlMode.PercentOutput, power);
	}

	void shiftIntoDrive() {
		shifter.set(DoubleSolenoid.Value.kForward);
		shifterStatus = DoubleSolenoid.Value.kForward.name();
	}

	void shiftIntoClimb() {
		shifter.set(DoubleSolenoid.Value.kReverse);
		shifterStatus = DoubleSolenoid.Value.kReverse.name();
	}
	
	
	/******************
	 * Vasista's Auto Simplificatorator *
	 ******************/
	public static final double INCHES_TO_ENCTICKS = 42.7;
	public static final double FEET_TO_ENCTICKS = 12 * INCHES_TO_ENCTICKS;

	int turnOnTargetCount = 0;

	public void goStraight(double ticks, double setPoint, double power) {
		if (Math.abs(getEncoderMax())> ticks) {
			driveRobot(0,0);
			driveStraight.reset();
			resetEncoders();
			autoStep++;
		} else {
				driveStraight.setSetpoint(setPoint);
				driveStraight.enable();

			driveRobot(power + driveStraightCorrection.correctionValue, power - driveStraightCorrection.correctionValue);
		}
	}

	public void turnToAngle(double angle, double maxPower) {
		if (turnRobot.onTarget()) {
			turnOnTargetCount++;
		}
		if (turnOnTargetCount > 3) {
			resetEncoders();
			driveRobot(0,0);
			turnOnTargetCount = 0;
			autoStep++;
			turnRobot.reset();
		} else {
			turnRobot.setSetpoint(angle);
			turnRobot.enable();
			driveRobot(turnRobotCorrection.correctionValue * Math.abs(maxPower), -turnRobotCorrection.correctionValue* Math.abs(maxPower));
		}
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

}
