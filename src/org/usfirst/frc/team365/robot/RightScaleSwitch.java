package org.usfirst.frc.team365.robot;

public class RightScaleSwitch {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	public static void run(Robot us) {
		
		switch (us.autoStep) {
		
			case 1:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE); 
				AutoSimplify.deployGrabber(us);
				AutoSimplify.goStraight(us, 17.0 * FEET, 0, 0.6); //209.091 in.
				break;
				
			case 2:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE); 
				AutoSimplify.deployGrabber(us);
				AutoSimplify.autoPIDTurn(us, -30);
				//AutoSimplify.turnToAngle(us, -30, 0.6);
				break;
				
			case 3:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SCALE); 
				AutoSimplify.deployGrabber(us);
				AutoSimplify.goStraight(us, 2.3 * FEET, -30, 0.6); //31.812 in.
				us.autoTimer.reset();
				break;
			
			case 4:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(us, -150);
				//AutoSimplify.turnToAngle(us, -150, 0.6);
				break;
				
			case 5:
				AutoSimplify.launchCube(us);
				break;
				
			case 6:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(us, -180);
				//AutoSimplify.turnToAngle(us, -180, 0.6);
				us.autoTimer.reset();
				break;
				
			case 7:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 3 * FEET, -150, 0.6); //40 in
				break;
				
				
			case 8:
				AutoSimplify.grabCube(us);
				break;
				
			case 9:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, .55 * FEET, -180, 0.5); //9.091 in.
				break;
				
			case 10:
				AutoSimplify.launchCube(us);
				break;
				
			case 11:
				us.driveRobot(0, 0);
				break;
				
		}
	}


}
