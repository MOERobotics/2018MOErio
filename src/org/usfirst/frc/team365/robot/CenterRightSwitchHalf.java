package org.usfirst.frc.team365.robot;

public class CenterRightSwitchHalf {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.halfTurnRight(robot, 40, .8);
			break;
		case 2:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 62 * INCHES, 40, .5);
			break;
		case 3:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnLeft(robot, 0, .8);
			break;
		case 4:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 13 * INCHES, 0, .5);
			break;
		case 5:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 49 * INCHES, 0, -.5);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.openGrabber(robot);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 54 * INCHES, -90, .5);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 12 * INCHES, 0, .5);
			break;
		case 11:
			AutoSimplify.grabCube(robot);
			break;
		case 12:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 70);
			break;
		case 13:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 50 * INCHES, 70, .5);
			break;
		case 14:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 15:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 13 * INCHES, 0, .5);
			break;
		case 16:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 17:
			robot.driveRobot(0.0,0.0);
			break;
		}
	}
}
