package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;


	static void autoGoStraightTurnTest(Robot ourselves) {
	

		switch(ourselves.autoStep) {
		case 1:
//			ourselves.goStraight(7.576 * FEET, 0, 0.6); //90.901 in
//			ourselves.autoPIDStraight(9.849 * FEET, 0, 0.7); //118.182 in
			break;
		case 2:
//			ourselves.turnToAngle(90.0, 0.7);
//			ourselves.autoPIDTurn(90.0);
			break;
		case 3:
//			ourselves.goStraight(6.061 * FEET, 90, 0.6); //72.723 in
//			ourselves.autoPIDStraight(4.545 * FEET, 90, 0.6); //54.545 in
			break;
		case 4:
			ourselves.driveRobot(0, 0);

		}
		
		if (ourselves.autoTimer.get() > 0.5) {
			SmartDashboard.putNumber("straightCorrection", ourselves.driveStraightCorrection.correctionValue);
			SmartDashboard.putNumber("turnCorrection", ourselves.turnRobotCorrection.correctionValue);
			ourselves.autoTimer.reset();
		}
	}

}
