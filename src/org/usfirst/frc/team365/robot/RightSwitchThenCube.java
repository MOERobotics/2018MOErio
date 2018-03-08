package org.usfirst.frc.team365.robot;

public class RightSwitchThenCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			AutoSimplify.goStraight(robot, 12.5 * FEET, 0, .7);
			
			if(AutoSimplify.deployGrabber(robot)) AutoSimplify.setElevator(robot, 2000);
			
			break;
		case 2:
			//turnToAngle(navxsetPoint, maxPower)
			AutoSimplify.setElevator(robot, 2000);
			
			robot.driveRobot(-.6, .6);
			
			if(robot.navX.getYaw() < -80) {
				robot.rollOut();
				robot.autoStep++;
			}
			break;
		case 3:
			AutoSimplify.turnToAngle(robot, -180);
			break;
		case 4:
			robot.driveRoll(0);
			AutoSimplify.setElevator(robot, 0);
			AutoSimplify.goStraight(robot, 7 * FEET, -180, -.7);

			break;
		case 5:
			robot.cubeClawOpen();
			robot.rollIn();
			AutoSimplify.setElevator(robot, 0);
			AutoSimplify.turnToAngle(robot, -145);

			break;
		case 6:
			AutoSimplify.goStraight(robot, 2 * FEET, -145, .5);
			break;
		case 7:
			robot.cubeClawClose();
			robot.autoStep++;
			break;
		case 8:
			AutoSimplify.setElevator(robot, 2000);
			AutoSimplify.goStraight(robot, 4 * INCHES, -145, -.5);
			break;
		case 9:
			robot.driveRoll(0);
			AutoSimplify.setElevator(robot, 2000);
			AutoSimplify.goStraight(robot, 1 * FEET, -90, .5);
			break;
		case 10:
			AutoSimplify.launchCube(robot);
			break;
		default:
			robot.driveRobot(0, 0);
			robot.driveRoll(0);
			robot.driveWrist(0);
			robot.driveElevator(0);
			break;
			
		}
		
		
	}
}
