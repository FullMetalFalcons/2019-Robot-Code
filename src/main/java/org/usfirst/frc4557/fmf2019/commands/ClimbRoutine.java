/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.commands;

import java.awt.Robot;

import org.usfirst.frc4557.fmf2019.subsystems.Climber;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class ClimbRoutine extends CommandGroup {
  /**
   * Add your docs here.
   */
  public ClimbRoutine() {

    addSequential(new ClimberWithStablizer());
    addSequential(new ClimberDrive(Climber.PistonPosition.FRONT));
    addSequential(new ClimberRetractPiston(Climber.PistonPosition.FRONT, 5000));
    addSequential(new ClimberWristPosition());    
    addSequential(new ClimberDrive(Climber.PistonPosition.REAR));
    addSequential(new ClimberRetractPiston(Climber.PistonPosition.REAR, 5000));
    //addSequential(new ClimberFinale());

    // Add Commands here:
    // e.g. addSequential(new Command1());
    // addSequential(new Command2());
    // these will run in order.

    // To run multiple commands at the same time,
    // use addParallel()
    // e.g. addParallel(new Command1());
    // addSequential(new Command2());
    // Command1 and Command2 will run in parallel.

    // A command group will require all of the subsystems that each member
    // would require.
    // e.g. if Command1 requires chassis, and Command2 requires arm,
    // a CommandGroup containing them would require both the chassis and the
    // arm.
  }
}
