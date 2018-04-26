package org.usfirst.frc.team365.robot;



import com.ctre.phoenix.motorcontrol.ControlMode;

public class LeftSwitch_Exchange {
	


	 
		
		public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
		public static final double FEET = 12 * INCHES;
		
		static void run(Robot robot) {
		
			switch(robot.autoStep) {
			case 1:
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				AutoSimplify.deployGrabber(robot);
				AutoSimplify.halfTurnLeft(robot, -45, 0.8);
				break;
				
			case 2:
				AutoSimplify.deployGrabber(robot);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(robot, 57 * INCHES, -45, 0.5); //60
				break;
				
			case 3:
				AutoSimplify.deployGrabber(robot);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				AutoSimplify.halfTurnRight(robot, 0, 0.8);
				break;

			case 4:
				AutoSimplify.deployGrabber(robot);
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(robot, 18 * INCHES, 0, 0.5); //15
				break;
				
			case 5:
				AutoSimplify.raiseElevator(robot, robot.HEIGHT_FOR_SWITCH);
				AutoSimplify.launchCube(robot); 
				break;
				
			case 6:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(robot, 1.6 * FEET, 0, -0.5); 
				break;
				
			case 7:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(robot);
				AutoSimplify.halfTurnLeft(robot, -65, -0.8);
				break;
				
			case 8:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(robot);
				AutoSimplify.goStraight(robot, 3.1 * FEET, -65, -0.5); 
				break;
			
			case 9:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(robot, 0);
				AutoSimplify.openGrabber(robot);
				break;
				
			case 10:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(robot, 15.5 * INCHES, 0, 0.4); //15
				break;
			
			case 11:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.grabCube(robot); 
				break;
			
		
			case 12	:
				AutoSimplify.autoPIDTurn(robot, -125);
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				break;
			case 13:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDStraight(robot, 2*FEET, -125, 0.4);
				break;
			case 14:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.halfTurnLeft(robot, -170, 0.8);
				break;
			case 15:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDStraight(robot, 5*INCHES, -175, 0.3);
				
			break;
			case 16:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				AutoSimplify.launchExCube(robot);
				break;
			case 17:
				AutoSimplify.lowerElevator(robot, robot.BOTTOM_HEIGHT);
				robot.driveRobot(0,0);
				break;
				
				
			}
			
		}
		
	}	

	/* some stuff:
	 * 	time to drop cube: 13-ish sec
	 * 	extra 2-ish sec to 
	 * 	
	 */


