/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;
import java.lang.Math;
import java.lang.Thread;
import javax.lang.model.util.ElementScanner6;

import org.usfirst.frc4557.fmf2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberWithStablizer extends Command {
  private double startTime;
  private boolean isStarted = false;
  private boolean done = false;
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
    done =false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    if (!isStarted)
    {
      Robot.climber.frontDown();
      Robot.climber.rearDown();
      isStarted = true;
      return;
    }

    if (System.currentTimeMillis() - startTime < 1000)
    {
      return;
    }
    float roll = Robot.driveBase.getRoll();
System.out.println("Speed" + Robot.driveBase.getVelocityZ() + " -- " + "Roll=" + roll );


    if (roll > 1.5) {
      //front is too fast -- slow front down
      Robot.climber.stop();
      
      long runtime = Math.abs(Math.round(roll));
      System.out.println("FrontSTOP -- " + runtime);
      // try {
      //   Thread.sleep(runtime);
      // } catch (Exception e) {
      //   //TODO: handle exception
      // }
    } else {
      //Robot.climber.rearDown();
    }
    
    if (roll < -3.5) {
      // rear is too fast -- slow rear down
      Robot.climber.stop();
      System.out.println("RearSTOP");
      long runtime =  Math.abs(Math.round(roll));
      System.out.println("RearSTOP -- " + runtime);
      // try {
      //   Thread.sleep(runtime);
      // } catch (Exception e) {
      //   //TODO: handle exception
      // }
    } else {
      //Robot.climber.rearDown();
    }

    Robot.climber.frontDown();  
    Robot.climber.rearDown();
    done = (System.currentTimeMillis() - startTime > 7000);
    //done = (Robot.driveBase.getVelocityZ() == 0);
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
