/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4557.fmf2019.Robot;

public class ClimberDrive extends Command {

  private double currentHeight;
  private boolean done;
  private double startTime;
  static final int WAITTIME = 10;
  public ClimberDrive() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done= false;
    startTime = 0;
    currentHeight = Robot.climber.getFrontChasisHeight();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (Robot.climber.getFrontChasisHeight() < 7) {
      if (startTime == 0) {
        // We front wheel is on the platform
        Robot.climber.stop();
        startTime =System.currentTimeMillis();
        Robot.climber.frontUp();
      } else {
        // Wait until the WAITTIME has passed
        if ( System.currentTimeMillis() - startTime > WAITTIME ){
          done = true;
        }
      }
    } else {
      // Continue to drive since the front wheel has not clear the platform
      Robot.climber.driveForward(0.3);
    }
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return done;
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
