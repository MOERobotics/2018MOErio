package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	static void autoGoStraightTurnTestRight(Robot ourselves) {
	

		switch(ourselves.autoStep) {
		case 1:
			AutoSimplify.goStraight(ourselves, 135 * INCHES, 0, 0.6); //90.901 in
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
//			AutoSimplify.autoPIDStraight(ourselves, 146 * INCHES, 0, 0.6); //118.182 in
			break;
		case 2:
//			ourselves.turnToAngle(90.0, 0.7);
			AutoSimplify.autoPIDTurn(ourselves, -90);
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
			break;
		case 3:
//			ourselves.goStraight(6.061 * FEET, 90, 0.6); //72.723 in
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
			AutoSimplify.autoPIDStraight(ourselves, 6 * INCHES, -90, 0.5); //54.545 in
			break;
		case 4:
			AutoSimplify.launchCube(ourselves);
			AutoSimplify.raiseElevator(ourselves, 1800);
//			AutoSimplify.autoPIDStraight(ourselves, 1*FEET, -90, 0.4);
			break;
		case 5:
			AutoSimplify.goStraight(ourselves, 6*INCHES, -90, -0.4);
//			ourselves.driveRobot(0, 0);
			AutoSimplify.raiseElevator(ourselves, 1800);
			break;
		case 6:
			AutoSimplify.autoPIDTurn(ourselves, 0);
			AutoSimplify.lowerElevator(ourselves, 600);
			break;
		case 7:
			AutoSimplify.goStraight(ourselves, 3*FEET, 0, 0.5);
			AutoSimplify.lowerElevator(ourselves, 600);
			break;
		case 8:
			AutoSimplify.halfTurnLeft(ourselves, -90, 0.8);
			AutoSimplify.lowerElevator(ourselves, 600);
			break;
		case 9:
			AutoSimplify.autoPIDTurn(ourselves, -180);
			AutoSimplify.lowerElevator(ourselves, 600);
			break;
		case 10:
			AutoSimplify.openGrabber(ourselves);
			AutoSimplify.lowerElevator(ourselves, ourselves.BOTTOM_HEIGHT);
			break;
		case 11:
			AutoSimplify.downElevatorStep(ourselves, ourselves.BOTTOM_HEIGHT);
			break;
		case 12:
			AutoSimplify.grabCube(ourselves);
			ourselves.driveRobot(0, 0);
			break;
		case 13:
			ourselves.driveRobot(0, 0);
			break;
			
			
		}	
	
/*		
		if (ourselves.autoTimer.get() > 0.5) {
			SmartDashboard.putNumber("straightCorrection", ourselves.driveStraightCorrection.correctionValue);
			SmartDashboard.putNumber("turnCorrection", ourselves.turnRobotCorrection.correctionValue);
			ourselves.autoTimer.reset();
		}*/
	}
	
	static void autoGoStraightTurnTestLeft(Robot ourselves) {
		

		switch(ourselves.autoStep) {
		case 1:
			AutoSimplify.goStraight(ourselves, 135 * INCHES, 0, 0.6); //90.901 in
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
//			AutoSimplify.autoPIDStraight(ourselves, 146 * INCHES, 0, 0.6); //118.182 in
			break;
		case 2:
//			ourselves.turnToAngle(90.0, 0.7);
			AutoSimplify.autoPIDTurn(ourselves, 90);
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
			break;
		case 3:
//			ourselves.goStraight(6.061 * FEET, 90, 0.6); //72.723 in
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.deployGrabber(ourselves);
			AutoSimplify.autoPIDStraight(ourselves, 6 * INCHES, 90, 0.5); //54.545 in
			break;
		case 4:
			AutoSimplify.launchCube(ourselves);
			AutoSimplify.raiseElevator(ourselves, 1800);
			AutoSimplify.autoPIDStraight(ourselves, 1*FEET, 90, 0.4);
			break;
		case 5:
			ourselves.driveRobot(0, 0);
			AutoSimplify.raiseElevator(ourselves, 1800);
			break;
		}
/*		
		if (ourselves.autoTimer.get() > 0.5) {
			SmartDashboard.putNumber("straightCorrection", ourselves.driveStraightCorrection.correctionValue);
			SmartDashboard.putNumber("turnCorrection", ourselves.turnRobotCorrection.correctionValue);
			ourselves.autoTimer.reset();
		}*/
	}
	
	static void autoScaleTest(Robot ourselves) {
		
		switch(ourselves.autoStep) {
	case 1:
	    AutoSimplify.raiseElevator(ourselves, 5300);
		AutoSimplify.deployGrabber(ourselves);
		AutoSimplify.goStraight(ourselves, 23.0 * FEET, 0, 0.6); //
//		AutoSimplify.autoPIDStraight(ourselves, 23 * FEET, 0, 0.7); //
		break;
	case 2:
//		ourselves.turnToAngle(90.0, 0.7);
		AutoSimplify.raiseElevator(ourselves, 5300);
		AutoSimplify.deployGrabber(ourselves);
		AutoSimplify.autoPIDTurn(ourselves, -25);
		break;
	case 3:
		AutoSimplify.raiseElevator(ourselves, 5300);
		AutoSimplify.deployGrabber(ourselves);
		ourselves.driveRobot(0, 0);
//		ourselves.goStraight(6.061 * FEET, 90, 0.6); //72.723 in
//		ourselves.autoPIDStraight(4.545 * FEET, 90, 0.6); //54.545 in
		break;
	case 4:
		AutoSimplify.raiseElevator(ourselves, 5300);
		AutoSimplify.launchCube(ourselves);
		ourselves.driveRobot(0, 0);
	case 5:
		AutoSimplify.lowerElevator(ourselves, 10);
		AutoSimplify.autoPIDStraight(ourselves, 40 * INCHES, 40, -0.4);
	case 6:
		AutoSimplify.lowerElevator(ourselves, 10);
		ourselves.driveRobot(0, 0);

		}
	}
	static void autoLine(Robot us){
			/**
			 * Forward: 210 inches
			 * Left (-90)
			 * Forward: 185 inches
			 * Right (0)
			 * Forward: 39 inches 
			 * Backwards (0)
			 * Backward: 37 inches
			 * Turn (180)
			 * Forward: 14 inches
			 * 
			 */
			
			switch (us.autoStep)
			{
			case 1: 
				AutoSimplify.goStraight(us, 16.666 * FEET, 0, 0.7); //206.173 in
				//AutoSimplify.deployGrabber(us);
				break;
			case 2:
				us.driveRobot(0,0);
				break;
				}
	}
	static void autoLineSwitch(Robot us){
		switch(us.autoStep){
		case 1:
			AutoSimplify.goStraight(us, 2 * FEET, 0, .4);
			break;
		case 2:
			//AutoSimplify.deployGrabber(robot);
			//AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, 45);
			break;
		case 3:
			//AutoSimplify.deployGrabber(robot);
			//AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 72.7 * INCHES, 45, .4);
			break;
		case 4:
			//AutoSimplify.deployGrabber(robot);
			//AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us,  180);
			break;
		case 5:
			AutoSimplify.goStraight(us, 5*INCHES, 180, -0.4);
			break;
		case 6:
			us.driveRobot(0,0);
			break;
		}
	}

}
