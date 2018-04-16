package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

public class CenterLeftSwitchExchange {
	
	public static final double INCHES = Robot.INCHES_TO_ENCTICKS;
	public static final double FEET = 12 * INCHES;
	
	static void run(Robot us) {
		
		switch(us.autoStep) {
				
				case 1:
					AutoSimplify.deployGrabber(us);
					AutoSimplify.halfTurnLeft(us, -50, .8);
					break;
					
				case 2:
					AutoSimplify.deployGrabber(us);
					AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
					AutoSimplify.goStraight(us, 59 * INCHES, -50, .5);
					break;
					
				case 3:
					AutoSimplify.deployGrabber(us);
					AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
					AutoSimplify.halfTurnRight(us, -5, .8);
					break;
		
				case 4:
					AutoSimplify.deployGrabber(us);
					AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
					AutoSimplify.goStraight(us, 18 * INCHES, -2, .5);
					break;
					
				case 5:
					AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
					AutoSimplify.launchCube(us); 
					break;
					
				case 6:
					AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
					AutoSimplify.goStraight(us, 4.3 * FEET, 0, -0.5); //4.6
					//AutoSimplify.goStraght(us, 2.1 * FEET, 0, -0.5); Alternative Gentle turn routine
					break;
					
				case 7:
					AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
					AutoSimplify.openGrabber(us);
					AutoSimplify.autoPIDTurn(us, 90);
					//AutoSimplify.halfTurnLeft(us, -65, -0.8); (Alternative)
					break;
					
				case 8:
					AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
					AutoSimplify.openGrabber(us);
					AutoSimplify.goStraight(us, 4.3 * FEET, 90, 0.5); //4.3
					//AutoSimplify.goStraight(us, 3.1 * FEET, -65, -0.5); (Alternative)
					break;
				
				case 9:
					AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
					AutoSimplify.autoPIDTurn(us, 0);
					AutoSimplify.openGrabber(us);
					break;
					
				case 10:
					AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
					AutoSimplify.goStraight(us, 1.7 * FEET, 0, 0.4); //1.2
					//AutoSimplify.goStraight(us, 15*INCHES, 0, 0.4); (Alternative)
					break;
				
				case 11:
					AutoSimplify.grabCube(us); 
					break;
				
				//exchange code lol
				case 12:
					AutoSimplify.autoPIDStraight(us, 3 * FEET, 0, -0.6);
					break;
					
				case 13:
					AutoSimplify.autoPIDTurn(us, -160);
					break;
								
				case 14:
					AutoSimplify.autoPIDStraight(us, 3 * FEET, -160, 0.6);
					break;
					
				case 15:
					AutoSimplify.autoPIDTurn(us, 180);
					break;
					
				case 16:
					AutoSimplify.autoPIDStraight(us, 2 * INCHES, 180, 0.5);
					break;
					
				case 17: 
					us.driveRobot(0, 0);
					break;		
				
		}
		
	}
	
	static void run2(Robot us) {
		
		switch(us.autoStep) {
		
			case 1:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.halfTurnLeft(us, -50, .8);
				break;
				
			case 2:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 59 * INCHES, -50, .5);
				break;
				
			case 3:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.halfTurnRight(us, -5, .8);
				break;
	
			case 4:
				AutoSimplify.deployGrabber(us);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.goStraight(us, 18 * INCHES, -2, .5);
				break;
				
			case 5:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.launchCube(us); 
				break;
				
			case 6:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 4.3 * FEET, 0, -0.5); 
				break;
				
			case 7:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.autoPIDTurn(us, 90);
				break;
				
			case 8:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.openGrabber(us);
				AutoSimplify.goStraight(us, 4.3 * FEET, 90, 0.5); 
				break;
			
			case 9:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.autoPIDTurn(us, 0);
				AutoSimplify.openGrabber(us);
				break;
				
			case 10:
				AutoSimplify.lowerElevator(us, us.BOTTOM_HEIGHT);
				AutoSimplify.goStraight(us, 1.7 * FEET, 0, 0.4); 
				break;
			
			case 11:
				AutoSimplify.grabCube(us); 
				break;
				
			case 12:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.autoPIDTurn(us, -75); 
				break;
				
			case 13:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.autoPIDStraight(us, 3.2 * FEET, -75, 0.6); 
				break;	
				
			case 14:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.halfTurnRight(us, -5, 0.8);
				break;
				
			case 15:
				AutoSimplify.goStraight(us, 1.0 * FEET, -2, 0.5);
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				break;
				
			case 16:
				AutoSimplify.raiseElevator(us, us.HEIGHT_FOR_SWITCH);
				AutoSimplify.launchCube(us); 
				break;
				
			case 17:
				AutoSimplify.autoPIDStraight(us, 5 * FEET, 0, -0.8);
				break;
				
			case 18:
				AutoSimplify.autoPIDTurn(us, 90);
				break;
				
			case 19:
				AutoSimplify.autoPIDStraight(us, 25 * INCHES, 90, 0.6);
				break;
				
			case 20:
				AutoSimplify.autoPIDTurn(us, 180);
				break;
				
			case 21:
				if (us.encoderWrist.getRaw() < 1100) {
					us.wrist.set(ControlMode.PercentOutput, 0);
				}
				else us.wrist.set(ControlMode.PercentOutput, 0.9); 
	
				
			case 22:
				us.driveRobot(0, 0);
				break;
			
		}
	}

}
