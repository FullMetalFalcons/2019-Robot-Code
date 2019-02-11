/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.subsystems;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 * Add your docs here.
 */
public class Intake extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid lift;
  DoubleSolenoid wrist;
  DoubleSolenoid shoot;

  public Intake(){
    lift = new DoubleSolenoid(11, 4, 5);
 //   wrist = new DoubleSolenoid(moduleNumber, forwardChannel, reverseChannel);
 //   shoot = new DoubleSolenoid(moduleNumber, forwardChannel, reverseChannel);
  }

  public void down(){
    lift.set(Value.kForward);
  }

  public void up(){
    lift.set(Value.kReverse);
  }

  public void wristUp(){

  }

  public void wristDown(){

  }

  public void intakeIn(){

  }

  public void intakeOut(){
    
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
