package org.usfirst.frc.team365.robot;

public class RightLeftScaleCube {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us)
	{
		/**
		 * Forward: 210 inches
		 * Left (-90)
		 * Forward: 180 inches
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
			AutoSimplify.goStraight(us, 22668, 0, 0.7);
			break;
		case 2:
			AutoSimplify.turnToAngle(us, -90, 0.8);
			break;
		case 3:
			AutoSimplify.goStraight(us, 19440, -90, 0.5);
			break;
		case 4:
			AutoSimplify.turnToAngle(us, 0, 0.8);
			break;
		case 5:
			AutoSimplify.goStraight(us, 4200, 0, 0.4);
			break;
		case 6:
			AutoSimplify.pause(us, 1);
		case 7:
			AutoSimplify.goStraight(us, 4000, 0, -0.4);
			break;
		case 8:
			AutoSimplify.turnToAngle(us, 180, 0.8);
			break;
		case 9:
			AutoSimplify.goStraight(us, 1500, 180, 0.4);
			break;
		case 10:
			us.driveRobot(0, 0);
			break;	
		}
	}

}
