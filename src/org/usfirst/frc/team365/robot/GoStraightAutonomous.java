package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {


	static void autoGoStraightTest(Robot ourselves) {
		int autoStep = ourselves.autoStep;
		PIDController driveStraight = ourselves.driveStraight;
		PIDController turnRobot = ourselves.turnRobot;
		switch(autoStep) {

		case 1 :
			ourselves.distanceR.reset();
			ourselves.startPower = 0.5;
			double kProp = SmartDashboard.getNumber("kProp", 0);
			double kInt = SmartDashboard.getNumber("kInt", 0);
			double kDer = SmartDashboard.getNumber("kDer", 0);
			driveStraight.setPID(kProp, kInt, kDer);
			driveStraight.setSetpoint(0);
			driveStraight.enable();
			autoStep = 2;
			break;
		case 2:
			if (ourselves.distanceR.getRaw() > 10000) {
				driveStraight.disable();
				ourselves.driveRobot(0,0);
				autoStep = 3;
			}
			break;
		case 3:
			turnRobot.setSetpoint(90.0);
			turnRobot.setAbsoluteTolerance(2.0);
			ourselves.startPower = 0;
			turnRobot.enable();
			ourselves.onCount = 0;
			autoStep = 4;
			break;
		case 4:
			if (turnRobot.onTarget()) {
				ourselves.onCount ++;
				if (ourselves.onCount > 2) {
					turnRobot.disable();
					ourselves.driveRobot(0,0);
					autoStep = 5;
				}
			}
			break;
		case 5:
			ourselves.distanceR.reset();
			ourselves.startPower = 0.5;
			driveStraight.setSetpoint(90.0);
			driveStraight.enable();
			autoStep = 6;
			break;
		case 6:
			if (ourselves.distanceR.getRaw() > 8000) {
				driveStraight.disable();
				ourselves.driveRobot(0,0);
				autoStep = 7;
			}
			break;
		case 7:	

			ourselves.driveRobot(0,0);


		}
	}

}
