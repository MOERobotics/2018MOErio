package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Right_Switch_Cube_Plus {
static void run(Robot robot) {
	switch(robot.autoStep) {
	case 1:
		robot.distanceR.reset ();
		robot.startPower = 0.8;
		/*robot.kProp = SmartDashboard.getNumber("robot.kProp", 0);
		robot.kInt = SmartDashboard.getNumber("robot.kInt", 0);
		robot.kDer = SmartDashboard.getNumber("robot.kDer", 0); */

		//robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer); 
		robot.driveStraight.setSetpoint(0);
		Robot.resetPIDController(robot.driveStraight);
		robot.driveStraight.enable();
		robot.autoStep = 2;
		break;
	case 2:
		if (robot.distanceR.getRaw() >30500) {
			robot.driveStraight.disable();
			robot.autoStep = 3;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.driveStraightCorrection.correctionValue;
			double leftSide = robot.startPower + robot.driveStraightCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;

	case 3:


		robot.turnRobot.setSetpoint(-90.0);
		robot.turnRobot.setAbsoluteTolerance(3.0);
		Robot.resetPIDController(robot.turnRobot);
		robot.turnRobot.reset();
		
		robot.turnRobot.enable();
		robot.startPower = 0;
		robot.onCount = 0;
		robot.autoStep = 4;

		break;
	case 4:
		if (robot.turnRobot.onTarget()) {
			robot.onCount ++;
			if (robot.onCount>1 ) {
				robot.turnRobot.disable();
				robot.autoTimer.reset();
				robot.autoStep = 5;

			}
		}else {
			double rightSide = robot.startPower - robot.turnRobotCorrection.correctionValue;
			double leftSide = robot.startPower + robot.turnRobotCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
			//					robot.onCount=0;
		}

		break;

	case 5: 
		if (robot.autoTimer.get()>1) {


			robot.distanceR.reset();
			robot.autoStep = 6;
		}
		else {
			robot.driveRobot (0.0,0.0);
		}

		break;
	case 6:
		
		/*robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer);*/
		robot.driveStraight.setSetpoint(-90.0);
		robot.startPower = 0.5;

		robot.driveStraight.enable();
		robot.autoStep = 7;
		break;
	case 7: 
		if (robot.distanceR.getRaw() >200.5) {
			robot.driveStraight.disable();
			robot.autoStep = 8;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.driveStraightCorrection.correctionValue;
			double leftSide = robot.startPower + robot.driveStraightCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;
	case 8: 
		robot.driveRobot (0.0,0.0);
		robot.autoStep = 9;
		break;






	case 9:
		robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer);
		robot.driveStraight.setSetpoint(-90.0);
		robot.startPower = -0.4;
		robot.driveStraight.enable();
		robot.distanceR.reset();
		robot.autoStep = 10;
		break;
	case 10:
		if (robot.distanceR.getRaw() <-137.5) {
			robot.driveStraight.disable();
			robot.autoStep = 11;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.driveStraightCorrection.correctionValue;
			double leftSide = robot.startPower + robot.driveStraightCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;

	case 11:	
		robot.turnRobot.setSetpoint(-180.0);
		robot.turnRobot.setAbsoluteTolerance(2.0);
		robot.startPower = 0;
		robot.turnRobot.enable();
		robot.autoStep = 12;
		robot.onCount = 0;
		break;

	case 12:
		if (robot.turnRobot.onTarget()) {
			robot.onCount ++;
			if (robot.onCount>1 ) {
				robot.turnRobot.disable();
				robot.autoStep = 13 ;

			}
		}else {
			double rightSide = robot.startPower - robot.turnRobotCorrection.correctionValue;
			double leftSide = robot.startPower + robot.turnRobotCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
			//					robot.onCount=0;
		}
		break;


	case 13:

		robot.driveRobot (0.0,0.0);
		robot.autoStep = 14;
		break;
	case 14:
		//robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer);
		robot.driveStraight.setSetpoint(-180.0);
		robot.startPower = 0.5;
		robot.driveStraight.enable();
		robot.distanceR.reset();
		robot.autoStep = 15;
		break;


	case 15:
		if (robot.distanceR.getRaw() >8000) {
			robot.driveStraight.disable();
			robot.autoStep = 16;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.driveStraightCorrection.correctionValue;
			double leftSide = robot.startPower + robot.driveStraightCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;

	case 16:


		robot.driveRobot (0.0,0.0);
		robot.autoStep = 17;
		break;

	case 17:	
		robot.turnRobot.setSetpoint(-90.0);
		robot.turnRobot.setAbsoluteTolerance(3.0);
		robot.startPower = 0;
		robot.turnRobot.enable();
		robot.autoStep = 18;
		robot.onCount = 0;
		break;

	case 18:
		if (robot.turnRobot.onTarget()) {
			robot.onCount ++;
			if (robot.onCount>1 ) {
				robot.turnRobot.disable();
				robot.autoStep = 19 ;

			}
		}else {
			double rightSide = robot.startPower - robot.turnRobotCorrection.correctionValue;
			double leftSide = robot.startPower + robot.turnRobotCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
			//					robot.onCount=0;
		}
		break;
	case 19:
		robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer);
		robot.driveStraight.setSetpoint(-90.0);
		robot.startPower = 0.5;
		robot.driveStraight.enable();
		robot.distanceR.reset();
		robot.autoStep = 20;
		break;


	case 20:
		if (robot.distanceR.getRaw() > 5000) {
			robot.driveStraight.disable();
			robot.autoStep = 21;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.driveStraightCorrection.correctionValue;
			double leftSide = robot.startPower + robot.driveStraightCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;
	case 21:	
		robot.turnRobot.setSetpoint(-180.0);
		robot.turnRobot.setAbsoluteTolerance(3.0);
		robot.startPower = 0;
		robot.turnRobot.enable();
		robot.autoStep = 22;
		robot.onCount = 0;
		break;

	case 22:
		if (robot.turnRobot.onTarget()) {
			robot.onCount ++;
			if (robot.onCount>1 ) {
				robot.turnRobot.disable();
				robot.autoStep = 23 ;

			}
		}else {
			double rightSide = robot.startPower - robot.turnRobotCorrection.correctionValue;
			double leftSide = robot.startPower + robot.turnRobotCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
			//					robot.onCount=0;
		}
		break;
	case 23:
		//robot.driveStraight.setPID(robot.kProp, robot.kInt, robot.kDer);
		robot.driveStraight.setSetpoint(-180.0);
		robot.startPower = 0.5;
		robot.driveStraight.enable();
		robot.distanceR.reset();
		robot.autoStep = 24;
		break;


	case 24:
		if (robot.distanceR.getRaw() >1500) {
			robot.driveStraight.disable();
			robot.autoStep = 25;
			robot.driveRobot (0.0,0.0);
		} else {
			double rightSide = robot.startPower - robot.turnRobotCorrection.correctionValue;
			double leftSide = robot.startPower + robot.turnRobotCorrection.correctionValue;
			robot.driveRobot(leftSide,rightSide);
		}
		break;

	case 25:
		robot.driveRobot (0.0,0.0);

		break;
	}



}
}
