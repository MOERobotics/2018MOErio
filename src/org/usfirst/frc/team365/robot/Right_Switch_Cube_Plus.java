package org.usfirst.frc.team365.robot;

public class Right_Switch_Cube_Plus {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	public static void run(Robot robot) {
		switch(robot.autoStep) {


		case 1:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			AutoSimplify.goStraight(robot, 18*FEET, 0, 0.6);
			AutoSimplify.deployGrabber(robot);
			//ROBOT GOES STRAIGHT
			break;

		case 2:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.turnToAngle(robot, -30, 0.6);
			AutoSimplify.deployGrabber(robot);
			//ROBOT TURNS AND GRABBER LOWERS
			break;

		case 3:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.goStraight(robot, 1.1*FEET, -30, 0.6);
			// ROBOT GOES STRAIGHT
			break;

		case 4:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			//GRABBERS LAUNCH CUBE ONTO SWITCH

			break;

		case 5: 
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 2.5*FEET, -30, -0.6);
			//ELEVEATOR DOWN AND MOVING BACKWARDS
			break;
		case 6:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			//ROBOT TURNS TO LANE
			/*	
			AutoSimplify.turnToAngle(robot, -150, 0.6);
			break;

		case 7:
			AutoSimplify.goStraight(robot, 3500, -150, 0.6);
			break;
			 */
			//case 8:

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
		/*	case 1:
			AutoSimplify.goStraight(robot,300*INCHES , 0, .7);
			//change to 33000 for real
			//300 inches

			break;
		case 2:
			AutoSimplify.autoPIDTurn(robot,-90);
			break;
			//elevator up to top
			//rollers up
		case 3: 
			AutoSimplify.autoPIDTurn(robot,180);
			break;

		case 4:
			AutoSimplify.goStraight(robot, 54.55*INCHES , -180, .5);
			//54.55 inches
			break;

		case 5:
			AutoSimplify.autoPIDTurn(robot,-90);		
			break;

		case 6:
			AutoSimplify.goStraight(robot,80*INCHES, -90, .5);
			//80 inches
			break;

		case 7:	
			AutoSimplify.autoPIDTurn(robot,-180);
			break;

		case 8:
			AutoSimplify.goStraight(robot,36.36*INCHES , -180, .5);
			//36.36 inches
			break;
			//rollers in

		case 9:
			robot.driveRobot(0.0,0.0);
			break;
		}
		 */
	}
}