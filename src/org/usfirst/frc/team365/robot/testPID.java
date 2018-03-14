package org.usfirst.frc.team365.robot;

public class testPID {
//Turn to angle 90 
	public static void run(Robot us){
		switch(us.autoStep){
		case 1:
			AutoSimplify.turnToAngle(us, 90);
			break;
		case 2:
			us.driveRobot(0, 0);
			break;
		}
	}
}
