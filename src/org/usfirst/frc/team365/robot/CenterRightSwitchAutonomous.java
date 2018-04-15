package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterRightSwitchAutonomous {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.goStraight(robot, 2 * FEET, 0, .4);
			break;
		case 2:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 45);
			break;
		case 3:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 72.7 * INCHES, 45, .4);
			break;
		case 4:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 5:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 1 * INCHES, 0, .4);
			break;
		case 6:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 48 * INCHES, 0, -.4);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.autoPIDTurn(robot, -90);
			AutoSimplify.openGrabber(robot);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 58 * INCHES, -90, .4);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 11:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 9 * INCHES, 0, .4);
			break;
		case 12:
			AutoSimplify.grabCube(robot);
			break;
		case 13:
			robot.driveRobot(0, 0);
			break;
		}
	}
}
