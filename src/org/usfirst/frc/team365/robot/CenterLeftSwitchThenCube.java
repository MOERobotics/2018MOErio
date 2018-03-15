package org.usfirst.frc.team365.robot;

// Original author: Audrey

public class CenterLeftSwitchThenCube
{
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
	
switch(us.autoStep) {
		
		case 1:
			AutoSimplify.autoPIDStraight(us, 2 * FEET, 0, 0.5);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
			
		case 2:
			AutoSimplify.autoPIDTurn(us, -52);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			/*if (us.navX.getYaw() < -49) {
				us.driveRobot(0, 0);
				us.autoStep = 2;
			}
			else 
				us.driveRobot(0, 0.6);*/
			break;
			
		case 3:
			AutoSimplify.goStraight(us, 6 * FEET, -52.0, 0.6); //3.6
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
			
		case 4:	
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.deployGrabber(us);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			/*if (us.navX.getYaw() >= -3) {
				us.driveRobot(0, 0);
				us.resetDriveEncoders();
				us.autoStep = 4;
			}
			else 
				us.driveRobot(0.6, 0); */
			break;
			
		case 5: 
			AutoSimplify.goStraight(us, 1.24 * FEET, 0, 0.5); //1.2
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			us.autoTimer.reset();
			break;
			
		case 6:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us); //changed autoSimplify code,,, not sure if okay (made launchCube static)
			break;
			
		case 7:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 4.45 * FEET, 0, -0.5); //4.6
			break;
			
		case 8:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.autoPIDTurn(us, 90);
			break;
			
		case 9:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.openGrabber(us);
			AutoSimplify.goStraight(us, 4.167 * FEET, 90, 0.6); //4.167
			break;
		
		case 10:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.openGrabber(us);
			break;
			
		case 11:
			AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(us, 1.2 * FEET, 0, 0.5); //1.0
			break;
		
		case 12:
			AutoSimplify.grabCube(us); 
			break;
			
		case 13:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, -30);
			break;
			
		case 14:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDStraight(us, 5.5 * FEET, -30, 0.5); //not sure about this value
			break;
			
		case 15:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(us, 0);
			break;
			
		case 16:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.launchCube(us);
			break;
			
		case 17:
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
