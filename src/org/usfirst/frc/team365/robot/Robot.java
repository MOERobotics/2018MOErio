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

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;

public class Robot extends TimedRobot {
	//Motors
	//TL;DR: Double braces after a new object let you run commands on object immediately after the object is constructed.
	//Longer answer: Anonymous default constructor for anonymous class implementing given class
	 TalonSRX driveLA   = new TalonSRX( 0) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveLB   = new TalonSRX(15) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveRA   = new TalonSRX( 1) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveRB   = new TalonSRX(14) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX elevator  = new TalonSRX( 2) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX rollLeft  = new TalonSRX( 3) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX rollRight = new TalonSRX(12) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX wrist     = new TalonSRX( 4) {{ setNeutralMode(NeutralMode.Brake); }};

    //Solenoids
           Solenoid shifter   = new       Solenoid(2);
           Solenoid cubeClaw  = new       Solenoid(3);
     DoubleSolenoid mouseTrap = new DoubleSolenoid(0,1);

	// Servos
	 Servo flySwatter = new Servo(0);

	// Sensors
	AHRS         navX       = new AHRS(SPI.Port.kMXP, (byte) 50);
	Encoder      encoderL  = new Encoder(0, 1, true, EncodingType.k1X);
	Encoder      encoderR  = new Encoder(2, 3, true, EncodingType.k1X);
	Encoder encoderElevator = new Encoder(4, 5, true, EncodingType.k2X);
	Encoder encoderWrist = new Encoder(8, 9, true, EncodingType.k2X);

	DigitalInput elevatorBottomLimitSwitch = new DigitalInput(6);
	DigitalInput elevatorTopLimitSwitch = new DigitalInput(7);

	//Joysticks
	private Joystick driveStick = new Joystick(0);
	private XboxController functionStick = new XboxController(1);

	// Global Variables
	int autoStep    = 0;
	int autoRoutine = 0;

	int autoLoopCounter = 0;
    	Timer  autoTimer    = new Timer();
    Timer grabTimer = new Timer();
	double  turnSum     = 0;
	double  lastOffYaw  = 0;
	boolean newStep      = true;
	double  rampUpPower = 0;
	boolean buttonPress = false;
//	boolean newTime     = true;

	// GameData Stuff
	String  gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;
	boolean reachedSetting = false;
	
	final int HEIGHT_FOR_SWITCH = 1800;
	final int HEIGHT_FOR_SCALE = 5300;
	final int BOTTOM_HEIGHT = 10;

	//Output Storage
	String statusMessage = "We use this to know what the status of the robot is";
	String shifterStatus = "Climb";

	double
		driveOutputLeft  = 0.0,
		driveOutputRight = 0.0,
		elevatorOutput   = 0.0,
        rollOutput    = 0.0,
        wristOutput      = 0.0;


	// PID Controllers
	double straightP = 0.025, straightI = 0.0003, straightD = .0;
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

