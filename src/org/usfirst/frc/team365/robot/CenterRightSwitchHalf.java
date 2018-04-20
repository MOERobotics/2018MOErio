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
			//AutoSimplify.goStraight(robot, 2.1 * FEET, 0, -0.5); (Alternative)
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.openGrabber(robot);

			//AutoSimplify.halfTurnRight(robot, 65, -0.8); (Alternative)
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 54 * INCHES, -90, .5);
			//AutoSimplify.goStraight(robot, 3.1 * FEET, 65, -0.5); (Alternative)
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 16 * INCHES, 0, .5);
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
			AutoSimplify.goStraight(robot, 12 * INCHES, 0, .5);
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
	static void run2(Robot robot) {
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
			AutoSimplify.goStraight(robot, 1.5 * FEET, 0, -.5);//2.1
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.halfTurnRight(robot, 65, -.8);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 3.6 * FEET, 65, -.5);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.openGrabber(robot);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 23 * INCHES, 0, .5);//15
			break;
		case 11:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.grabCube(robot);
			break;
		case 12:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 70);
			break;
		case 13:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			//AutoSimplify.goStraight(robot, 50 * INCHES, 70, .5);
			AutoSimplify.goStraight(robot, 36 * INCHES, 70, .5);
			break;
		case 14:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			//AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.halfTurnLeft(robot, 0, .8);
			break;
		case 15:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 2 * INCHES, 0, .5);
			break;
		case 16:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 17:
			AutoSimplify.goStraight(robot, 2 * FEET, 0, -.6);
			break;
		case 18:
			AutoSimplify.autoPIDTurn(robot, 50);
			break;
		case 19:
			AutoSimplify.goStraight(robot, 2 * FEET, 50, .6);
			break;
		case 20:
			AutoSimplify.halfTurnLeft(robot, 0, .6);
			break;
		case 21:
			robot.driveRobot(0, 0);
			break;
		}
	}
	static void vault(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.halfTurnRight(robot, 40, .8);
			break;
		case 2:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 62 * INCHES, 40, .6);
			break;
		case 3:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnLeft(robot, 0, .8);
			break;
		case 4:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 13 * INCHES, 0, .6);
			break;
		case 5:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 1.5 * FEET, 0, -.6);//2.1
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.halfTurnRight(robot, 65, -.8);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 3.9 * FEET, 65, -.6);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.openGrabber(robot);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 23 * INCHES, 0, .6);//15
			break;
		case 11:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.grabCube(robot);
			break;
		case 12:
			AutoSimplify.goStraight(robot,  10 * INCHES, 0, -.6);
			break;
		case 13:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, -125);
			break;
		case 14:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 26 * INCHES, -125, .6);
			break;
		case 15:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, -175);
			break;
		case 16:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 4 * INCHES, -175, .6);
			break;
		case 17:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.launchCube(robot);
			break;
		case 18:
			robot.driveRobot(0,0);
			break;
		}
	}
	static void vault2(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.halfTurnRight(robot, 40, .8);
			break;
		case 2:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 62 * INCHES, 40, .6);
			break;
		case 3:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.halfTurnLeft(robot, 0, .8);
			break;
		case 4:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 13 * INCHES, 0, .6);
			break;
		case 5:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 1.5 * FEET, 0, -.6);//2.1
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.halfTurnRight(robot, 65, -.8);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 3.9 * FEET, 65, -.6);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.openGrabber(robot);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 23 * INCHES, 0, .6);//15
			break;
		case 11:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.grabCube(robot);
			break;
		case 12:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 70);
			break;
		case 13:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			//AutoSimplify.goStraight(robot, 50 * INCHES, 70, .5);
			AutoSimplify.goStraight(robot, 42 * INCHES, 70, .6);
			break;
		case 14:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			//AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.halfTurnLeft(robot, 0, .8);
			break;
		case 15:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 2 * INCHES, 0, .6);
			break;
		case 16:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 17:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.goStraight(robot, 1.0 * FEET, 0, -.6);//1.5
			break;
		case 18:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.halfTurnRight(robot, 65, -.8);
			break;
		case 19:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.goStraight(robot, 3.9 * FEET, 65, -.6);
			break;
		case 20:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.autoPIDTurn(robot, 0);
			AutoSimplify.openGrabber(robot);
			break;
		case 21:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.goStraight(robot, 27 * INCHES, 0, .6);//15
			break;
		case 22:
			AutoSimplify.lowerElevator(robot, 600);
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.grabCube(robot);
			break;
		case 23:
			AutoSimplify.goStraight(robot,  10 * INCHES, 0, -.6);
			break;
		case 24:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, -150);
			break;
		case 25:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 48 * INCHES, -140, .6);
			break;
		case 26:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, -175);
			break;
		case 27:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.launchCube(robot);
			break;
		case 28:
			robot.driveRobot(0,0);
			break;
		}
	}
}
