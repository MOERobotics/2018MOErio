package org.usfirst.frc.team365.robot;

public class ScaleSwitchCombo {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	public static void leftSameSideStart(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			AutoSimplify.raiseElevator(robot, 3000);
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
			robot.autoTape = false;
			// ROBOT GOES STRAIGHT
			break;

		case 4:
			if(robot.leftTape.get()) robot.autoTape = true;
			AutoSimplify.raiseElevator(robot,robot.HEIGHT_FOR_SCALE);
			AutoSimplify.launchCube(robot);
			//GRABBERS LAUNCH CUBE ONTO SCALE
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
			AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
			//AutoSimplify.openGrabber(robot);
			AutoSimplify.goStraight(robot, 2.9*FEET, 90, 0.4);
			//ROBOT GOES STRAIGHT
			break;
		case 8:
			AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
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
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.goStraight(robot, 1.1*FEET, 180, .4);
			break;
		case 11:
			AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
			AutoSimplify.grabCube(robot);
			//GRABBERS PICK UP CUBE
			break;
			//TEST THIS for after getting cube (scale via gamedata)
		case 12:
			if(robot.switchLeft){
				AutoSimplify.upElevatorStep(robot, robot.HEIGHT_FOR_SWITCH);
			}
			else{
				AutoSimplify.halfTurnLeft(robot, 30, -0.8);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			}
			break;
		case 13:
			if(robot.switchLeft){
				AutoSimplify.goStraight(robot, 1.3*FEET, 180, 0.4);
			}
			else{
				AutoSimplify.goStraight(robot, 3.5*FEET, 30, 0.4);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
			}
			break;
		case 14:
			if(robot.switchLeft){
				AutoSimplify.launchCube(robot);
			}
			else{
				AutoSimplify.openGrabber(robot);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				robot.driveRobot(0, 0);
			}
			break;
		case 15:
			if(robot.switchLeft){
				AutoSimplify.goStraight(robot, 1.1*FEET, 180, -0.4);
			}
			else{
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				robot.driveRobot(0, 0);
			}
			break;
		}

	}

		public static void rightSameSideStart(Robot robot) {
			switch(robot.autoStep) {

			case 1:
				AutoSimplify.raiseElevator(robot, 3000);
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
				AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
				AutoSimplify.goStraight(robot, 33*INCHES, -25, -0.4);
				//ELEVATOR DOWN AND MOVING BACKWARDS

				break;
			case 6:
				AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
				//ROBOT TURNS TO LANE
				AutoSimplify.autoPIDTurn(robot, -90);
				break;
			case 7:	
				AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
				//AutoSimplify.openGrabber(robot);
				AutoSimplify.goStraight(robot, 2.9*FEET, -90, 0.4);
				//ROBOT GOES STRAIGHT
				break;
			case 8:
				AutoSimplify.lowerElevator(robot, robot.HEIGHT_ABOVE_CUBE);
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
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(robot, 1.1*FEET, 180, .4);
				break;
			case 11:
				AutoSimplify.lowerElevator(robot,robot.BOTTOM_HEIGHT);
				AutoSimplify.grabCube(robot);
				//GRABBERS PICK UP CUBE
				break;
				//TEST THIS for after getting cube (scale via gamedata)
			case 12:
				if(!robot.switchLeft){
					AutoSimplify.upElevatorStep(robot, robot.HEIGHT_FOR_SWITCH);
				}
				else{
					AutoSimplify.halfTurnRight(robot, -30, -0.8);
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				}
				break;
			case 13:
				if(!robot.switchLeft){
					AutoSimplify.goStraight(robot, 1.3*FEET, -180, 0.4);
				}
				else{
					AutoSimplify.goStraight(robot, 3.5*FEET, -30, 0.4);
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				}
				break;
			case 14:
				if(!robot.switchLeft){
					AutoSimplify.launchCube(robot);
				}
				else{
					AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
					AutoSimplify.openGrabber(robot);
					robot.driveRobot(0, 0);
				}
				break;
			case 15:
				if(!robot.switchLeft){
					AutoSimplify.goStraight(robot, 1.1*FEET, 180, -0.4);
				}
				else{
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SCALE);
				robot.driveRobot(0, 0);
				}
				break;

			}
		}
		
		public static void rightFarSideStart(Robot us){
			switch (us.autoStep)
			{
			case 1: 
				AutoSimplify.goStraight(us, 16.6 * FEET, 0, 0.9); //206.173 in
				AutoSimplify.deployGrabber(us);
				break;
			case 2:
				AutoSimplify.autoPIDTurn(us, -89);
				AutoSimplify.deployGrabber(us);
				break;
			case 3:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.goStraight(us, 14.3 * FEET, -90, 0.7); //176.727 in
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				break;
			case 4:
				AutoSimplify.autoPIDTurn(us, 3);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				break;
			case 5:
				AutoSimplify.goStraight(us, 2.3 * FEET, 3, 0.4); //38.182 in
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				break;
			case 6:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				AutoSimplify.launchCube(us);
				break;
			case 7:
				AutoSimplify.goStraight(us, 2.1 * FEET, 0, -0.4); //36.364 in
				AutoSimplify.lowerElevator(us, us.HEIGHT_ABOVE_CUBE);
				break;
			case 8:
				AutoSimplify.autoPIDTurn(us, 165);
				AutoSimplify.lowerElevator(us, us.HEIGHT_ABOVE_CUBE);
				//us.driveRobot(0, 0);
				break;
			case 9:
				AutoSimplify.openGrabber(us);
				AutoSimplify.downElevatorStep(us, us.BOTTOM_HEIGHT);
				break;
			case 10:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, .75*FEET, 165, 0.4);
				break;
			case 11:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.grabCube(us);
				break;
			case 12:
				AutoSimplify.upElevatorStep(us, us.HEIGHT_FOR_SWITCH);
				break;
			case 13:
				if(us.switchLeft){
					AutoSimplify.goStraight(us, 1.3*FEET, 165, 0.4);
				}
				else{
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.autoPIDTurn(us, 0);
				}
				break;
			case 14:
				if(us.switchLeft){
					us.driveRobot(0, 0);
					AutoSimplify.launchCube(us);
				}
				else{
					us.driveRobot(0,0);
				}
				break;
			case 15:
				if(us.switchLeft){
					us.driveRobot(0, 0);
				}
				else{
					us.driveRobot(0,0);
				}
				break;
			}
		}
		
		public static void leftFarSideStart(Robot us){
			switch (us.autoStep)
			{
			case 1: 
				AutoSimplify.goStraight(us, 16.6 * FEET, 0, 0.9); //206.173 in
				AutoSimplify.deployGrabber(us);
				break;
			case 2:
				AutoSimplify.autoPIDTurn(us, 89);
				AutoSimplify.deployGrabber(us);
				break;
			case 3:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.goStraight(us, 14.5 * FEET, 89, 0.7); //176.727 in
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				break;
			case 4:
				AutoSimplify.autoPIDTurn(us, -3);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				break;
			case 5:
				AutoSimplify.goStraight(us, 2.3 * FEET, -3, 0.4); //38.182 in
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				break;
			case 6:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
				AutoSimplify.launchCube(us);
				break;
			case 7:
				AutoSimplify.goStraight(us, 2.1 * FEET, 0, -0.4); //36.364 in
				AutoSimplify.lowerElevator(us, us.HEIGHT_ABOVE_CUBE);
				break;
			case 8:
				AutoSimplify.autoPIDTurn(us, -165);
				AutoSimplify.lowerElevator(us, us.HEIGHT_ABOVE_CUBE);
				//us.driveRobot(0, 0);
				break;
			case 9:
				AutoSimplify.openGrabber(us);
				AutoSimplify.downElevatorStep(us, us.BOTTOM_HEIGHT);
				break;
			case 10:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, .75*FEET, -165, 0.4);
				break;
			case 11:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.grabCube(us);
				break;
			case 12:
				AutoSimplify.upElevatorStep(us, us.HEIGHT_FOR_SWITCH);
				break;
			case 13:
				if(!us.switchLeft){
					AutoSimplify.goStraight(us, 1.3*FEET, -165, 0.4);
				}
				else{
					AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
					AutoSimplify.autoPIDTurn(us, 0);
				}
				break;
			case 14:
				if(!us.switchLeft){
					us.driveRobot(0, 0);
					AutoSimplify.launchCube(us);
				}
				else{
					us.driveRobot(0,0);
				}
				break;
			case 15:
				if(!us.switchLeft){
					us.driveRobot(0,0);
				}
				else{
					us.driveRobot(0,0);
				}
				break;
			}
		}
	}

