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
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.CounterBase.EncodingType;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot implements PIDOutput {
	AHRS navX = new AHRS(SPI.Port.kMXP, (byte) 50);

	TalonSRX driveLA = new TalonSRX(12);
	TalonSRX driveLB = new TalonSRX(13);
	TalonSRX driveLC = new TalonSRX(14);
	TalonSRX driveRA = new TalonSRX(1);
	TalonSRX driveRB = new TalonSRX(2);
	TalonSRX driveRC = new TalonSRX(3);

	TalonSRX collector = new TalonSRX(0);
	TalonSRX indexer   = new TalonSRX(5);

	Encoder distanceL = new Encoder(0, 1, false, EncodingType.k1X);
	Encoder distanceR = new Encoder(2, 3, true, EncodingType.k1X);

	Joystick driveStick = new Joystick(0);

	AnalogInput readSonar = new AnalogInput(1);
	DigitalInput ballSensor = new DigitalInput(7);

	Timer autoTimer = new Timer();

	PIDController driveStraight;
	PIDController turnRobot;

	double kProp = 0.08;
	double kInt  = 0.0005;
	double kDer  = 0.05;
	double turnProp = 0.05;
	double startPower;
	double straightSum;
	double lastYaw;
	double lastTime;
	double turnSum;
	double lastOffYaw;
	int autoStep;
	int onCount;
	int autoRoutine = 0;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	@Override
	public void robotInit() {

		driveRA.setInverted(true);
		driveRB.setInverted(true);
		driveRC.setInverted(true);

		driveLA.setNeutralMode(NeutralMode.Brake);
		driveLB.setNeutralMode(NeutralMode.Brake);
		driveLC.setNeutralMode(NeutralMode.Brake);
		driveRA.setNeutralMode(NeutralMode.Brake);
		driveRB.setNeutralMode(NeutralMode.Brake);
		driveRC.setNeutralMode(NeutralMode.Brake);

		driveStraight = new PIDController(
			kProp, 
			kInt, 
			kDer,
			navX, 
			this, 
			0.020
		);
		driveStraight.setInputRange(-180.0, 180.0);
		driveStraight.setOutputRange(-1.0, 1.0);
		driveStraight.setContinuous();

		turnRobot = new PIDController(
			turnProp, 
			0, 
			0,
			navX, 
			this, 
			0.020
		);
		turnRobot.setInputRange(-180.0, 180.0);
		turnRobot.setOutputRange(-0.6, 0.6);
		turnRobot.setContinuous();


	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select
	 * between different autonomous modes using the dashboard. The sendable
	 * chooser code works with the Java SmartDashboard. If you prefer the
	 * LabVIEW Dashboard, remove all of the chooser code and uncomment the
	 * getString line to get the auto name from the text box below the Gyro
	 *
	 * <p>You can add additional auto modes by adding additional comparisons to
	 * the switch structure below with additional strings. If using the
	 * SendableChooser make sure to add them to the chooser code above as well.
	 */
	@Override
	public void disabledInit() {
		if (driveStraight.isEnabled()) {
			driveStraight.disable();
		}
		if (turnRobot.isEnabled()) {
			turnRobot.disable();
		}
		autoTimer.start();
	}

	public void disabledPeriodic() {
		if (driveStick.getRawButton(2)) {
			distanceR.reset();
			distanceL.reset();
			navX.zeroYaw();
		}
		if (autoTimer.get() > 0.5) {
			SmartDashboardPrint.printToSmartDashboard(this);
			autoTimer.reset();
		}
		if (driveStick.getRawButton(6)) autoRoutine = 1;
		if (driveStick.getRawButton(8)) autoRoutine = 2;
	}
	public void autonomousInit() {
		autoStep = 1;
	}

	/**
	 * This function is called periodically during autonomous.
	 */
	@Override
	public void autonomousPeriodic() {
		switch(autoRoutine) {
		case 1:
			GoStraightAutonomous.autoGoStraightTest(this);
		case 2:
			MiddleStartAutonomous.autoMiddleStart(this);
		case 3:
			//this.doNothingRoutine();
			DoNothingAutonomous.doNothingRoutine(this);
		}

	}

	/**
	 * This function is called periodically during operator control.
	 */
	@Override
	public void teleopPeriodic() {

		double yJoy = -driveStick.getY();
		double xJoy = driveStick.getX();


		if (driveStick.getRawButton(5))  {

			if (driveStick.getRawButtonPressed(5))
			{
				straightSum = 0;
				distanceR.reset();

				SmartDashboard.putNumber("pressed", straightSum);
			}

			autoStraight(0,0.4);
		}

		else if (driveStick.getRawButton(6)) {
			autoTurnToAngle(45);
		}
		else if (driveStick.getRawButton(7))  {
			if (driveStick.getRawButtonPressed(7))
			{
				straightSum = 0;
				distanceR.reset();
				//				SmartDashboard.putNumber("pressed", straightSum);
			}
			autoStraight(45,0.5);
		}
		else if (driveStick.getRawButton(8)) {
			autoTurnToAngle(0);
		}
		else if (driveStick.getTrigger()) {  //drive robot straight
			driveRobot(yJoy, yJoy);

		}
		else if (driveStick.getRawButton(2)) {  //turn robot left
			driveRobot(-0.4,0.4);
		}
		else if (driveStick.getRawButton(4)) {
			driveRobot(0.4,-0.4);
		}
		else {
			double left = yJoy + xJoy;
			double right = yJoy - xJoy;
			driveRobot(left, right);
		}
		//		double loopTime = autoTimer.get() - currentTime;
		double newAccel = navX.getWorldLinearAccelX();

		if (autoTimer.get() > 0) {
			SmartDashboardPrint.printToSmartDashboard(this);

		}

	}
	

	/**
	 * This function is called periodically during test mode.
	 */
	@Override
	public void testPeriodic() {
	}

	void driveRobot(double leftP, double rightP) {
		driveLA.set(ControlMode.PercentOutput, leftP);
		driveLB.set(ControlMode.PercentOutput, leftP);
		driveLC.set(ControlMode.PercentOutput, leftP);
		driveRA.set(ControlMode.PercentOutput, rightP);
		driveRB.set(ControlMode.PercentOutput, rightP);
		driveRC.set(ControlMode.PercentOutput, rightP);
	}

	public void pidWrite(double output) {
		double rightSide = startPower - output;
		double leftSide = startPower + output;
		driveRobot(leftSide,rightSide);
	}
	

	void autoStraight(double setYaw, double speed) {
		double currentYaw = navX.getYaw();
		double offYaw = setYaw - currentYaw;

		//	if (offYaw > 0.5 || offYaw < -0.5) {
		if (offYaw < 2 && offYaw > -2) {
			straightSum = straightSum + kInt * offYaw;

		}
		//		}
		//		else offYaw = 0;

		double newPower = kProp * offYaw + straightSum + kDer * (offYaw - lastYaw);
		if (newPower < -1.0) newPower = -1.0;
		else if (newPower > 1.0) newPower = 1.0;
		double leftSide = speed + newPower;
		double rightSide = speed - newPower;
		driveRobot(leftSide, rightSide);
		lastYaw = currentYaw;
	}
	
	

	void autoTurnToAngle(double desiredYaw) {
		double currentYaw = navX.getYaw();
		double offYaw = desiredYaw - currentYaw;

		if (offYaw * lastOffYaw <= 0) {
			turnSum = 0;
		}


		if (offYaw > 1 || offYaw < -1) {
			//			if (offYaw < 20 && offYaw > -20) {
			//				if (offYaw > 0) turnSum = turnSum + 0.01;
			//				else turnSum = turnSum - 0.01;
			//			}
			double newPower = 0.05*offYaw + turnSum + kDer*(offYaw - lastOffYaw);
			if (newPower > 0.6) newPower = 0.6;
			else if (newPower < -0.6) newPower = -0.6;

			driveRobot(newPower, -newPower);

		}
		else {
			driveRobot(0,0);
		}
		lastOffYaw = offYaw;
	}

}
