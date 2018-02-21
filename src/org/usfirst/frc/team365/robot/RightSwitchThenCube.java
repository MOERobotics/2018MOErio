package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			robot.wristDown();
			robot.pulseRolliesIn(.3);
			robot.goStraight(12 * FEET, 0, .5);
			robot.setElevator(robot.SWITCH_HEIGHT);
			break;
		case 2:
			robot.turnToAngle(-90, .8);
			break;
		case 3:
			robot.pulseRolliesOut(.5);
			break;
		case 5:
			robot.turnToAngle(0);
			break;
		case 6:
			robot.setElevator(robot.BOTTOM);
			robot.goStraight(6 * FEET, 0, .5);
			break;
		case 7:
			robot.turnToAngle(-90, .8);
			break;
		case 8:
			robot.goStraight(3 * FEET, 90, .5);
			robot.openGrabber();
			break;
		case 9:
			robot.turnToAngle(-180, .8);
			robot.rolliesIn();
			break;
		case 10:
			robot.goStraight(2 * FEET, 180, .5);
			break;
		case 11:
			robot.closeGrabber();
			robot.driveRobot(0,0);
			robot.pulseRolliesIn(.2);
			break;
			
		}
		
		
	}
}
