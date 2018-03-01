package org.usfirst.frc.team365.robot;

public class RightScaleSwitch {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	public static void run(Robot us) {
		
		switch (us.autoStep) {
		
			case 1:
				us.goStraight(23000, 0, 0.6);
				break;
				
			case 2:
				us.turnToAngle(-30, 0.6);
				break;
				
			case 3:
				us.goStraight(3500, -30, 0.6);
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
				us.turnToAngle(-150, 0.6);
				break;
				
			case 6:
				us.goStraight(4400, -150, 0.6);
				break;
				
			case 7:
				us.turnToAngle(-180, 0.6);
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
				us.goStraight(1000, -180, 0.5);
				break;
				
		
		}
	}


}
