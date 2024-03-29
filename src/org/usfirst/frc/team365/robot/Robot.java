/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
//Ahhh, it was fun time! 10 out of 10 would do again! - Arya 
package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.GenericHID.RumbleType;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Robot extends TimedRobot {
	//Motors
	//TL;DR: Double braces after a new object let you run commands on object immediately after the object is constructed.
	//Longer answer: Anonymous default constructor for anonymous class implementing given class
	 TalonSRX driveLA   = new TalonSRX( 0) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveLB   = new TalonSRX(15) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveRA   = new TalonSRX( 1) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX driveRB   = new TalonSRX(14) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX elevator  = new TalonSRX( 2) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX rollLeft  = new TalonSRX(12) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX rollRight = new TalonSRX( 3) {{ setNeutralMode(NeutralMode.Brake); }};
	 TalonSRX wrist     = new TalonSRX( 4) {{ setNeutralMode(NeutralMode.Brake); }};
	 
	 
    //Solenoids
           Solenoid shifter   = new       Solenoid(2);
           Solenoid cubeClaw  = new       Solenoid(3);
     DoubleSolenoid mouseTrap = new DoubleSolenoid(0,1);
     Solenoid lightsL = new Solenoid(4);
     Solenoid lightsR = new Solenoid(5);

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
	DigitalInput rightTape = new DigitalInput(11);
	DigitalInput leftTape = new DigitalInput(10);

	//Joysticks
	private Joystick driveStick = new Joystick(0);
	private XboxController functionStick = new XboxController(1);

	// Global Variables
	int autoStep    = 0;
	int autoRoutine = 0;
	int secondCubeSelect = 1;
	int autoLoopCounter = 0;
    	Timer  autoTimer    = new Timer();
    Timer grabTimer = new Timer();
	double  turnSum     = 0;
	double  lastOffYaw  = 0;
	boolean newStep      = true;
	double  rampUpPower = 0;
	boolean buttonPress = false;
//	boolean newTime     = true;
	boolean engagePTO = false;
	int pov = 0;
	int POVReadOut = functionStick.getPOV(pov);
	boolean autoTape = false;
	// GameData Stuff
	String  gameData = "";
	boolean switchLeft;
	boolean scaleLeft;
	boolean oppSwitchLeft;
	boolean reachedSetting = false;
	boolean topLimitWorks;
	boolean newClimb = true;
	
	boolean  luck = true;
	int rankingPoints = 4;
	
	boolean cam1On = true;
	UsbCamera cam1;
	UsbCamera cam2;
	VideoSink server;

	double[] currentWheelLeft  = new double[3];
	double[] currentWheelRight  = new double[3];
	
	final int HEIGHT_FOR_SWITCH = 1800;
	final int HEIGHT_FOR_SCALE = 5450;
	final int BOTTOM_HEIGHT = 10;
	final int HEIGHT_ABOVE_CUBE = 600;

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
	static final double bottomElevator = -0.6;
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
//		cam1 =CameraServer.getInstance().startAutomaticCapture(0);
//		cam1.setWhiteBalanceAuto();
		cam2 = CameraServer.getInstance().startAutomaticCapture(1);
		cam2.setWhiteBalanceAuto();
/*		server = CameraServer.getInstance().getServer(); */
		CameraServer.getInstance().startAutomaticCapture();
		
		System.out.println("It'sa me, MOERio!");
		SmartDashboardUtil.dashboardInit(this);
		shiftDrive();
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
		newClimb = true;
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
		if (driveStick.getRawButton(11)) autoRoutine = 5;
		if (driveStick.getRawButton(9)) autoRoutine = 6;
		if (driveStick.getRawButton(7)) autoRoutine = 7;
		if(driveStick.getRawButton(5)) autoRoutine = 8;
		if (driveStick.getRawButton(3)) autoRoutine = 9;
		
		if (functionStick.getAButton()) secondCubeSelect = 1;
		if(functionStick.getBButton()) secondCubeSelect = 2;
		if (functionStick.getYButton()) secondCubeSelect = 3;
    	SmartDashboardUtil.dashboardPeriodic(this);
	
	}//yayhappyface

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
		autoTape = false;

		navX.zeroYaw();
		resetEncoders();

		autoTimer.reset();
		autoTimer.start();

		driveStraight.reset();
		turnRobot.reset();
		grabTimer.reset();
		
		if(!(elevatorTopLimitSwitch.get())) topLimitWorks = true;
		else topLimitWorks = false;

