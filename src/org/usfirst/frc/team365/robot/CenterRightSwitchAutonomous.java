package org.usfirst.frc.team365.robot;

public class CenterRightSwitchAutonomous {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch (robot.autoStep) {
		case 1:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
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
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 45.0 * INCHES, 45, .5);
			break;
		case 3:
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
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
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			AutoSimplify.goStraight(robot, 10 * INCHES,0,.7);
			break;
		case 5:
			//AutoSimplify.pause(robot, 1.0);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 48 * INCHES, 0, -.5);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.turnToAngle(robot, -90);
			AutoSimplify.autoPIDTurn(robot, -90);
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 58 * INCHES, -90, .4);
			break;
		case 9:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			//AutoSimplify.turnToAngle(robot, 0);
			AutoSimplify.autoPIDTurn(robot, 0);
			break;
		case 10:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 9 * INCHES, 0, .5);
			break;
		case 11:
			AutoSimplify.grabCube(robot);
			break;
		case 12:
			robot.driveRobot(0, 0);
			break;
		}
	}
}
