package org.usfirst.frc.team365.robot;

public class Right_Switch_Cube_Plus {
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;

	static void run(Robot robot) {
	switch(robot.autoStep) {
	case 1:
	AutoSimplify.goStraight(robot,300*INCHES , 0, .7);
	//change to 33000 for real
	//300 inches
		break;

	case 2:
		AutoSimplify.autoPIDTurn(robot,-90);
		break;

	case 3: 
		AutoSimplify.autoPIDTurn(robot,180);
		break;

	case 4:
		AutoSimplify.goStraight(robot, 54.55*INCHES , -180, .5);
		//54.55 inches
		break;

	case 5:
		AutoSimplify.autoPIDTurn(robot,-90);		
		break;
		
	case 6:
		AutoSimplify.goStraight(robot,80*INCHES, -90, .5);
		//80 inches
		break;
		
	case 7:	
		AutoSimplify.autoPIDTurn(robot,-180);
		break;
		
	case 8:
		AutoSimplify.goStraight(robot,36.36*INCHES , -180, .5);
		//36.36 inches
		break;

	case 9:
		robot.driveRobot(0.0,0.0);
		break;
	}
}
}
