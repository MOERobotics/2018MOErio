package org.usfirst.frc.team365.robot;

public class ScaleSwitchCombo {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	public static void leftStart(Robot robot) {
		switch(robot.autoStep) {

		case 1:
			AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			AutoSimplify.autoPIDStraight(robot, 15.75*FEET, 0, 0.7);
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
			AutoSimplify.goStraight(robot, 4.083*FEET, 25, 0.5);
			// ROBOT GOES STRAIGHT
			break;

		case 4:
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			//GRABBERS LAUNCH CUBE ONTO SWITCH
			break;
		case 5: 
			AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
			AutoSimplify.goStraight(robot, 33*INCHES, 25, -0.4);
			//ELEVATOR DOWN AND MOVING BACKWARDS

			break;
		case 6:
			AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
			//ROBOT TURNS TO LANE
			AutoSimplify.autoPIDTurn(robot, 90);

			break;
		case 7:	
			AutoSimplify.lowerElevator(robot, 600);
			//AutoSimplify.openGrabber(robot);
			AutoSimplify.goStraight(robot, 2.7*FEET, 90, 0.4);
			//ROBOT GOES STRAIGHT
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, 600);
			AutoSimplify.autoPIDTurn(robot, 180);
			//ROBOT TURNS TO FACE CUBE TO PICK UP
			break;
		case 9:
			AutoSimplify.openGrabber(robot);
			AutoSimplify.downElevatorStep(robot,robot.BOTTOM_HEIGHT);
			//AutoSimplify.goStraight(robot, 1.2*FEET, 180, 0.4);
			//ROBOT GOES STRAIGHT AND CLOSE TO CUBE
			break;
		case 10:
			AutoSimplify.goStraight(robot, 1.1*FEET, 180, .4);
			break;
		case 11:
			//AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.grabCube(robot);
			//GRABBERS PICK UP CUBE
			break;
			//TEST THIS for after getting cube (scale via gamedata)
		case 12:
			if(!(robot.switchLeft)){
				AutoSimplify.halfTurnLeft(robot, 25, -0.8);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			} else {
				AutoSimplify.upElevatorStep(robot, robot.HEIGHT_FOR_SWITCH);
			}
			break;
		case 13:
			if(!(robot.switchLeft)){
				AutoSimplify.goStraight(robot, 3.5*FEET, 25, 0.4);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			} else {
				AutoSimplify.goStraight(robot, 1.25*FEET, 170, 0.4);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
			}
			break;
		case 14:
			if(!(robot.switchLeft)){
				AutoSimplify.openGrabber(robot);
				robot.driveRobot(0, 0);
			} else {
				AutoSimplify.launchCube(robot);
				robot.driveRobot(0,0);
			}
			break;
		case 15:
			robot.driveRobot(0, 0);
			break;
		}

	}

		public static void rightStart(Robot robot) {
			switch(robot.autoStep) {

			case 1:
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				AutoSimplify.autoPIDStraight(robot, 15.75*FEET, 0, 0.7);
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
				AutoSimplify.goStraight(robot, 4.083*FEET, -25, 0.5);
				// ROBOT GOES STRAIGHT
				break;

			case 4:
				AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
				AutoSimplify.launchCube(robot);
				//GRABBERS LAUNCH CUBE ONTO SWITCH
				break;
			case 5: 
				AutoSimplify.lowerElevator(robot, 600);
				AutoSimplify.goStraight(robot, 33*INCHES, -25, -0.4);
				//ELEVATOR DOWN AND MOVING BACKWARDS

				break;
			case 6:
				AutoSimplify.lowerElevator(robot, 600);
				//ROBOT TURNS TO LANE
				AutoSimplify.autoPIDTurn(robot, -90);

				break;
			case 7:	
				AutoSimplify.lowerElevator(robot, 600);
				//AutoSimplify.openGrabber(robot);
				AutoSimplify.goStraight(robot, 2.7*FEET, -90, 0.4);
				//ROBOT GOES STRAIGHT
				break;
			case 8:
				AutoSimplify.lowerElevator(robot, 600);
				AutoSimplify.autoPIDTurn(robot, -180);
				//ROBOT TURNS TO FACE CUBE TO PICK UP
				break;
			case 9:
				AutoSimplify.openGrabber(robot);
				AutoSimplify.downElevatorStep(robot,robot.BOTTOM_HEIGHT);
				//AutoSimplify.goStraight(robot, 1.2*FEET, 180, 0.4);
				//ROBOT GOES STRAIGHT AND CLOSE TO CUBE
				break;
			case 10:
				AutoSimplify.goStraight(robot, 1.1*FEET, 180, .4);
				break;
			case 11:
				//AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
				AutoSimplify.grabCube(robot);
				//GRABBERS PICK UP CUBE
				break;
				//TEST THIS for after getting cube (scale via gamedata)
			case 12:
				if(robot.switchLeft){
					AutoSimplify.halfTurnRight(robot, -25, -0.8);
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				} else {
					AutoSimplify.upElevatorStep(robot, robot.HEIGHT_FOR_SWITCH);
				}
				break;
			case 13:
				if(robot.switchLeft){
					AutoSimplify.goStraight(robot, 3.5*FEET, -25, 0.4);
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				} else {
					AutoSimplify.goStraight(robot, 1.25*FEET, -170, 0.4);
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				}
				break;
			case 14:
				if(robot.switchLeft){
					AutoSimplify.openGrabber(robot);
					robot.driveRobot(0, 0);
				} else {
					AutoSimplify.launchCube(robot);
					robot.driveRobot(0, 0);
				}
				break;
			case 15:
				robot.driveRobot(0, 0);
				break;

			}
		}
	}

