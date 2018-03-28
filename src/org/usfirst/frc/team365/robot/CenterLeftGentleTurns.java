package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

// Original author: Audrey

public class CenterLeftGentleTurns {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
		switch(us.autoStep) {
		
		case 1:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.halfTurnLeft(us, -45, .8);
			break;
			
		case 2:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 60 * INCHES, -45, .5);
			break;
			
		case 3:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, 0, .8);
			break;

		case 4:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(us, 15 * INCHES, 0, .5);
			break;
			
		case 5:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us); 
			break;
			
		case 6:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 4.3 * FEET, 0, -0.5); //4.6
			//AutoSimplify.goStraght(us, 2.1 * FEET, 0, -0.5); Alternative Gentle turn routine
			break;
			
		case 7:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.autoPIDTurn(us, 90);
			//AutoSimplify.halfTurnLeft(us, -65, -0.8); (Alternative)
			break;
			
		case 8:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.goStraight(us, 4 * FEET, 90, 0.6); //4.3
			//AutoSimplify.goStraight(us, 3.1 * FEET, -65, -0.5); (Alternative)
			break;
		
		case 9:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.openGrabber(us);
			break;
			
		case 10:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 1.7 * FEET, 0, 0.4); //1.2
			//AutoSimplify.goStraight(us, 15*INCHES, 0, 0.4); (Alternative)
			break;
		
		case 11:
			AutoSimplify.grabCube(us); 
			break;
			
		case 12:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, -75); //-70 
			//AutoSimplify.autoPIDTurn(us, -70); (Alternative)
			break;
			
		case 13:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDStraight(us, 4.1 * FEET, -75, 0.6); //4
			//AutoSimplify.goStraight(us, 3*FEET, -70, 0.5); (Alternative)
			break;	
		case 14:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, 0, 0.8);
			//AutoSimplify.halfTurnRight(us, -2, 0.8); (Alternative)
			/*if (us.navX.getYaw() >= -3) {
				us.driveRobot(0, 0);
				us.resetDriveEncoders();
				us.autoStep = 4;
			}
			else 
				us.driveRobot(0.6, 0);*/
			//AutoSimplify.autoPIDTurn(us, 0);
			break;
			
		case 15:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us); //Take this out if (Alternative)
			//AutoSimplify.goStraight(us, 1*FEET, -2, 0.5);
			break;
			
		case 16:
			//sets wrist back to level -- don't know if works
			//AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH); (Alternative)
			//AutoSimplify.launchCube(us); //Take this out if (Alternative)
			if (us.encoderWrist.getRaw() < 1100) {
				us.wrist.set(ControlMode.PercentOutput, 0);
			}
			else us.wrist.set(ControlMode.PercentOutput, 0.9); 
			
			us.driveRobot(0, 0);
			break;
/*		case 17:
 * 			us.driveRobot(0,0);
 */
			
		
			
		/* case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			us.goStraight(2 * FEET, 0, 0.5);
			break;
			
		case 2:
			//turnToAngle(navx setPoint, maxPower)
			us.turnToAngle(-52, 1);
			break;
			
		case 3:
			us.goStraight(6 * FEET, -55, 0.5);
			break;
		
		case 4:
			us.turnToAngle(0, 1);
			break;
			
		case 5:
			us.goStraight(2.24 * FEET, 0, 0.5);
			us.autoTimer.reset();
			break;
			
		case 6:
			if (us.autoTimer.get() > 1.0) {
				us.autoTimer.reset();
				us.autoStep = 7;
			}
			break;
			
		case 7:
			us.goStraight(4 * FEET, 0, -0.5);
			break;
			
		case 8: 
			us.turnToAngle(90, 1);
			break;
			
		case 9:
			us.goStraight(4.167 * FEET, 90, 0.5);
			break;
		
		case 10:
			us.turnToAngle(0, 1);
			break;
		
		
		*/
		
		
		
		}
		
	}
	
}
