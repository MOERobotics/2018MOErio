package org.usfirst.frc.team365.robot;

public class Right_Scale_Cube_Plus {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	public static void run(Robot robot) {
		switch(robot.autoStep) {

/*
		case 1:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			AutoSimplify.goStraight(robot, 15.75*FEET, 0, 0.6);
			AutoSimplify.deployGrabber(robot);
			//ROBOT GOES STRAIGHT
			//ELEVATOR UP
			break;

		case 2:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.autoPIDTurn(robot, -25);
			AutoSimplify.deployGrabber(robot);
			//ROBOT TURNS AND GRABBER LOWERS
			break;

		case 3:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.goStraight(robot, 4.083*FEET, -25, 0.6);
			// ROBOT GOES STRAIGHT
			break;

		case 4:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			//GRABBERS LAUNCH CUBE ONTO SWITCH
			break;
		case 5: 
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 2.5*FEET, -25, -0.6);
			//ELEVATOR DOWN AND MOVING BACKWARDS

			break;
		case 6:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			//ROBOT TURNS TO LANE
			AutoSimplify.turnToAngle(robot, -90, 0.6);
			break;
		case 7:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 3*FEET, -90, 0.6);
			//ROBOT GOES STRAIGHT
			break;
		case 8:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.turnToAngle(robot, 180, 0.6);
			//ROBOT TURNS TO FACE CUBE TO PICK UP
			break;
		case 9:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 1.3*FEET, 180, 0.4);
			//ROBOT GOES STRAIGHT AND CLOSE TO CUBE
			break;
		case 10:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.grabCube(robot);
			//GRABBERS PICK UP CUBE
			break;
		case 11:
			robot.driveRobot(0.0,0.0);
			//END PROGRAM
			break;
		}
		*/
	
	case 1:
		AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
		AutoSimplify.autoPIDStraight(robot, 15.75 * FEET, 0, 0.7);
//		AutoSimplify.goStraight(robot, 15.75*FEET, 0, 0.6);
		AutoSimplify.deployGrabber(robot);
		//ROBOT GOES STRAIGHT
		//ELEVATOR UP
		break;

	case 2:
		AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
		AutoSimplify.autoPIDTurn(robot, -25);
		//AutoSimplify.halfTurnLeft(robot, -25, 0.6);
		AutoSimplify.deployGrabber(robot);
		//ROBOT TURNS AND GRABBER LOWERS
		break;

	case 3:
		AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
		AutoSimplify.goStraight(robot, 4.083*FEET, -25, 0.4);
		//AutoSimplify.goStraight(robot, 3.6 * FEET, -25, 0.4);
		// ROBOT GOES STRAIGHT
		break;

	case 4:
		AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
		AutoSimplify.launchCube(robot);
		//GRABBERS LAUNCH CUBE ONTO SWITCH
		break;
	case 5: 
		AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
		AutoSimplify.goStraight(robot, 3*FEET, -25, -0.4);
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
	/*case 6:
		AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
		//ROBOT TURNS TO LANE
		AutoSimplify.turnToAngle(robot, -130, 0.6);
		break;
	case 7:
		AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
		AutoSimplify.goStraight(robot, 2*FEET, -150, 0.4);
		//ROBOT GOES STRAIGHT
		break;
	case 8:
		AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
		AutoSimplify.grabCube(robot);
		break;
	case 11:
		robot.driveRobot(0.0,0.0);
		//END PROGRAM
		break;
	} */
		 
	}
	}
}
