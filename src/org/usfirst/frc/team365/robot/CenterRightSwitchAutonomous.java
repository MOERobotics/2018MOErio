package org.usfirst.frc.team365.robot;

public class CenterRightSwitchAutonomous {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			if(robot.navX.getYaw() > 42) {
				robot.encoderL.reset();
				robot.encoderR.reset();
				robot.autoStep = 2;
			}
			else {
				robot.driveRobot(.7, 0);
			}
			break;
		case 2:
			AutoSimplify.goStraight(robot, 45.0 * INCHES, 45, .5);
			break;
		case 3:
			if(robot.navX.getYaw() < 3) {
				robot.encoderL.reset();
				robot.encoderR.reset();
				robot.autoStep = 4;
			}
			else {
				robot.driveRobot(0,.6);
			}
			break;
		case 4:
			AutoSimplify.goStraight(robot, 14 * INCHES,0,.7);
			break;
		case 5:
			AutoSimplify.pause(robot, 1.0);
			break;
		case 6:
			AutoSimplify.goStraight(robot, 48 * INCHES, 0, -.5);
			break;
		case 7:
			AutoSimplify.turnToAngle(robot, -90);
			break;
		case 8:
			AutoSimplify.goStraight(robot, 58 * INCHES, -90, .4);
			break;
		case 9:
			AutoSimplify.turnToAngle(robot, 0);
			break;
		case 10:
			AutoSimplify.goStraight(robot, 9 * INCHES, 0, .5);
			break;
		}
	}
}
