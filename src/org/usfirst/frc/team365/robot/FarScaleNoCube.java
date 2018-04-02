package org.usfirst.frc.team365.robot;

public class FarScaleNoCube {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	public static void rightStart(Robot us) {
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
			AutoSimplify.goStraight(us, 2.5 * FEET, 3, 0.4); //38.182 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 6:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		}
	}
	
	public static void leftStart(Robot us) {
		switch (us.autoStep)
		{
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
			AutoSimplify.goStraight(us, 2.5 * FEET, -3, 0.4); //38.182 in
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		case 6:
			AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE);
			break;
		}
	}
}
