package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class GoStraightAutonomous {


	static void autoGoStraightTurnTest(Robot ourselves) {
	

		switch(ourselves.autoStep) {
		case 1:
//			ourselves.goStraight(10000, 0, 0.6);
			ourselves.autoPIDStraight(13000,0,0.7);
			break;
		case 2:
//			ourselves.turnToAngle(90.0, 0.7);
			ourselves.autoPIDTurn(90.0);
			break;
		case 3:
//			ourselves.goStraight(8000, 90, 0.6);
			ourselves.autoPIDStraight(6000, 90, 0.6);
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
