package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.goStraight(robot, 10.25 * FEET, 0, .7);
			
			//if(robot.getEncoderMax() > 4 * FEET) {
				AutoSimplify.raiseElevator(robot, 2000);
			//}
			
			break;
		case 2:
			//turnToAngle(navxsetPoint, maxPower)
			AutoSimplify.deployGrabber(robot);
			AutoSimplify.raiseElevator(robot, 2000);
			AutoSimplify.autoPIDTurn(robot, -90);
			break;
		case 3:
			AutoSimplify.goStraight(robot, .5 * FEET, -90, .5);
			break;
		case 4:
			AutoSimplify.launchCube(robot);
			break;
		case 5:
			AutoSimplify.goStraight(robot, .5 * FEET, -90, -.5);
			break;
		case 6:
			AutoSimplify.autoPIDTurn(robot, -180);
			break;
		case 7:
			AutoSimplify.goStraight(robot, 6 * FEET, -180, -.5);
			AutoSimplify.lowerElevator(robot, 0);
			break;
		case 8:
			AutoSimplify.autoPIDTurn(robot, -135);
			robot.cubeClawOpen();
			break;
		case 9:
			AutoSimplify.goStraight(robot, 4 * FEET, -135, .5);
			break;
		case 10:
			AutoSimplify.grabCube(robot);
			break;
		default:
			robot.driveRobot(0,0);
			robot.driveWrist(0);
			robot.driveRoll(0);
			robot.driveElevator(0);
			break;
			
		}
		
		
	}
}
