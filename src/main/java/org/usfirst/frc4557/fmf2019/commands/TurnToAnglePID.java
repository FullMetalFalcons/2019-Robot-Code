/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc4557.fmf2019.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

public class TurnToAnglePID extends Command implements PIDOutput {

  PIDController turnController;

  static final double kP = 0.0025;
  static final double kI = 0.001;
  static final double kD = 0.00;
  static final double kF = 0.00;
  private double target;
  private double pidOut;

  public TurnToAnglePID(double targetAngle) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveBase);
    target = targetAngle;
    
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.driveBase.resetGyro();
    turnController = new PIDController(kP, kI, kD, kF, Robot.driveBase.ahrs, this);
    turnController.setSetpoint(target);
    turnController.setInputRange(-180.0f, 180.0f);
    turnController.setOutputRange(-0.45, 0.45);
    turnController.setAbsoluteTolerance(1.0f);
    turnController.setContinuous(true);
    turnController.enable();
    setTimeout(15);

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    Robot.driveBase.rotate(pidOut);
    System.out.println(pidOut);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {



    if (isTimedOut())
      return true;
    return turnController.onTarget();
   

  }

  // Called once after isFinished returns true
  @Override
  protected void end() {

    turnController.disable();
    Robot.driveBase.drive(0,0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }


  @Override
  public void pidWrite(double output) {
    synchronized(this){
      pidOut = output;
    }
  }
}
