package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class CenterRightSwitchAutonomous {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			robot.goStraight(2 * FEET, 0, .5);
			break;
		case 2:
			robot.turnToAngle(45);
			break;
		case 3:
			robot.goStraight(72.7 * INCHES, 45, .5);
			break;
		case 4:
			robot.turnToAngle(0);
			break;
		case 5:
			robot.goStraight(9 * INCHES, 0, .5);
			break;
		case 6:
			robot.pause(1.0);
			break;
		case 7:
			robot.goStraight(48 * INCHES, 0, -.5);
			break;
		case 8:
			robot.turnToAngle(-90);
			break;
		case 9:
			robot.goStraight(58 * INCHES, -90, .4);
			break;
		case 10:
			robot.turnToAngle(0);
			break;
		case 11:
			robot.goStraight(9 * INCHES, 0, .5);
			break;
		}
	}
}
