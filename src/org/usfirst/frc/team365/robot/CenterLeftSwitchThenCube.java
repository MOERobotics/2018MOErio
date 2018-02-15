package org.usfirst.frc.team365.robot;

public class CenterLeftSwitchThenCube
{
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
		switch(us.autoStep) {
		case 1:
			//goStraight(encoderTicks, navx setPoint, startPower - forwards/backwards is pos/neg)
			us.goStraight(2 * FEET, 0, 0.5);
			break;
			
		case 2:
			//turnToAngle(navx setPoint, maxPower)
			us.turnToAngle(-55, 1);
			break;
			
		case 3:
			us.goStraight(6 * FEET, -55, 0.5);
			break;
		
		case 4:
			us.turnToAngle(0, 1);
			break;
			
		case 5:
			us.goStraight(2.24 * FEET, 0, 0.5);
			us.autoTimer.reset();
			break;
			
		case 6:
			if (us.autoTimer.get() > 1.0) {
				us.autoTimer.reset();
			}
			break;
			
		case 7:
			us.goStraight(-4 * FEET, 0, -0.4);
			break;
			
		case 8: 
			us.turnToAngle(90, 1);
			break;
			
		case 9:
			us.goStraight(4.167 * FEET, 90, 0.4);
			break;
		
		case 10:
			us.turnToAngle(0, 1);
			break;
		
		}

		
		
	}

}
