package org.usfirst.frc.team365.robot;

public class RightScalefromSide {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
	switch(robot.autoStep) {
	case 1:
		AutoSimplify.goStraight(robot, 24.5*FEET, 0, 0.8);
		AutoSimplify.deployGrabber(robot);
	AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
		
	case 2:
		AutoSimplify.autoPIDTurn(robot, -80);
		AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
		//raise elevator to scale height
		
	case 3:
		AutoSimplify.launchCube(robot);
		
	case 4:
		AutoSimplify.autoPIDTurn(robot, 0);
	case 5:
		AutoSimplify.lowerElevator(robot, 0);
	case 6:
		robot.driveRobot(0, 0);
}
	}
}
