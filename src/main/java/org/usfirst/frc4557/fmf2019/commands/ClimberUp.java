/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;
import org.usfirst.frc4557.fmf2019.Robot;
import edu.wpi.first.wpilibj.command.Command;

public class ClimberUp extends Command {

  private double previousHeight;
  private boolean done;
  private double startTime;

  public ClimberUp() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    previousHeight = 0;
    startTime = System.currentTimeMillis();
    setTimeout(2);
    done = false;
    System.out.println("Command Initialize");
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    double currentHeight = Robot.climber.getFrontChasisHeight();
    System.out.println(currentHeight + "---" + previousHeight);
    
    
    if (System.currentTimeMillis() - startTime > 10000)
    {
      System.out.println("CommandTimedOut******");
      done=true;
    }
    if (previousHeight == 0)
    {
      Robot.climber.frontDown();
      Robot.climber.rearDown();
      previousHeight =currentHeight;
    } else {
      
      if (currentHeight == previousHeight)
      {
        System.out.println("Reached------------------------");
        done = true;
      } else {
        previousHeight = currentHeight;
      }
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
