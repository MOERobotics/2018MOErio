package org.usfirst.frc.team365.robot;

import com.ctre.phoenix.motorcontrol.ControlMode;

import edu.wpi.first.wpilibj.PIDController;

public class AutoSimplify{
		/******************
		 * Vasista's Auto Simplificatorator *
		 ******************/

		static void goStraight(Robot us, double ticks, double setPoint, double power) {
			if (us.newPID) {
				us.resetEncoders();
				us.driveStraight.reset();
				us.driveStraight.setSetpoint(setPoint);
				us.driveStraight.enable();
				us.newPID = false;
			}

			if (Math.abs(us.getEncoderMax()) > ticks) {
				us.driveRobot(0, 0);
				us.driveStraight.reset();
				us.autoTimer.reset();
				us.autoStep++;
				us.newPID = true;
			} else {
				us.driveRobot(power + us.driveStraightCorrection.correctionValue,
						power - us.driveStraightCorrection.correctionValue);
			}
		}

		public static void turnToAngle(Robot us, double angle, double maxPower, double tolerance) {
			if (us.newPID) {
				us.turnRobot.setAbsoluteTolerance(tolerance);
			}
			turnToAngle(us, angle, maxPower);
		}

		public static void turnToAngle(Robot us, double angle, double maxPower) {
			if (us.newPID) {
				us.resetEncoders();
				us.turnRobot.reset();
				us.turnRobot.setSetpoint(angle);
				us.turnRobot.setOutputRange(-Math.abs(maxPower), Math.abs(maxPower));
				us.turnRobot.enable();
				us.turnOnTargetCount = 0;
				us.newPID = false;
			}

			if (us.turnRobot.onTarget()) {
				us.turnOnTargetCount++;
			}

			if (us.turnOnTargetCount > 3) {
				us.resetEncoders();
				us.driveRobot(0, 0);
				us.turnOnTargetCount = 0;
				us.turnRobot.reset();
				us.autoTimer.reset();
				us.autoStep++;
				us.newPID = true;
			} else {
				us.driveRobot(us.turnRobotCorrection.correctionValue, -us.turnRobotCorrection.correctionValue);
			}
		}


		public static void turnToAngle(Robot us, double angle) {
			turnToAngle(us, angle, .6);
		}

		public static double getStraightPower(Robot us) {
			return (us.driveOutputLeft + us.driveOutputRight) / 2.0;
		}

		public static void pause(Robot us, double seconds) {
			if (us.autoTimer.get() > seconds) {
				us.autoStep++;
				us.autoTimer.reset();
			}
		}

		public static void halfTurnLeft(Robot us, double angle, double power) {
			if (Math.abs(us.navX.getYaw() - angle) < 3) {
				us.driveRobot(0, 0);
				us.autoStep++;
			} else {
				us.driveRobot(0, power);
			}
		}

