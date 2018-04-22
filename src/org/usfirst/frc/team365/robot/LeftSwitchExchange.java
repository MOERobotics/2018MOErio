package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class LeftSwitchExchange {
		
		public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
		public static final double FEET = 12 * INCHES;
	
		static void run(Robot us) {
			
			switch (us.autoStep) {
			
			case 1:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.halfTurnLeft(us, -45, 0.8);
				break;
				
			case 2:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 57 * INCHES, -45, 0.5); //60
				break;
				
			case 3:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.halfTurnRight(us, 0, 0.8);
				break;

			case 4:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 18 * INCHES, 0, 0.5); //15
				break;
				
			case 5:
				AutoSimplify.launchCube(us); 
				break;
				
			case 6:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 1.6 * FEET, 0, -0.5); 
				break;
				
			case 7:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.halfTurnLeft(us, -65, -0.8);
				break;
				
			case 8:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.goStraight(us, 3.1 * FEET, -65, -0.5); 
				break;
			
			case 9:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(us, 0);
				AutoSimplify.openGrabber(us);
				break;
				
			case 10:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 15.5 * INCHES, 0, 0.4); //15
				break;
			
			case 11:
				AutoSimplify.grabCube(us); 
				break;
				
			case 12:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.autoPIDTurn(us, -75); //-70
				break;
				
			case 13:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 3.6 * FEET, -70, 0.8); //3ft
				break;	
				
			case 14:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.halfTurnRight(us, -5, 0.8); 
				break;
				
			case 15:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 0.6 * FEET, -5, 0.7); //1ft
				break;
				
			case 16:
				AutoSimplify.launchCube(us); 
				break;
				
			case 17:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 1.6 * FEET, 0, -0.5); 
				break;
				
			case 18:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.halfTurnLeft(us, -65, -0.8);
				break;
				
			case 19:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.goStraight(us, 3.1 * FEET, -65, -0.5); 
				break;
			
			case 20:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(us, 0);
				AutoSimplify.openGrabber(us);
				break;
				
			case 21:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 19 * INCHES, 0, 0.4); //15.5
				break;
				
			case 22:
				AutoSimplify.grabCube(us);
				break;
				
			case 23:
				AutoSimplify.autoPIDTurn(us, -125);
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				break;
				
			case 24:
				AutoSimplify.autoPIDStraight(us, 2.3 * FEET, -125, 0.6);
				break;
				
			case 25:
				AutoSimplify.halfTurnLeft(us, -170, 0.8);
				break;
				
			case 26:
				AutoSimplify.autoPIDStraight(us, 5 * INCHES, -175, 0.7);
				break;
			
			case 27:
				AutoSimplify.dropCube(us);
				break;
			
			case 28:
				AutoSimplify.goStraight(us, 0.5 * FEET, 0, -0.5);
				if (us.encoderWrist.getRaw() < 1100) {
					us.wrist.set(ControlMode.PercentOutput, 0);
				}
				else us.wrist.set(ControlMode.PercentOutput, 0.9);
				us.driveRobot(0,0);
				break;
			
			
			}
		}
		
	}	


