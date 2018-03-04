package org.usfirst.frc.team365.robot;

public class Right_Switch_Cube_Plus {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	public static void Right_Switch_Cube_Plus(Robot robot) {
		switch(robot.autoStep) {
		

		case 1:
			AutoSimplify.goStraight(robot, 23000, 0, 0.6);
			break;
			
		case 2:
			AutoSimplify.turnToAngle(robot, -30, 0.6);
			break;
			
		case 3:
			AutoSimplify.goStraight(robot, 3500, -30, 0.6);
			robot.autoTimer.reset();
			break;
		
		case 4:
			// Elevator up and rollers out
			if(robot.autoTimer.get() > 1)
			{
				robot.autoStep = 5;
			}
			else
			{
				robot.driveRobot(0,0);
			}
			break;
			
		case 5: 
			
			AutoSimplify.goStraight(robot, 3500, -30, -0.6);
			break;
		case 6:
			
			AutoSimplify.turnToAngle(robot, -150, 0.6);
			break;
			
		case 7:
			AutoSimplify.goStraight(robot, 3500, -150, 0.6);
			break;
		case 8:
			
			AutoSimplify.turnToAngle(robot, -90, 0.6);
			break;
		case 9:
			AutoSimplify.goStraight(robot, 4000, -90, 0.6);
		
		break;
		
		case 10:
			AutoSimplify.turnToAngle(robot, 180, 0.6);
			break;
		case 11:
			AutoSimplify.goStraight(robot, 1500, 180, 0.4);
			robot.autoTimer.reset();
			break;
		case 12:
			//grabber rollers in
			if(robot.autoTimer.get() > 1)
			{
				robot.autoStep = 5;
			}
			else
			{
				robot.driveRobot(0,0);
			}
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