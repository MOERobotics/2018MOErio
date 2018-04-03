package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CenterLeftMAR {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
		switch(us.autoStep) {
		
		case 1:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.halfTurnLeft(us, -45, 0.8);
			break;
			
		case 2:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 57 * INCHES, -45, 0.5); //60
			break;
			
		case 3:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, 0, 0.8);
			break;

		case 4:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 18 * INCHES, 0, 0.5); //15
			break;
			
		case 5:
			AutoSimplify.launchCube(us); 
			break;
			
		case 6:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 2.1 * FEET, 0, -0.5); 
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
			AutoSimplify.goStraight(us, 16.5 * INCHES, 0, 0.4); //15
			break;
		
		case 11:
			AutoSimplify.grabCube(us); 
			break;
			
		case 12:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, -75); //-70
			break;
			
		case 13:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 3.1 * FEET, -70, 0.5); //3ft
			break;	
			
		case 14:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, -5, 0.8); 
			break;
			
		case 15:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 0.7 * FEET, -5, 0.5); //1ft
			break;
			
		case 16:
			AutoSimplify.launchCube(us); 
			break;
			
		case 17:
			//works
			AutoSimplify.goStraight(us, 0.5 * FEET, 0, -0.5);
			if (us.encoderWrist.getRaw() < 1100) {
				us.wrist.set(ControlMode.PercentOutput, 0);
			}
			else us.wrist.set(ControlMode.PercentOutput, 0.9); 
			break;
			
		case 18:
			us.driveRobot(0,0);
			break;
			
			
		}
		
	}
	
}	

/* some stuff:
 * 	time to drop cube: 13-ish sec
 * 	extra 2-ish sec to 
 * 	
 */
