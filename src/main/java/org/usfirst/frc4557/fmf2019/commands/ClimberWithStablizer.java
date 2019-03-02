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

public class ClimberWithStablizer extends Command {
  private double warmuptime = 1000;
  private double commandtimeout = 7000;
  private boolean isDone = false;

  private double startTime;
  private boolean isStarted = false;
  private double previousHeight = 0;
  
  public ClimberWithStablizer() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveBase);
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    isStarted = false;
    startTime = System.currentTimeMillis();
    isDone =false;
    // Start the climb
    Robot.climber.frontDown();
    Robot.climber.rearDown();
  
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    
    double currentHeight = Robot.climber.getFrontChasisHeight();
    double currentMilli = System.currentTimeMillis();
  
    // Wait for x ms before stablizing
    if (currentMilli - startTime < warmuptime)
    {
      return;
    } 
    if (currentMilli - startTime > commandtimeout)
    {
      isDone = true;
      return;
    }
    float roll = Robot.driveBase.getRoll();
    if (roll > 1.5) {
      //front is too fast -- slow front down
      Robot.climber.frontStop();
    } else if(roll < -3.5) {
      Robot.climber.rearStop();
      // rear is too fast -- slow rear down
    } else {
      Robot.climber.frontDown();  
      Robot.climber.rearDown();
    }

    isDone = (previousHeight == currentHeight);
    previousHeight = currentHeight;
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    
    return isDone;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.climber.frontStop();
    Robot.climber.rearStop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
    Robot.climber.frontStop();
    Robot.climber.rearStop();
  }
}
