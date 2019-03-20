/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc4557.fmf2019.Robot;
import org.usfirst.frc4557.fmf2019.subsystems.Climber;

public class ClimberDrive extends Command {
  
  private double currentHeight;
  private boolean done;
  private Climber.PistonPosition target;

  static final int WAITTIME = 100;
  public ClimberDrive(Climber.PistonPosition pos) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
    target = pos;
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    setTimeout(10);
    done= false;   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
      // Continue to drive since the front wheel has not clear the platform
      Robot.climber.driveForward(0.1);
    
  }
  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return done || isTimedOut() || getChasisHeight() < 30;
  }
  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private double getChasisHeight()
  {
    if (target == Climber.PistonPosition.FRONT)
    {
      return Robot.climber.getFrontChasisHeight();
    } else {
      return Robot.climber.getRearChasisHeight();
    }
  }
}