		public static void halfTurnRight(Robot us, double angle, double power) {
			if (Math.abs(us.navX.getYaw() - angle) < 3) {
				us.driveRobot(0, 0);
				us.autoStep++;
			} else {
				us.driveRobot(power, 0);
			}
		}
		/**
		 * 
		 * Below is a modification to goStraight that will ramp up and ramp down the
		 * speed of the bot to minimize tipping. The 4 doubles at the start of
		 * goStraightSS need to be tweaked for goodness - especially the last 2.
		 */

		
		public static void goStraightSS(Robot us, double ticks, double setPoint, double maxPower) {
			double deltaSpeedIncrease = .01;
			double deltaSpeedDecrease = .01;
			double distAwayFromTargetToStartBraking = 24 * us.INCHES_TO_ENCTICKS;
			double maxOKBrakingPower = .3;

			if (us.newPID) {
				us.resetEncoders();
				us.driveStraight.reset();
				us.driveStraight.setSetpoint(setPoint);
				us.driveStraight.enable();
				us.newPID = false;
			}

			if (Math.abs(us.getEncoderMax()) > ticks) {
				us.driveRobot(0, 0);
				us.driveStraight.reset();
				us.autoTimer.reset();
				us.autoStep++;
				us.newPID = true;
			} else {
				if (ticks - Math.abs(us.getEncoderMax()) < distAwayFromTargetToStartBraking
						&& getStraightPower(us) > maxOKBrakingPower) {
					us.driveRobot(getStraightPower(us) - deltaSpeedDecrease + us.driveStraightCorrection.correctionValue,
							getStraightPower(us) - deltaSpeedDecrease - us.driveStraightCorrection.correctionValue);
				}

				else if (getStraightPower(us) < maxPower) {
					us.driveRobot(getStraightPower(us) + deltaSpeedIncrease + us.driveStraightCorrection.correctionValue,
							getStraightPower(us) + deltaSpeedIncrease - us.driveStraightCorrection.correctionValue);
				} else {
					us.driveRobot(maxPower + us.driveStraightCorrection.correctionValue,
							maxPower - us.driveStraightCorrection.correctionValue);
				}
			}

		}
		/******************
		 * Lucy's Mods to VVVSimplify *
		 ******************/
		public static void autoPIDTurn(Robot us, double desiredYaw) {
			double currentYaw = us.navX.getYaw();
			double offYaw = desiredYaw - currentYaw;

			// if driving near 180 need to correct offYaw
			if (offYaw > 180)
				offYaw = offYaw - 360;
			else if (offYaw < -180)
				offYaw = offYaw + 360;

			// initialize values during first loop
			if (us.newPID) {
				us.turnSum = 0;
				us.newPID = false;
				us.lastOffYaw = offYaw;
				us.turnOnTargetCount = 0;
				us.driveRobot(0, 0);
			} else {

				// re-zero the error sum when turn past yaw setpoint
				if (offYaw * us.lastOffYaw <= 0) {
					us.turnSum = 0;
				}

				// determine if within yaw tolerance
				if (offYaw > 2 || offYaw < -2) {
					// only add to error sum when close to target value
					if (offYaw < 20 && offYaw > -20) {
						if (offYaw > 0)
							us.turnSum = us.turnSum + 0.01;
						else
							us.turnSum = us.turnSum - 0.01;
					}
					// calculate new correction value
					double newPower = us.turnP * offYaw + us.turnSum + us.turnD * (offYaw - us.lastOffYaw);

					// limit output power
					if (newPower > 0.6)
						newPower = 0.6;
					else if (newPower < -0.6)
						newPower = -0.6;
					us.driveRobot(newPower, -newPower);
				}
				// if robot is within yaw tolerance stop robot and increase onCount
				else {
					us.turnOnTargetCount++;
					if (us.turnOnTargetCount > 3) {
						us.turnOnTargetCount = 0;
						us.autoStep++;
						us.driveRobot(0, 0);
						us.newPID = true;
					}

				}
				us.lastOffYaw = offYaw;
			}
		}
		
		static void autoPIDStraight(Robot us, double ticks, double setPoint, double power) {
//			double currentYaw = navX.getYaw();
//			double offYaw = setPoint - currentYaw;
			

			
	//   initialize needed value for first loop		
			if (us.newPID) {
//				driveStraight.reset();
				us.rampUpPower = 0.4;
				us.newPID = false;
				us.driveRobot(0,0);
				us.resetEncoders();
				us.driveStraight.setSetpoint(setPoint);
				us.driveStraight.enable();
				
			}
			
			
				else if (us.getEncoderMax() > ticks) {
				us.driveRobot(0, 0);
				us.driveStraight.reset();
//				resetEncoders();
//				autoPauseTimer.reset();
//				autoPauseTimer.start();
				us.autoStep++;
				us.newPID = true;
			}
			
			else {
				us.rampUpPower = us.rampUpPower + 0.06;
				if (us.rampUpPower < power) {
					us.driveRobot(us.rampUpPower + us.driveStraightCorrection.correctionValue,  us.rampUpPower - us.driveStraightCorrection.correctionValue);
//					rampUpPower = power;
				}
				else if (us.getEncoderMax() > ticks - 600) power = 0.4;
				us.driveRobot(power + us.driveStraightCorrection.correctionValue,
						power - us.driveStraightCorrection.correctionValue);
			}
			

		}		
}
