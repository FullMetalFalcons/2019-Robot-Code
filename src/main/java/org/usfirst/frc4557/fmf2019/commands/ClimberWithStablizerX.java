/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import java.lang.Math;
import java.lang.Thread;
import org.usfirst.frc4557.fmf2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberWithStablizerX extends Command {

  public boolean isDone = false;
  private boolean isStarted = false;
  private double previousHeight = 0;

  public ClimberWithStablizerX() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveBase);
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isStarted = false;
    setTimeout(12);
    isDone = false;
    // Start the climb
    Robot.climber.frontDown();
    Robot.climber.rearDown();

  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    float roll = Robot.driveBase.getRoll();

    // if robot is balance, continue climb
    if (roll < 0.2 && roll > -0.2) {
      Robot.climber.frontDown();
      Robot.climber.rearDown();
    } else {
      if (roll > 0.2) {
        // front is too fast -- slow front down
        Robot.climber.frontStop();
        Robot.climber.rearDown();
      }
      if (roll < -0.2) {
        Robot.climber.rearStop();
        Robot.climber.frontDown();
        // rear is too fast -- slow rear down
      }

    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (isDone) {
      return true;
    }
    double currentHeight = Robot.climber.getFrontChasisHeight();
    if (currentHeight != previousHeight) {
      previousHeight = currentHeight;
      return false;
    } else {
      return true;
    }
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.frontStop();
    Robot.climber.rearStop();
    Robot.isAutonomous = false;
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.climber.frontStop();
    Robot.climber.rearStop();
  }
}
