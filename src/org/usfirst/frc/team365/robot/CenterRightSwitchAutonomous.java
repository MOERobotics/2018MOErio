package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterRightSwitchAutonomous {
	static double startPower = 0;
	static int onCount = 0;
	static void run(Robot ourselves) {
			switch(ourselves.autoStep) {
			case 1:
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				startPower = 0.5;
				Robot.resetPIDController(ourselves.driveStraight);
				ourselves.driveStraight.setSetpoint(0);
				ourselves.driveStraight.enable();
				ourselves.autoStep = 2;
				break;
			case 2:
				if(ourselves.distanceR.getRaw() > 2640) {
					ourselves.driveStraight.disable();
					ourselves.driveRobot(0.0,0.0);
					ourselves.autoStep = 3;
				} else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 3:
				ourselves.turnRobot.setSetpoint(45);
				ourselves.turnRobot.setAbsoluteTolerance(2.0);
				startPower = 0;
				ourselves.turnRobot.enable();
				ourselves.autoStep = 4;
				break;
			case 4:
				if(ourselves.turnRobot.onTarget()) {
					onCount++;
					if(onCount > 0) {
						ourselves.turnRobot.disable();
						ourselves.autoStep = 5;
					}
				}
				else {
					double rightSide = startPower - ourselves.turnRobotCorrection.correctionValue;
					double leftSide = startPower + ourselves.turnRobotCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 5:
				startPower = .5;
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				ourselves.driveStraight.setSetpoint(45);
				ourselves.driveStraight.enable();
				ourselves.autoStep = 6;
				break;
			case 6:
				if(ourselves.distanceR.getRaw() > 8000) {
					ourselves.driveStraight.disable();
					ourselves.autoStep = 7;
				}
				else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 7:
				ourselves.turnRobot.setSetpoint(0);
				ourselves.turnRobot.setAbsoluteTolerance(2.0);
				startPower = 0;
				onCount = 0;
				ourselves.turnRobot.enable();
				ourselves.autoStep = 8;
				break;
			case 8:
				if(ourselves.turnRobot.onTarget()) {
					onCount++;
					if(onCount > 0) {
						ourselves.turnRobot.disable();
						ourselves.autoStep = 9;
					}
				}
				else {
					double rightSide = startPower - ourselves.turnRobotCorrection.correctionValue;
					double leftSide = startPower + ourselves.turnRobotCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 9:
				startPower = .4;
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				ourselves.driveStraight.setSetpoint(0);
				ourselves.driveStraight.enable();
				ourselves.autoStep = 10;
				break;
			case 10:
				if(ourselves.distanceR.getRaw() > 1000) {
					ourselves.driveStraight.disable();
					ourselves.driveRobot(0.0,0.0);
					ourselves.autoTimer.reset();
					ourselves.autoStep = 11;
				}
				else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 11:
				startPower = 0.0;
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				if (ourselves.autoTimer.get() > 1.0) {
					ourselves.autoStep = 12;
				}
				else {
					ourselves.driveRobot(0.0,0.0);
				}
				break;
			case 12:
				startPower = -.4;
				ourselves.driveStraight.enable();
				if (ourselves.distanceR.getRaw() < -5300) {
					ourselves.driveStraight.disable();
					ourselves.driveRobot(0.0,0.0);
					ourselves.autoStep = 13;
				}
				else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 13:
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				ourselves.turnRobot.setSetpoint(-90);
				ourselves.turnRobot.setAbsoluteTolerance(2.0);
				startPower = 0;
				ourselves.turnRobot.enable();
				ourselves.autoStep = 14;
				break;
			case 14:
				if(ourselves.turnRobot.onTarget()) {
					onCount++;
					if(onCount > 0) {
						ourselves.turnRobot.disable();
						ourselves.autoStep = 15;
					}
				}
				else {
					double rightSide = startPower - ourselves.turnRobotCorrection.correctionValue;
					double leftSide = startPower + ourselves.turnRobotCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 15:
				startPower = .5;
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				ourselves.driveStraight.setSetpoint(-90);
				ourselves.driveStraight.enable();
				ourselves.autoStep = 16;
				break;
			case 16:
				if(ourselves.distanceR.getRaw() > 6400) {
					ourselves.driveStraight.disable();
					ourselves.driveRobot(0.0,0.0);
					ourselves.autoStep = 17;
				}
				else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 17:
				ourselves.turnRobot.setSetpoint(0);
				ourselves.turnRobot.setAbsoluteTolerance(2.0);
				startPower = 0;
				onCount = 0;
				ourselves.turnRobot.enable();
				ourselves.autoStep = 18;
				break;
			case 18:
				if(ourselves.turnRobot.onTarget()) {
					onCount++;
					if(onCount > 0) {
						ourselves.turnRobot.disable();
						ourselves.autoStep = 19;
					}
				}
				else {
					double rightSide = startPower - ourselves.turnRobotCorrection.correctionValue;
					double leftSide = startPower + ourselves.turnRobotCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 19:
				startPower = .4;
				ourselves.distanceR.reset();
				ourselves.distanceL.reset();
				ourselves.driveStraight.setSetpoint(0);
				ourselves.driveStraight.enable();
				ourselves.autoStep = 20;
				break;
			case 20:
				if(ourselves.distanceR.getRaw() > 1000) {
					ourselves.driveStraight.disable();
					ourselves.driveRobot(0.0,0.0);
					ourselves.autoStep = 21;
				}
				else {
					double rightSide = startPower - ourselves.driveStraightCorrection.correctionValue;
					double leftSide  = startPower + ourselves.driveStraightCorrection.correctionValue;
					ourselves.driveRobot(leftSide,rightSide);
				}
				break;
			case 21:
				ourselves.driveRobot(0.0,0.0);
				break;
			}
		}
	}