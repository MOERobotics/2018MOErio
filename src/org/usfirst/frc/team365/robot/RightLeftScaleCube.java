package org.usfirst.frc.team365.robot;

public class RightLeftScaleCube {
	public static void run(Robot us)
	{
		/**
		 * Forward: 9 inches ==> 18 feet ==> 216 inches ==> 22668 pulses
		 * Left (-90)
		 * Forward: 8 inches ==> 15 feet ==> 180 inches ==> 19440 pulses
		 * Right (0)
		 * Forward:  3 inches ==> 6 feet ==> 60 inches ==> 6480 pulses
		 * Right (90)
		 */
		{
			switch (us.autoStep)
			{
			case 1:
				Robot.resetPIDController(us.driveStraight);
				us.distanceR.reset();
				us.distanceL.reset();
				us.startPower = 0.7;
				us.driveStraight.setSetpoint(0);
				us.driveStraight.enable();
				us.autoStep = 2;
				break;
			case 2:
				if(us.distanceR.getRaw() > 22668)
				{
					us.driveStraight.disable();
					us.driveRobot(0, 0);
					us.autoStep = 3;
				} else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
			case 3:
				Robot.resetPIDController(us.turnRobot);
				us.turnRobot.setSetpoint(-90);
				us.turnRobot.setAbsoluteTolerance(2);
				us.startPower = 0;
				us.turnRobot.enable();
				us.autoStep = 4;
				break;
			case 4: 
				if(us.turnRobot.onTarget())
				{
					us.autoLoopCounter++;
					if (us.autoLoopCounter > 0)
					{
						us.turnRobot.disable();
						us.driveRobot(0, 0);
						us.autoStep = 5;
					} 
				}else {
					us.driveRobot(us.turnRobotCorrection.correctionValue,-us.turnRobotCorrection.correctionValue );
				}
				break;
			case 5:
				Robot.resetPIDController(us.driveStraight);
				us.distanceR.reset();
				us.distanceL.reset();
				us.startPower = 0.5;
				us.driveStraight.setSetpoint(-90);
				us.driveStraight.enable();
				us.autoStep = 7;
				break;
			case 7:
				if(us.distanceR.getRaw() > 19440)
				{
					us.driveStraight.disable();
					us.driveRobot(0, 0);
					us.autoStep = 8;
				} else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
			case 8:
				Robot.resetPIDController(us.turnRobot);
				us.turnRobot.setSetpoint(0);
				us.turnRobot.setAbsoluteTolerance(2);
				us.startPower = 0;
				us.turnRobot.enable();
				us.autoStep = 9;
				break;
			case 9: 
				if(us.turnRobot.onTarget())
				{
					us.autoLoopCounter++;
					if (us.autoLoopCounter > 0)
					{
						us.turnRobot.disable();
						us.driveRobot(0, 0);
						us.autoStep = 10;
					}
				}
				else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
			case 10:
				Robot.resetPIDController(us.driveStraight);
				us.distanceR.reset();
				us.distanceL.reset();
				us.startPower = 0.4;
				us.driveStraight.setSetpoint(0);
				us.driveStraight.enable();
				us.autoStep = 11;
				break;
			case 11: 
				if(us.distanceR.getRaw() > 4200)
				{
					us.driveStraight.disable();
					us.driveRobot(0, 0);
					us.autoStep = 14;
				}else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
	/*		case 12:
				us.turnRobot.setSetpoint(90);
				us.turnRobot.setAbsoluteTolerance(2);
				us.startPower = 0;
				us.turnRobot.enable();
				us.autoStep = 13;
				break;
			case 13:
				if(us.turnRobot.onTarget())
				{
					us.autoLoopCounter++;
					if (us.autoLoopCounter > 0)
					{
						us.turnRobot.disable();
						us.autoStep = 14;
					}
				}
				break;
				*/
			case 14:
				us.driveRobot(0,0);
				us.autoStep = 15;
				us.autoTimer.reset();
				break;
			case 15:
				if(us.autoTimer.get() > 1)
				{
					us.autoStep = 16;
				}
				else
				{
					us.driveRobot(0,0);
				}
				break;
			case 16:
				Robot.resetPIDController(us.driveStraight);
				us.distanceR.reset();
				us.distanceL.reset();
				us.startPower = -0.4;
				us.driveStraight.setSetpoint(0);
				us.driveStraight.enable();
				us.autoStep = 17;
				break;
			case 17:
				if(us.distanceR.getRaw() < -3500)
				{
					us.driveStraight.disable();
					us.driveRobot(0, 0);
					us.autoStep = 19;
				}else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
			case 19:
				Robot.resetPIDController(us.turnRobot);
				us.turnRobot.setSetpoint(180);
				us.turnRobot.setAbsoluteTolerance(2);
				us.startPower = 0;
				us.turnRobot.enable();
				us.autoStep = 20;
				break;
			case 20:
				if(us.turnRobot.onTarget())
				{
					us.autoLoopCounter++;
					if (us.autoLoopCounter > 0)
					{
						us.turnRobot.disable();
						us.driveRobot(0, 0);
						us.autoStep = 21;
					} 
				}
				else {
					us.driveRobot(us.turnRobotCorrection.correctionValue,-us.turnRobotCorrection.correctionValue );
				}
				break;
			case 21:
				Robot.resetPIDController(us.driveStraight);
				us.distanceR.reset();
				us.distanceL.reset();
				us.startPower = 0.4;
				us.driveStraight.setSetpoint(180);
				us.driveStraight.enable();
				us.autoStep = 22;
				break;
			case 22:
				if(us.distanceR.getRaw() > 2500)
				{
					us.driveStraight.disable();
					us.driveRobot(0, 0);
					us.autoStep = 23;
				} else {
					us.driveRobot(us.startPower + us.driveStraightCorrection.correctionValue,us.startPower - us.driveStraightCorrection.correctionValue );
				}
				break;
			case 23:
				us.driveRobot(0,0);
				break;
				
			}
		}
	}

}