//		SmartDashboardUtil.getFromSmartDashboard(this); //force update

	}

	@Override
	public void autonomousPeriodic() {

		autoLoopCounter++;
		switch (autoRoutine) {
		case 1:		/* Starting at the center */
			if (switchLeft) {
				if (secondCubeSelect == 1) {
//					CenterLeftGentleTurns.run(this);
					CenterLeftMAR.run(this);
				}
				else if (secondCubeSelect == 2) {
					LeftSwitch_Exchange.run(this);
				}
				else {
					CenterLeftSwitchScale.run(this);
				}
			}
			else {
				if (secondCubeSelect == 1) {
//					CenterRightSwitchHalf.run(this);
					CenterRightSwitchHalf.run2(this);
				}
				else if (secondCubeSelect == 2) {
					CenterRightSwitchHalf.vault(this);
//					CenterRightSwitchHalf.vault2(this);
				}
				else {
					CenterRightSwitchHalf.centerSwitchAlmostScale(this);
					
				}
			}
			break;
		case 2:		// Starts from right and controls scale
			if (scaleLeft)
				//RightLeftScaleCube.rightStart(this);
				ScaleSwitchCombo.rightFarSideStart(this);
			else
				//Right_Scale_BackUp.run(this);
				ScaleScaleCombo.rightStart(this);
			break;
		case 3:  //starts from left and controls scale
			if (scaleLeft)
				ScaleScaleCombo.leftStart(this);
			else
				//RightLeftScaleCube.leftStart(this);
				ScaleSwitchCombo.leftFarSideStart(this);
			break;
		case 4: //Starts from the right and stays on side
			if (scaleLeft && switchLeft) 
				FarScaleNoCube.rightStart(this);
//				RightLeftScaleCube.rightStart(this);   // change me
			else if (scaleLeft && !switchLeft) 
				GoStraightAutonomous.doubleSwitchRightOnly(this);
				//GoStraightAutonomous.autoOnSideRightSwitchOnly(this);
			else if (!scaleLeft && switchLeft) {
				ScaleScaleCombo.rightStart(this);
			}
			else ScaleScaleCombo.rightStart(this);
			break;
		case 5: //Starts from the left and stays on side
			if (scaleLeft && switchLeft) ScaleScaleCombo.leftStart(this);
			else if (scaleLeft && !switchLeft) ScaleScaleCombo.leftStart(this);
			else if (!scaleLeft && switchLeft) GoStraightAutonomous.doubleSwitchLeftOnly(this); //GoStraightAutonomous.autoOnSideLeftSwitchOnly(this);
			else {
				FarScaleNoCube.leftStart(this);
//				RightLeftScaleCube.leftStart(this);   // change me
			}

			break;
/*		case 6:  //starts from right and does scale with partner
			if (scaleLeft) {
				RightLeftScaleCube.rightStart(this);
			}
			else GoStraightAutonomous.autoScaleLeftStart(this);
			break;
		case 7:  //starts from left and does scale with partner
			if (scaleLeft) {
				GoStraightAutonomous.autoScaleRightStart(this);
			}
			else RightLeftScaleCube.leftStart(this);
			break; */
		case 6: //Starts from the right and stays on side (Switch priority)
			if (!switchLeft && scaleLeft) 
				GoStraightAutonomous.autoOnSideRightSwitchOnly(this);
//				RightLeftScaleCube.rightStart(this);   // change me
			else if (!scaleLeft && !switchLeft) 
				GoStraightAutonomous.autoOnSideRightSwitchScale(this);
			else if (!scaleLeft){
				ScaleScaleCombo.rightStart(this);
			}
			else{
				FarScaleNoCube.rightStart(this);
			}
			break;
		case 7: //Starts from the left and stays on side (Switch priority)
			if (switchLeft && !scaleLeft) GoStraightAutonomous.autoOnSideLeftSwitchOnly(this);
			else if (scaleLeft && switchLeft) GoStraightAutonomous.autoOnSideLeftSwitchScale(this);
			else if (scaleLeft) ScaleScaleCombo.leftStart(this);
			else FarScaleNoCube.leftStart(this);
			break;
		case 8: //Right start (stay on side making room with team)
			if(!scaleLeft) Right_Scale_BackUp.run(this);
			else if (!switchLeft) GoStraightAutonomous.doubleSwitchRightOnly(this); 
			else FarScaleNoCube.rightStart(this);
			break; 
/*			if(!scaleLeft)ScaleSwitchCombo.rightSameSideStart(this);
			else ScaleSwitchCombo.rightFarSideStart(this);
			break; */
		case 9: //Left start (stay on side making room with team)
			if(scaleLeft) Left_Scale_BackUp.run(this);
			else if (switchLeft) GoStraightAutonomous.doubleSwitchLeftOnly(this); //Change
			else FarScaleNoCube.leftStart(this);
			break; 
/*			if(scaleLeft) ScaleSwitchCombo.leftSameSideStart(this);
			else ScaleSwitchCombo.leftFarSideStart(this);
			break;*/ 
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
		lightsR.setPulseDuration(.5);
		lightsL.setPulseDuration(0.5);
		if(functionStick.getX(Hand.kLeft) < -0.2){
			lightsL.set(true);
		}
		else if(functionStick.getX(Hand.kLeft) > 0.2){
			lightsR.set(true);
		}
		else{
			lightsR.set(false);
			lightsL.set(false);
		}
		
		if(functionStick.getY(Hand.kLeft) > .7){
			if(autoTimer.hasPeriodPassed(.3)){
				lightsR.set(!lightsR.get());
				lightsL.set(!lightsL.get());
			}
		}
		double yJoy = -driveStick.getY();
		double xJoy = driveStick.getX();
		if (shifter.get()) {
			if(functionStick.getStickButton(Hand.kLeft)) { 
				climb(); //AutoClimb
			}
			else {
				if(-Math.abs(functionStick.getY(Hand.kLeft)) < -0.2 || (-Math.abs(functionStick.getY(Hand.kRight)) < -0.2))
				{

					//Climbing using the Function Stick
					driveRobot(-Math.abs(functionStick.getY(Hand.kLeft)), -Math.abs(functionStick.getY(Hand.kRight)));
				}
				else {
					driveRobot(0,0);
				}
			}
			/*double left = yJoy + xJoy;
			double right = yJoy - xJoy;
			driveRobot(-Math.abs(left), -Math.abs(right));*/
		} else {
			if (driveStick.getTrigger()) {
				driveRobot(yJoy, yJoy);
			} 
			else if (functionStick.getPOV(0) == 180){
				driveRobot(-0.3, -0.3);
			}
			else if (functionStick.getPOV(0) >= 45 && functionStick.getPOV(0) <= 135 && !engagePTO) { // turn robot left
				driveRobot(0.6, -0.6);
			} else if (functionStick.getPOV(0) >= 225 && functionStick.getPOV(0) <= 315 && !engagePTO) {
				driveRobot(-0.6, 0.6);
			} else {
				double left = yJoy + xJoy;
				double right = yJoy - xJoy;
				driveRobot(left, right);
			}
		}
		
		//Camera
/*		cameraToggle();
		if(On) server.setSource(cam1);
		else server.setSource(cam2); */
		//Shifting
		if(functionStick.getStartButtonPressed()) shiftClimb();
		if(functionStick.getBackButtonPressed()) shiftDrive();
		//Rollers DONE
		if(functionStick.getAButton()) rollIn();
		else if(functionStick.getBButton()) rollOut();
		else driveRoll(0);
		//cubeClaw SAME
		if(functionStick.getXButton()) cubeClawClose();
		else if(functionStick.getYButton()) cubeClawOpen();
		//wrist SAME
		if(functionStick.getBumper(Hand.kLeft)) wristDown();
		else if(functionStick.getBumper(Hand.kRight)) wristUp();
		else backDriveWrist();
		//flySwatter 
		if(functionStick.getStickButton(Hand.kRight) && functionStick.getY(Hand.kRight) <= -0.9) flySwatterShoot();
		else flySwatterClose();
		//Elevator SAME
		if(functionStick.getTriggerAxis(Hand.kLeft) > functionStick.getTriggerAxis(Hand.kRight)) {
			driveElevator((bottomElevator * functionStick.getTriggerAxis(Hand.kLeft)));
		}
		else {
			driveElevator((upperElevator * functionStick.getTriggerAxis(Hand.kRight)));
		}
		//Earthquake
		//Pulses the vibration for last 5 seconds
		if( (DriverStation.getInstance().getMatchTime() <= 30 && DriverStation.getInstance().getMatchTime() >= 28) ||
			(DriverStation.getInstance().getMatchTime() <= 20 && DriverStation.getInstance().getMatchTime() >= 19) ||
			(DriverStation.getInstance().getMatchTime() <= 10 && DriverStation.getInstance().getMatchTime() >= 9)  ||
			(DriverStation.getInstance().getMatchTime() <= 5 && DriverStation.getInstance().getMatchTime() >= 4.5) || 
			(DriverStation.getInstance().getMatchTime() <= 4 && DriverStation.getInstance().getMatchTime() >= 3.5) || 
			(DriverStation.getInstance().getMatchTime() <= 3 && DriverStation.getInstance().getMatchTime() >= 2.5) ||
			(DriverStation.getInstance().getMatchTime() <= 2 && DriverStation.getInstance().getMatchTime() >= 1.5) ||
			(DriverStation.getInstance().getMatchTime() <= 1 && DriverStation.getInstance().getMatchTime() >= 0.1)){
			functionStick.setRumble(RumbleType.kLeftRumble, 1);
			functionStick.setRumble(RumbleType.kRightRumble, 1);
		} 
		else{
			functionStick.setRumble(RumbleType.kLeftRumble, 0);
			functionStick.setRumble(RumbleType.kRightRumble, 0);
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
		double power = -0.70;
		if(functionStick.getStickButton(Hand.kLeft)) power = -1;
		else power = -0.70;
		driveRoll(power);
	}
	
	public void rollOut() {
		double power = 0.4;
		if(functionStick.getStickButton(Hand.kLeft)) power = 1;
		else power = 0.4;
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
	
	public void backDriveWrist() {
		driveWrist(0.06);
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
		engagePTO = false;
	}
	
	public void shiftClimb() {
		shifter.set(true);
		engagePTO = true;
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
		flySwatter.set(0.55);
	}
	
	//Camera
	public void cameraToggle() {
		if(driveStick.getTriggerPressed()) {
			if(cam1On) cam1On = false;
			else cam1On = true;
		}
		
	}
	
	//Auto Climb
	public void tenseUp(double currentAvg) {
		if(currentAvg >= 15) {
			driveRobot(0,0);
			newClimb = false;
		} else {
			driveRobot(-0.4, 0);
			newClimb = true;
		}
	}
	
	public double currentAvg() {
		double driveCurrent = driveLA.getOutputCurrent() + driveLB.getOutputCurrent();
		double currentAvg = driveCurrent / 2;
		return currentAvg;
	}
	
	public void climb() {
		double roll = navX.getRoll();
		double pitch = navX.getPitch();
		double tolerance = 5;
		double climbPower = 1;
/*		if(newClimb) {
			tenseUp(currentAvg());
		} else { */
			if(roll > tolerance) {
				driveRobot(0, -climbPower);
			} else if(roll < -tolerance) {
				driveRobot(-climbPower, 0);
			} else {
				driveRobot(-climbPower, -climbPower);
			}
		//}	
	}
	
	public void lightsOn(){
		lightsR.setPulseDuration(1);
		lightsL.setPulseDuration(1);
	}
}
