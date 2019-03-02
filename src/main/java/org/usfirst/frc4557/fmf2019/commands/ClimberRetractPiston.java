/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import org.usfirst.frc4557.fmf2019.Robot;
import org.usfirst.frc4557.fmf2019.subsystems.Climber;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberRetractPiston extends Command {
  private Climber.PistonPosition target;
  private double waitTime;
  private boolean done;
  private double startTime;

  public ClimberRetractPiston(Climber.PistonPosition pos, double duration) {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
    target = pos;
    waitTime = duration;

  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    done = false;
    startTime = System.currentTimeMillis();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    if (System.currentTimeMillis() - startTime > waitTime)
    {
      done = true;
    }
    if (target == Climber.PistonPosition.FRONT)
    {
      Robot.climber.frontUp();
    }
    if (target == Climber.PistonPosition.REAR)
    {
      Robot.climber.rearUp();
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
    if (target == Climber.PistonPosition.FRONT)
    {
      Robot.climber.frontStop();
    }
    if (target == Climber.PistonPosition.REAR)
    {
      Robot.climber.rearStop();
    }
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
