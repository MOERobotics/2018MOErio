package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			AutoSimplify.goStraight(robot, 11 * FEET + 4 * INCHES, 0, .7);
			break;
		case 2:
			//turnToAngle(navxsetPoint, maxPower)
			AutoSimplify.turnToAngle(robot, -90);
			break;
		case 3:
			AutoSimplify.goStraight(robot, .5 * FEET, -90, .5);
			break;
		case 4:
			AutoSimplify.pause(robot, 1);
			break;
		case 5:
			AutoSimplify.goStraight(robot, .5 * FEET, -90, -.5);
			break;
		case 6:
			AutoSimplify.turnToAngle(robot, 0);
			break;
		case 7:
			AutoSimplify.goStraight(robot, 6 * FEET, 0, .5);
			break;
		case 8:
			AutoSimplify.turnToAngle(robot, -90);
			break;
		case 9:
			AutoSimplify.goStraight(robot, 4 * FEET, -90, .5);
			break;
		case 10:
			AutoSimplify.turnToAngle(robot, -180);
			break;
		case 11:
			AutoSimplify.goStraight(robot, 1 * FEET, -180, .5);
			break;
		case 12:
			robot.driveRobot(0,0);
			break;
			
		}
		
		
	}
}
