/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import org.usfirst.frc4557.fmf2019.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ClimberFinale extends Command {
  private double rearChasisHeight ;
  private double startTime;
  private boolean done;
  static final int WAITTIME = 3;
  public ClimberFinale() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.driveBase);
    requires(Robot.climber);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    rearChasisHeight = Robot.climber.getRearChasisHeight();
    done= false;
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    // Drive Until middle of the robot is on the platform
    if (rearChasisHeight > 7) {
      Robot.driveBase.drive(0.3, 0.3);
      Robot.climber.driveForward(0.3);
    } else {
      if (startTime ==0) {
        Robot.driveBase.drive(0.1, 0.1);
        Robot.intake.wristDown();
        startTime =System.currentTimeMillis();
        Robot.climber.stop();
        Robot.climber.rearUp();
      } else {
        if (System.currentTimeMillis() - startTime > WAITTIME)
        {
          Robot.driveBase.drive(0.3,0.3);
          try {
            Thread.sleep(500);
          } catch (Exception e) {
            System.out.println(e);
            done = true;
          }
          Robot.driveBase.drive(0, 0);
          done = true;
        }
      }
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return false;
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
