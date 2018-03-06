package org.usfirst.frc.team365.robot;

// Original author: Audrey

public class CenterLeftSwitchThenCube
{
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
		
		switch(us.autoStep) {
		
		case 1:
			AutoSimplify.deployGrabber(us);
			if (us.navX.getYaw() < -49) {
				us.driveRobot(0, 0);
				us.autoStep = 2;
			}
			else 
				us.driveRobot(0, 0.6);
			break;
			
		case 2:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.goStraight(us, 3.6 * FEET, -52.0, 0.55);
			break;
			
		case 3:	
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.deployGrabber(us);
			if (us.navX.getYaw() >= -3) {
				us.driveRobot(0, 0);
				us.resetDistanceEncoders();
				us.autoStep = 4;
			}
			else 
				us.driveRobot(0.6, 0);
			break;
			
		case 4: 
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.goStraight(us, 1.2 * FEET, 0, 0.6);
			us.autoTimer.reset();
			break;
			
		case 5:
			AutoSimplify.launchCube(us);
			break;
			
			
		case 6:
			AutoSimplify.goStraight(us, 3.25 * FEET, 0, -0.5);
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			break;
			
		case 7: 
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.turnToAngle(us, 90, 0.6);
			break;
			
		case 8:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 3.5 * FEET, 90, 0.6);
			break;
		
		case 9:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.turnToAngle(us, 0, 0.6);
			break;
		
			
		case 10:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 0.5 * FEET, 0, 0.5); 
			break;
			
		case 11:
			AutoSimplify.grabCube(us); 
			break;
			
		case 12:
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
