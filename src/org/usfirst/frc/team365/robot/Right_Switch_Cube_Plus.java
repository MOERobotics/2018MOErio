package org.usfirst.frc.team365.robot;

public class Right_Switch_Cube_Plus {
	static void run(Robot robot) {
		switch(robot.autoStep) {
		case 1:
			robot.goStraight(32500 , 0, .7);
			//change to 33000 for real
			//300 inches
			break;
		case 2:
			robot.autoPIDTurn(-90);
			break;
			//new case that raises elevator to the top
			//case after that that moves rollers out to throw cube
		case 3: 
			robot.autoPIDTurn(180);
			//elevator down to bottom
		case 4:
			robot.goStraight(6000 , -180, .5);
			//54.55 inches
			break;
		case 5:

			robot.autoPIDTurn(-90);		

			break;

		case 6:
			robot.goStraight(8800 , -90, .5);
			//80 inches
			break;

		case 7:	

			robot.autoPIDTurn(-180);

			break;
		case 8:

			robot.goStraight(4000 , -180, .5);
			//36.36 inches
			// rollers in to grab cube
			break;

		case 9:
			robot.driveRobot (0.0,0.0);

			break;
		}



	}
}
