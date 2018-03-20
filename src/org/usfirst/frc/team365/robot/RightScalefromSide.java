package org.usfirst.frc.team365.robot;

public class RightScalefromSide {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
	switch(robot.autoStep) {
	case 1:
		AutoSimplify.autoPIDStraight(robot, 21*FEET, 0, 0.7);
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
		AutoSimplify.launchCube(robot);
		break;
	/*case 5:
		AutoSimplify.autoPIDTurn(robot, 0);
		break;
	case 6:
		AutoSimplify.lowerElevator(robot, 0);
		break;
	case 7: 
		AutoSimplify.goStraight(robot, 6*FEET, 0, -0.6);
		break;
	case 8:
		AutoSimplify.autoPIDTurn(robot, -90);
		break;
	case 9:
		AutoSimplify.goStraight(robot, 3.83*FEET, -90, 0.4);
		break;
	case 10:
		AutoSimplify.autoPIDTurn(robot, 180);
		break;
	case 11:
		AutoSimplify.goStraight(robot, 1.5*FEET, 180, 0.3);
		*/
	default:
		robot.driveRobot(0, 0);
		break;
}
	}
}
