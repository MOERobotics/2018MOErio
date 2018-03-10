package org.usfirst.frc.team365.robot;

public class RightLeftScaleCube {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void rightStart(Robot us)
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
			AutoSimplify.goStraight(us, 16.666 * FEET, 0, 0.7); //206.173 in
			AutoSimplify.deployGrabber(us);
			break;
		case 2:
			AutoSimplify.autoPIDTurn(us, -90);
			AutoSimplify.deployGrabber(us);
			break;
		case 3:
			AutoSimplify.goStraight(us, 14.727 * FEET, -90, 0.5); //176.727 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 4:
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 5:
			AutoSimplify.goStraight(us, 3.182 * FEET, 0, 0.4); //38.182 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 6:
			AutoSimplify.launchCube(us);
			break;
		case 7:
			AutoSimplify.goStraight(us, 2.5 * FEET, 0, -0.4); //36.364 in
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 8:
			AutoSimplify.autoPIDTurn(us, 180);
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 9:
			AutoSimplify.goStraight(us, 0 * FEET, 180, 0.4); //13.636 in
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.openGrabber(us);
			break;
		case 10:
			us.driveRobot(0, 0);
			break;	
		}
	}
	
	static void leftStart(Robot us)
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
			AutoSimplify.goStraight(us, 16.666 * FEET, 0, 0.7); //206.173 in
			AutoSimplify.deployGrabber(us);
			break;
		case 2:
			AutoSimplify.autoPIDTurn(us, 90);
			AutoSimplify.deployGrabber(us);
			break;
		case 3:
			AutoSimplify.goStraight(us, 14.727 * FEET, 90, 0.5); //176.727 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 4:
			AutoSimplify.autoPIDTurn(us, 0);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 5:
			AutoSimplify.goStraight(us, 3.182 * FEET, 0, 0.4); //38.182 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 6:
			AutoSimplify.launchCube(us);
			break;
		case 7:
			AutoSimplify.goStraight(us, 2.5 * FEET, 0, -0.4); //36.364 in
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 8:
			AutoSimplify.autoPIDTurn(us, 180);
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 9:
			AutoSimplify.goStraight(us, 0 * FEET, 180, 0.4); //13.636 in
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			AutoSimplify.openGrabber(us);
			break;
		case 10:
			us.driveRobot(0, 0);
			break;	
		}
	}
}
