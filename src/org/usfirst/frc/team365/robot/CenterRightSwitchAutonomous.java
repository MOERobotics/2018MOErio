package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterRightSwitchAutonomous {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.goStraight(robot, 2 * FEET, 0, .5);
			break;
		case 2:
			AutoSimplify.turnToAngle(robot, 45);
			break;
		case 3:
			AutoSimplify.goStraight(robot, 72.7 * INCHES, 45, .5);
			break;
		case 4:
			AutoSimplify.turnToAngle(robot, 0);
			break;
		case 5:
			AutoSimplify.goStraight(robot, 9 * INCHES, 0, .5);
			break;
		case 6:
			AutoSimplify.pause(robot, 1.0);
			break;
		case 7:
			AutoSimplify.goStraight(robot, 48 * INCHES, 0, -.5);
			break;
		case 8:
			AutoSimplify.turnToAngle(robot, -90);
			break;
		case 9:
			AutoSimplify.goStraight(robot, 58 * INCHES, -90, .4);
			break;
		case 10:
			AutoSimplify.turnToAngle(robot, 0);
			break;
		case 11:
			AutoSimplify.goStraight(robot, 9 * INCHES, 0, .5);
			break;
		}
	}
}
