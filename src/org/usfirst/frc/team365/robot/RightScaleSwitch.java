package org.usfirst.frc.team365.robot;

public class RightScaleSwitch {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	public static void run(Robot us) {
		
		switch (us.autoStep) {
		
			case 1:
				AutoSimplify.goStraight(us, 23000, 0, 0.6);
				break;
				
			case 2:
				AutoSimplify.turnToAngle(us, -30, 0.6);
				break;
				
			case 3:
				AutoSimplify.goStraight(us, 3500, -30, 0.6);
				us.autoTimer.reset();
				break;
				
			case 4:
				if(us.autoTimer.get() > 1)
				{
					us.autoStep = 5;
				}
				else
				{
					us.driveRobot(0,0);
				}
				break;
			
			case 5:
				AutoSimplify.turnToAngle(us, -150, 0.6);
				break;
				
			case 6:
				AutoSimplify.goStraight(us, 4400, -150, 0.6);
				break;
				
			case 7:
				AutoSimplify.turnToAngle(us, -180, 0.6);
				us.autoTimer.reset();
				break;
				
			case 8:
				if(us.autoTimer.get() > 1)
				{
					us.autoStep = 9;
				}
				else
				{
					us.driveRobot(0,0);
				}
				break;
				
			case 9:
				AutoSimplify.goStraight(us, 1000, -180, 0.5);
				break;
				
		
		}
	}


}
