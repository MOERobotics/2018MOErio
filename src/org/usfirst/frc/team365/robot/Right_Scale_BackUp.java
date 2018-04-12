package org.usfirst.frc.team365.robot;

public class Right_Scale_BackUp {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	public static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			AutoSimplify.autoPIDStraight(robot, 23.5* FEET , 0, 0.8);
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			break;
		case 2:
			AutoSimplify.upElevatorStep(robot, robot.HEIGHT_FOR_SCALE);
			
			break;
		case 3:
			AutoSimplify.autoPIDTurn(robot, -60);
			break;
		case 4:
			AutoSimplify.autoPIDStraight(robot, 0.3*FEET, -60, 0.4);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			break;
		case 5:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.autoPIDStraight(robot, 5*FEET, -60, -0.3);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			break;
		case 8:
			AutoSimplify.autoPIDTurn(robot, 0);
			
			break;
		}
	}
}
