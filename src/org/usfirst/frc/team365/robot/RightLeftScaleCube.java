package org.usfirst.frc.team365.robot;

public class RightLeftScaleCube {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us)
	{
		/**
		 * Forward: 210 inches
		 * Left (-90)
		 * Forward: 185 inches
		 * Right (0)
		 * Forward: 39 inches 
		 * Backwards (0)
		 * Backward: 37 inches
		 * Turn (180)
		 * Forward: 14 inches
		 * 
		 */
		
		switch (us.autoStep)
		{
		case 1: 
			AutoSimplify.goStraight(us, 17.173 * FEET, 0, 0.7); //206.173 in
			break;
		case 2:
			AutoSimplify.turnToAngle(us, -90, 0.8);
			break;
		case 3:
			AutoSimplify.goStraight(us, 14.727 * FEET, -90, 0.5); //176.727 in
			break;
		case 4:
			AutoSimplify.turnToAngle(us, 0, 0.8);
			break;
		case 5:
			AutoSimplify.goStraight(us, 3.182 * FEET, 0, 0.4); //38.182 in
			break;
		case 6:
			AutoSimplify.pause(us, 1);
		case 7:
			AutoSimplify.goStraight(us, 3.03 * FEET, 0, -0.4); //36.364 in
			break;
		case 8:
			AutoSimplify.turnToAngle(us, 180, 0.8);
			break;
		case 9:
			AutoSimplify.goStraight(us, 1.136 * FEET, 180, 0.4); //13.636 in
			break;
		case 10:
			us.driveRobot(0, 0);
			break;	
		}
	}

}
