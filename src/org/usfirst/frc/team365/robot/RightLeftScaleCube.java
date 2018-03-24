package org.usfirst.frc.team365.robot;
/*
 * @Author: Arya Shajpaul
 * Far scale auto routine
 */
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
			AutoSimplify.goStraight(us, 16.1 * FEET, 0, 0.9); //206.173 in
			AutoSimplify.deployGrabber(us);
			break;
		case 2:
			AutoSimplify.autoPIDTurn(us, -90);
			AutoSimplify.deployGrabber(us);
			break;
		case 3:
			AutoSimplify.deployGrabber(us);
			AutoSimplify.goStraight(us, 14.5 * FEET, -90, 0.7); //176.727 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 4:
			AutoSimplify.autoPIDTurn(us, 3);
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 5:
			AutoSimplify.goStraight(us, 3 * FEET, 3, 0.4); //38.182 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 6:
			us.driveRoll(0.3);
			break;
		case 7:
			AutoSimplify.goStraight(us, 3 * FEET, 3, -0.4); //36.364 in
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			break;
		case 8:
			//AutoSimplify.autoPIDTurn(us, 180);
			AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
			us.driveRobot(0, 0);
			break;
		case 9:
			//AutoSimplify.goStraight(us, 0 * FEET, 180, 0.4); //13.636 in
			us.driveRobot(0, 0);
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
		
		switch (us.autoStep){
	case 1: 
		AutoSimplify.goStraight(us, 16.1 * FEET, 0, 0.9); //206.173 in
		AutoSimplify.deployGrabber(us);
		break;
	case 2:
		AutoSimplify.autoPIDTurn(us, 90);
		AutoSimplify.deployGrabber(us);
		break;
	case 3:
		AutoSimplify.deployGrabber(us);
		AutoSimplify.goStraight(us, 14.5 * FEET, 90, 0.7); //176.727 in
		AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
		break;
	case 4:
		AutoSimplify.autoPIDTurn(us, -3);
		AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
		break;
	case 5:
		AutoSimplify.goStraight(us, 3 * FEET, -3, 0.4); //38.182 in
		AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
		break;
	case 6:
		us.driveRoll(0.3);
		break;
	case 7:
		AutoSimplify.goStraight(us, 3 * FEET, -3, -0.4); //36.364 in
		AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
		break;
	case 8:
		//AutoSimplify.autoPIDTurn(us, 180);
		AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
		us.driveRobot(0, 0);
		break;
	case 9:
		//AutoSimplify.goStraight(us, 0 * FEET, 180, 0.4); //13.636 in
		us.driveRobot(0, 0);
		AutoSimplify.lowerElevator(us, us.HEIGHT_FOR_SWITCH);
		AutoSimplify.openGrabber(us);
		break;
	case 10:
		us.driveRobot(0, 0);
		break;	
	}
	}
}
