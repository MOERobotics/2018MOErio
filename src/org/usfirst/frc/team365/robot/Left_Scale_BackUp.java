package org.usfirst.frc.team365.robot;

public class Left_Scale_BackUp {
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
			AutoSimplify.autoPIDTurn(robot, 60);
			break;
		case 4:
			AutoSimplify.autoPIDStraight(robot, 0.3*FEET, 60, 0.4);
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			break;
		case 5:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			break;
		case 6:
			AutoSimplify.autoPIDStraight(robot, 5*FEET, 60, -0.3);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
			break;
		case 8:
			AutoSimplify.autoPIDTurn(robot, 0);
			
			break;
		}
	}
	/*
case 1:
	AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
	AutoSimplify.autoPIDStraight(robot, 15.75 * FEET, 0, 0.6);
//	AutoSimplify.goStraight(robot, 15.75*FEET, 0, 0.6);
	AutoSimplify.deployGrabber(robot);
	//ROBOT GOES STRAIGHT
	//ELEVATOR UP
	break;

case 2:
	AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
	AutoSimplify.autoPIDTurn(robot, 25);
	AutoSimplify.deployGrabber(robot);
	//ROBOT TURNS AND GRABBER LOWERS
	break;

case 3:
	AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
	AutoSimplify.goStraight(robot, 4.083*FEET, 25, 0.4);
	// ROBOT GOES STRAIGHT
	break;

case 4:
	AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
	AutoSimplify.launchCube(robot);
	//GRABBERS LAUNCH CUBE ONTO SWITCH
	break;
case 5: 
	AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
	AutoSimplify.goStraight(robot, 3*FEET, 25, -0.4);
	//ELEVATOR DOWN AND MOVING BACKWARDS

	break;
case 6:
	AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
	robot.driveRobot(0, 0);
	break;
case 7: 
	AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
	robot.driveRobot(0, 0);
	break;
case 8: 
	AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
	robot.driveRobot(0, 0);
	break;
case 9: 
	AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
	robot.driveRobot(0, 0);
	break;
	*/
}

