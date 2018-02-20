package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			robot.goStraight(11 * FEET + 4 * INCHES, 0, .7);
			break;
		case 2:
			//turnToAngle(navxsetPoint, maxPower)
			robot.turnToAngle(-90);
			break;
		case 3:
			robot.goStraight(.5 * FEET, -90, .5);
			break;
		case 4:
			robot.pause(1);
			break;
		case 5:
			robot.goStraight(.5 * FEET, -90, -.5);
			break;
		case 6:
			robot.turnToAngle(0);
			break;
		case 7:
			robot.goStraight(6 * FEET, 0, .5);
			break;
		case 8:
			robot.turnToAngle(-90);
			break;
		case 9:
			robot.goStraight(4 * FEET, -90, .5);
			break;
		case 10:
			robot.turnToAngle(-180);
			break;
		case 11:
			robot.goStraight(1 * FEET, -180, .5);
			break;
		case 12:
			robot.driveRobot(0,0);
			break;
			
		}
		
		
	}
}
