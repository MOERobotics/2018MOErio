package org.usfirst.frc.team365.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardUtil {

	private static Timer smartDashboardTimer = new Timer() {{
		start();
	}};

	public static void dashboardInit(Robot ourselves) {
		setDefaultValues(ourselves);
		printToSmartDashboard(ourselves);
//		getFromSmartDashboard(ourselves);
	}

	public static void dashboardPeriodic(Robot ourselves) {

		if (smartDashboardTimer.hasPeriodPassed(0.3)) {
			printToSmartDashboard(ourselves);
//			getFromSmartDashboard(ourselves);
			smartDashboardTimer.reset();
//			smartDashboardTimer.start();
		}

	}

	static void setDefaultValues(Robot ourselves) {
		SmartDashboard.putNumber("straightP", ourselves.straightP);
		SmartDashboard.putNumber("straightI", ourselves.straightI);
		SmartDashboard.putNumber("straightD", ourselves.straightD);
		SmartDashboard.putNumber(    "turnP", ourselves.turnP    );
		SmartDashboard.putNumber(    "turnI", ourselves.turnI    );
		SmartDashboard.putNumber(    "turnD", ourselves.turnD    );
	}

	static void getFromSmartDashboard(Robot ourselves) {
		ourselves.straightP = SmartDashboard.getNumber("straightP", ourselves.straightP);
		ourselves.straightI = SmartDashboard.getNumber("straightI", ourselves.straightI);
		ourselves.straightD = SmartDashboard.getNumber("straightD", ourselves.straightD);
		ourselves.turnP     = SmartDashboard.getNumber(    "turnP", ourselves.turnP    );
		ourselves.turnI     = SmartDashboard.getNumber(    "turnI", ourselves.turnI    );
		ourselves.turnD     = SmartDashboard.getNumber(    "turnD", ourselves.turnD    );
	}

	static void printToSmartDashboard(Robot ourselves) {
		SmartDashboard.putString(           "status", ourselves.statusMessage                 );
		//parts
		SmartDashboard.putNumber(        "distRight", ourselves.encoderR.getRaw()            );
		SmartDashboard.putNumber(         "distLeft", ourselves.encoderL.getRaw()            );
		SmartDashboard.putNumber(    "encoderWrist", ourselves.encoderWrist.getRaw());
		SmartDashboard.putNumber( "encoderElevator", ourselves.encoderElevator.getRaw()         );
		SmartDashboard.putNumber(  "driveOutputLeft", ourselves.driveOutputLeft               );
		SmartDashboard.putNumber( "driveOutputRight", ourselves.driveOutputRight              );
		SmartDashboard.putNumber(   "elevatorOutput", ourselves.elevatorOutput                );
		SmartDashboard.putString(    "shifterStatus", ourselves.shifterStatus                 );
		SmartDashboard.putString(	"GameData:", ourselves.gameData							);
		SmartDashboard.putBoolean("rightTape", ourselves.rightTape.get());
		SmartDashboard.putBoolean("leftTape", ourselves.leftTape.get());
		SmartDashboard.putBoolean("autoTape: ", ourselves.autoTape);
		//navx
		SmartDashboard.putNumber(              "Yaw", ourselves.navX.getYaw()                 );
		SmartDashboard.putNumber(              "Pitch", ourselves.navX.getPitch()             );
		SmartDashboard.putNumber(              "Roll", ourselves.navX.getRoll()               );
//		SmartDashboard.putNumber(    "accelerationX", ourselves.navX.getWorldLinearAccelX()   );
//		SmartDashboard.putNumber(    "accelerationY", ourselves.navX.getWorldLinearAccelY()   );

		//auto
		SmartDashboard.putNumber(      "autoRoutine", ourselves.autoRoutine                   );
		SmartDashboard.putNumber(         "autoStep", ourselves.autoStep                      );
		
		if(ourselves.autoRoutine == 1){
			if (ourselves.secondCubeSelect == 1) SmartDashboard.putString("secondCube", "switch");
			if (ourselves.secondCubeSelect == 2) SmartDashboard.putString("secondCube", "exchange");
			if (ourselves.secondCubeSelect == 3) SmartDashboard.putString("secondCube", "scaleReady");
		}
		else {
			SmartDashboard.putString("secondCube", "N/A!");
		}
		
		SmartDashboard.putNumber(        "AutoTimer", ourselves.autoTimer.get()               );
		SmartDashboard.putBoolean("Top limit switch: ", ourselves.elevatorTopLimitSwitch.get());
		SmartDashboard.putBoolean("Bottom limit switch", ourselves.elevatorBottomLimitSwitch.get());
		SmartDashboard.putNumber("Gripper current left", ourselves.rollLeft.getOutputCurrent());
		SmartDashboard.putNumber("Gripper current right", ourselves.rollRight.getOutputCurrent());
		SmartDashboard.putNumber("Roll: ", ourselves.navX.getRoll());
		SmartDashboard.putNumber("Pitch: ", ourselves.navX.getPitch());

	}
}
