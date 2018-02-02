package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardPrint {

	static void printToSmartDashboard(Robot ourselves) {
		
		SmartDashboard.putNumber("distRight", ourselves.distanceR.getRaw());
		SmartDashboard.putNumber("distLeft", ourselves.distanceL.getRaw());
		SmartDashboard.putNumber("Yaw",ourselves.navX.getYaw());
		SmartDashboard.putNumber("accelerationX", ourselves.navX.getWorldLinearAccelX());
		SmartDashboard.putNumber("accelerationY", ourselves.navX.getWorldLinearAccelY());
		SmartDashboard.putNumber("sonarVoltage", ourselves.readSonar.getAverageVoltage());
		SmartDashboard.putNumber("autoRoutine", ourselves.autoRoutine);
		SmartDashboard.putNumber("autoStep", ourselves.autoStep);
		SmartDashboard.putNumber("kProp", ourselves.kProp);
		SmartDashboard.putNumber("kInt", ourselves.kInt);
		SmartDashboard.putNumber("kDer", ourselves.kDer);
		
	}
}
