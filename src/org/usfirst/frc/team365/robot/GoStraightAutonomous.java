package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	static void autoGoStraightTurnTest(Robot ourselves) {
	

		switch(ourselves.autoStep) {
		case 1:
			AutoSimplify.goStraight(ourselves, 146 * INCHES, 0, 0.6); //90.901 in
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
			ourselves.driveRobot(0, 0);

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

}
