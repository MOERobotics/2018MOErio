package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {


	static void autoGoStraightTest(Robot ourselves) {
		PIDController driveStraight           = ourselves.driveStraight;
		PIDController turnRobot               = ourselves.turnRobot;

		PIDCorrection driveStraightCorrection = ourselves.driveStraightCorrection;
		PIDCorrection turnRobotCorrection     = ourselves.turnRobotCorrection;

		switch(ourselves.autoStep) {
			case 1 :
				ourselves.distanceR.reset();
				driveStraight.setSetpoint(0);
				Robot.resetPIDController(driveStraight);
				ourselves.autoStep = 2;
				break;
			case 2:
				if (ourselves.distanceR.getRaw() > 1000) {
					driveStraight.disable();
					ourselves.driveRobot(0,0);
					ourselves.autoStep = 3;
				} else {
					ourselves.driveRobot(
						0.3 + (driveStraightCorrection.correctionValue * 0.1),
						0.3 - (driveStraightCorrection.correctionValue * 0.1)
					);
				}
				break;
			case 3:
				ourselves.driveRobot(0,0);
				break;
			/*
				turnRobot.setSetpoint(90.0);
				turnRobot.setAbsoluteTolerance(2.0);
				ourselves.startPower = 0;
				turnRobot.enable();
				ourselves.onCount = 0;
				ourselves.autoStep = 4;
				break;
			case 4:
				if (turnRobot.onTarget()) {
					ourselves.onCount ++;
					if (ourselves.onCount > 2) {
						turnRobot.disable();
						ourselves.driveRobot(0,0);
						ourselves.autoStep = 5;
					}
				}
				break;
			case 5:
				ourselves.distanceR.reset();
				ourselves.startPower = 0.5;
				driveStraight.setSetpoint(90.0);
				driveStraight.enable();
				ourselves.autoStep = 6;
				break;
			case 6:
				if (ourselves.distanceR.getRaw() > 8000) {
					driveStraight.disable();
					ourselves.driveRobot(0,0);
					ourselves.autoStep = 7;
				}
				break;
			case 7:
				ourselves.driveRobot(0,0);
			*/
		}
	}

}
