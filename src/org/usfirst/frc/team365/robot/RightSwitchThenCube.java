package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = 110;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			//goStraight(encoderTicks, Power forward/backwards, navx setPoint)
			robot.goStraight(12 * FEET, 0, .5);
			break;
		case 2:
			robot.turnToAngle(-90, .8);
			break;
		case 3:
			robot.goStraight(.8 * FEET, -90, .5);
			break;
		case 4:
			robot.goStraight(.8 * FEET, -90, -.5);
			break;
		case 5:
			robot.turnToAngle(0, .8);
			break;
		case 6:
			robot.goStraight(6 * FEET, 0, .5);
			break;
		case 7:
			robot.turnToAngle(-90, .8);
			break;
		case 8:
			robot.goStraight(3 * FEET, 90, .5);
			break;
		case 9:
			robot.turnToAngle(-180, .8);
			break;
		case 10:
			robot.goStraight(2 * FEET, 180, .5);
			break;
		case 11:
			robot.driveRobot(0,0);
			break;
			
		}
		
		
	}
}
