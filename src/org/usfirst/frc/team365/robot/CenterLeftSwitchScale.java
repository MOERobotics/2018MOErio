package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CenterLeftSwitchScale {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
		int side = 1;
			
		if (us.scaleLeft) 
			side = -1; 
		
		switch(us.autoStep) {
		
		case 1:
			
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.halfTurnLeft(us, -45, 0.8);
			break;
			
		case 2:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 57 * INCHES, -45, 0.5); 
			break;
			
		case 3:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, 0, 0.8);
			break;

		case 4:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 18 * INCHES, 0, 0.5); 
			break;
			
		case 5:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us); 
			break;
			
		case 6:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 1.6 * FEET, 0, -0.5); 
			break;
			
		case 7:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.halfTurnLeft(us, -65, -0.8);
			break;
			
		case 8:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.goStraight(us, 3.1 * FEET, -65, -0.5); 
			break;
		
		case 9:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.openGrabber(us);
			break;
			
		case 10:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 15.5 * INCHES, 0, 0.4); 
			break;
		
		case 11:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.grabCube(us); 
			break;
			
		case 12:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(us, 80 * side);
			break;
			
		case 13:
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 6 * FEET, 80 * side, 0.6); //check goStraight val here
			break;

		case 14:
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 0.5 * FEET, 0, -0.5);
			if (us.encoderWrist.getRaw() < 1100) {
				us.wrist.set(ControlMode.PercentOutput, 0);
			}
			else us.wrist.set(ControlMode.PercentOutput, 0.9); 
			us.driveRobot(0,0);
			break;
		}
		
	}

}
