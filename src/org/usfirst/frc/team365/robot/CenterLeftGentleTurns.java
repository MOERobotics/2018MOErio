package org.usfirst.frc.team365.robot;

// Original author: Audrey

public class CenterLeftGentleTurns {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
		switch(us.autoStep) {
		
		case 1:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.halfTurnLeft(us, -45, .8);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
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
			AutoSimplify.goStraight(us, 14 * INCHES, 0, .5);
			break;
			
		case 5:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us); 
			break;
			
		case 6:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 49 * INCHES, 0, -0.4); //2.75
			break;
			
		case 7:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.autoPIDTurn(us, 90);
			break;
			
		case 8:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.goStraight(us, 4.2 * FEET, 90, 0.4); //3.2,, 4.6
			break;
		
		case 9:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.openGrabber(us);
			break;
			
		case 10:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 9 * INCHES, 0, 0.4); //1.2
			break;
		
			
		case 11:
			AutoSimplify.grabCube(us); 
			break;
			
		case 12:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, -70); //-70 
			break;
			
		case 13:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDStraight(us, 4.1 * FEET, -70, 0.5); //check distance? i'm guessing to short,, nope too long
			break;
			
		case 14:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnRight(us, 0, 0.8);
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
			AutoSimplify.launchCube(us);
			break;
			
		case 16:
			us.driveRobot(0, 0);
			break;


			
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