	double turnP = 0.025, turnI = 0.0001, turnD = 0.02;
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
		setAbsoluteTolerance(3);
		setContinuous();
		enable();
	}};

	// Elevator
	double elevP = 0.05, elevI = 0.03, elevD = 0.03;
	PIDCorrection elevatorCorrection = new PIDCorrection();
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
	static final double upperElevator = 1;
	static final double bottomElevator = -0.4;
	static final double backDrive = 0.1;
	int turnOnTargetCount = 0;
	public static final double INCHES_TO_ENCTICKS = 45;
	public static final double FEET_TO_ENCTICKS = 12 * INCHES_TO_ENCTICKS;

	/**********
	 * Global *
	 **********/

	@Override
	public void robotInit() {
		driveRA.setInverted(true);
		driveRB.setInverted(true);
		rollLeft.setInverted(true);

		// Uncomment to stream video from the camera.
		// Documentation here on setting modes: https://wpilib.screenstepslive.com/s/currentCS/m/vision/l/669166-using-the-cameraserver-on-the-roborio
		CameraServer.getInstance().startAutomaticCapture();

		System.out.println("It'sa me, MOERio!");
		SmartDashboardUtil.dashboardInit(this);
		shiftDrive();
		mouseTrapUp();
		cubeClawClose();
		
	}

	@Override
	public void robotPeriodic() {
//		SmartDashboardUtil.dashboardPeriodic(this);
		// If this isn't still good when you print it again, we did something bad.
		statusMessage = "Everything is good!";
	}


	/************
	 * Disabled *
	 ************/

	@Override
	public void disabledInit() {

		autoTimer.start();
		shiftDrive();
		cubeClawClose();
	//	autoPauseTimer.start();
		top = true;
		bottom = true;
		grabTimer.start();
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
    	
	SmartDashboardUtil.dashboardPeriodic(this);
	
	}

	/**************
	 * Autonomous *
	 **************/

	@Override
	public void autonomousInit() {
		gameData      = DriverStation.getInstance().getGameSpecificMessage();
		switchLeft    = gameData.charAt(0) == 'L';
		scaleLeft     = gameData.charAt(1) == 'L';
		oppSwitchLeft = gameData.charAt(2) == 'L';

		autoLoopCounter = 0;
		autoStep = 1;
		newStep = true;

		navX.zeroYaw();
		resetEncoders();

		autoTimer.reset();
		autoTimer.start();

		driveStraight.reset();
		turnRobot.reset();
		grabTimer.reset();

//		SmartDashboardUtil.getFromSmartDashboard(this); //force update

	}

	@Override
	public void autonomousPeriodic() {

		autoLoopCounter++;
		switch (autoRoutine) {
		case 1:		/* Starting at the center */
			if (switchLeft)
				RightScalefromSide.run(this);
			else
				CenterRightSwitchAutonomous.run(this);
			//GoStraightAutonomous.autoLineSwitch(this);
			break;
		case 2:		/* Starting at the right. */
			if (scaleLeft)
				RightLeftScaleCube.rightStart(this);
			else
				//RightScaleBackUp.run(this);
				//RightScaleGrabCube.run(this);
				RightScalefromSide.run(this);
			break;
		case 3:
//			if (switchLeft)
//				LeftSwitchThenCube.run(this);
//			else 
			if (scaleLeft)
				LeftScaleBackUp.run(this);
			else
				RightLeftScaleCube.leftStart(this);
			break;
		case 4:
			testPID.run(this);
			break;
/*
		case 3:
			RightSwitchThenCube.run(this);
			break;
*/			
/*		case 1:
			CenterRightSwitchAutonomous.run(this);
			break;
		case 2:
			RightSwitchThenCube.run(this);
			break;
		case 3:
			// Right_Switch_Cube_Plus.run(this);
			//break;
		case 4:
			RightScaleSwitch.run(this);
			break;*/
/*		case 5:
			GoStraightAutonomous.autoGoStraightTurnTest(this);
			GoStraightAutonomous.autoScaleTest(this);
			break; 
		default:
			statusMessage = "WARNING: We tried to run an invalid autonomous program!";
			break;
			*/

		}

	}

	/*****************
	 * Tele-Operated *
	 *****************/

	@Override
	public void teleopInit() {
		// force update
//		SmartDashboardUtil.getFromSmartDashboard(this);
	}

	@Override
	public void teleopPeriodic() {
		double yJoy = -driveStick.getY();
		double xJoy = driveStick.getX();
		if (shifter.get()) {
			
			//Climbing using the Function Stick
			driveRobot(-Math.abs(functionStick.getY(Hand.kLeft)), -Math.abs(functionStick.getY(Hand.kRight)));
			
			/*double left = yJoy + xJoy;
			double right = yJoy - xJoy;
			driveRobot(-Math.abs(left), -Math.abs(right));*/
		} else {
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
		//Shifting
		if(driveStick.getRawButton(11)) shiftClimb();
		if(driveStick.getRawButton(12)) shiftDrive();
		//Rollers
		if(functionStick.getAButton()) rollIn();
		else if(functionStick.getBButton()) rollOut();
		else driveRoll(0);
		//cubeClaw
		if(functionStick.getXButton()) cubeClawClose();
		else if(functionStick.getYButton()) cubeClawOpen();
		//wrist
		if(functionStick.getBumper(Hand.kLeft)) wristDown();
		else if(functionStick.getBumper(Hand.kRight)) wristUp();
		else driveWrist(0);
		//flySwatter
		if(functionStick.getBackButton() && functionStick.getStartButton()) flySwatterShoot();
		else flySwatterClose();
		//mouseTrap
		if(driveStick.getRawButton(14)) mouseTrapDown();
		else mouseTrapUp();
		//Elevator
		if(functionStick.getTriggerAxis(Hand.kLeft) > functionStick.getTriggerAxis(Hand.kRight)) {
			driveElevator((bottomElevator * functionStick.getTriggerAxis(Hand.kLeft)));
		}
		else {
			driveElevator((upperElevator * functionStick.getTriggerAxis(Hand.kRight)));
		}
		
		SmartDashboardUtil.dashboardPeriodic(this);
		

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
		driveOutputLeft = leftPower;
		driveOutputRight = rightPower;
		driveLA.set(ControlMode.PercentOutput, leftPower);
		driveLB.set(ControlMode.PercentOutput, leftPower);
		driveRA.set(ControlMode.PercentOutput, rightPower);
		driveRB.set(ControlMode.PercentOutput, rightPower);
	}
	
	public void resetEncoders() {
		encoderL.reset();
		encoderR.reset();
		encoderElevator.reset();
		encoderWrist.reset();
	}
	
	public void resetDriveEncoders() {
		encoderL.reset();
		encoderR.reset();
	}

	public double getEncoderMax() {
		return Math.abs(encoderL.getRaw()) > Math.abs(encoderR.getRaw()) ? Math.abs(encoderL.getRaw()) : Math.abs(encoderR.getRaw());
	}	
	//Elevator Functions (going up or down)
	boolean bottom = true;
	boolean top = true;
	public void driveElevator(double power) {
		double height = encoderElevator.getRaw();
		if(functionStick.getStickButtonPressed(Hand.kLeft) && functionStick.getStickButtonPressed(Hand.kRight)) {
			bottom = false;
			top = false;
		}
		if(elevatorBottomLimitSwitch.get() && bottom) { //Drive positive
			if(power < 0) power = 0;
		}
		else if(elevatorTopLimitSwitch.get() && top) {//Drive negative
			if(power > 0) power = 0;
		}
		else if(power < backDrive && power > -0.005) {
			power = backDrive;
		}
		elevator.set(ControlMode.PercentOutput, power);
		elevatorOutput = power;
	}
	
	//Roller in or out
	public void driveRoll(double power) {
		rollLeft.set(ControlMode.PercentOutput, power);
		rollRight.set(ControlMode.PercentOutput, power);
	}
	
	public void rollIn() {
		driveRoll(-0.70);
	}
	
	public void rollOut() {
		double power = 0.5;
		if(functionStick.getY(Hand.kLeft) > 0.8) power = 1;
		else power = 0.5;
		driveRoll(power);
	}
	
	//wrist down or up 
	public void driveWrist(double power) {
		wrist.set(ControlMode.PercentOutput, power);
	}
	
	public void wristUp() {
		driveWrist(0.8);
	}
	
	public void wristDown() {
		driveWrist(-0.4);
	}
	
	//cubeClaw holds or lets go of cube
	public void cubeClawOpen() {
		cubeClaw.set(true);
	}
	
	public void cubeClawClose() {
		cubeClaw.set(false);
	}
	
	//Shifting to either drive or climb
	public void shiftDrive() {
		shifter.set(false);
	}
	
	public void shiftClimb() {
		shifter.set(true);
	}
	
	//mouseTrap functions
	public void mouseTrapUp() {
		mouseTrap.set(Value.kReverse);
	}
	
	public void mouseTrapDown() {
		mouseTrap.set(Value.kForward);
	}
	
	//flySwatter functions
	public void flySwatterShoot() {
		flySwatter.set(0);
	}
	
	public void flySwatterClose() {
		flySwatter.set(90);
	}	
}
