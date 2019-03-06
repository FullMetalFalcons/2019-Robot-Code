/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import org.usfirst.frc4557.fmf2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class TurnToAngle extends Command {

  public static final double turnGainMultiplier = 0.1;
	public static final double turnP = 0.5;
	public static final double turnI = 0.03;
	public static final double turnD = 0.5;
	
	public static final double driveStraightGainMultiplier = 0.03; //.03
	public static final double driveStraightP = 0.45; //.45 //1.0 (12/23)
	public static final double driveStraightI = 0.015; //.015 //1.0 (12/23)
  public static final double driveStraightD = 0.011; //.011 //3.0 (12/23) //8.0(12/23-2)
  
  double motorSpeed = 0.55;
	double direction = 0;
	double currentAngle = 0;
	double error = 0;
	double pAdjustment = 0;
	double iAdjustment = 0;
	double dAdjustment = 0;
	double lastError = 0;
	double PIDAdjustment = 0;
	double speed = 0;
	double way = 1;
	int n = 0;
	int i = 0;
	boolean pid = false;

  public TurnToAngle(double newdirection) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveBase);
    direction = newdirection;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    motorSpeed = 0.55;
		currentAngle = 0;
		error = 0;
		pAdjustment = 0;
		iAdjustment = 0;
		dAdjustment = 0;
		lastError = 0;
		PIDAdjustment = 0;
		speed = 0;
		way = 1;
		n = 0;
		i = 0;
    pid = false;


		Robot.driveBase.resetGyro();
    
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    pAdjustment = error * turnP * turnGainMultiplier;
		iAdjustment = iAdjustment + (error * turnI * turnGainMultiplier);
		dAdjustment = (error - lastError) * turnD * turnGainMultiplier;
		lastError = error;
    PIDAdjustment = pAdjustment + iAdjustment + dAdjustment;


		double gyroYaw = Robot.driveBase.getGyro();

		

    if (gyroYaw < 10 + direction && gyroYaw > direction - 10) {
			if (pid == false) {
				iAdjustment = 0;
				pid = true;
			}
		} else {
			pid = false;
		}

		if (pid == true) {
			if (PIDAdjustment > .3) {
				speed = .3;
			} else if (PIDAdjustment < -.3) {
				speed = -.3;
			} else {
				speed = PIDAdjustment;
			}
		} else {
			if (error > 0) {
				speed = motorSpeed;
			} else {
				speed = -motorSpeed;
			}
		}
		Robot.driveBase.drive(speed, -speed);

		if (Robot.driveBase.getGyro() < 2 + direction && Robot.driveBase.getGyro() > direction - 2) {
			n++;
			i++;
		} else {
			n = 0;
		}
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {

    
		return Robot.driveBase.getGyro() < 2 + direction && Robot.driveBase.getGyro() > direction - 2;
		


  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    pid = false;
		Robot.isTurning = false;
		Robot.driveBase.stop();
		
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    end();
  }
}
