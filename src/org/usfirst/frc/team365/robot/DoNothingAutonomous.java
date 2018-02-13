package org.usfirst.frc.team365.robot;

public class DoNothingAutonomous {
	static void doNothingRoutine(Robot ourselves) {
		ourselves.driveRobot(0,0);
	}
}

/* 
	1. Change method signature to (Robot ourselves)
	2. Prefix internal variables with ourselves.xxx
	3. Change method to static
	4. Prefix method call with class name, pass this ex. Robot.AutoMethod1(this)
	5. ???
	6. Profit!
*/