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
  DoubleSolenoid pickup;

  public Intake(){
    lift = new DoubleSolenoid(11, 0, 1);
    wrist = new DoubleSolenoid(11, 4, 5);
    shoot = new DoubleSolenoid(11, 7, 6);
    pickup = new DoubleSolenoid(12 , 3, 2);
  }

  public void down(){
    lift.set(Value.kForward);
  }

  public void up(){
    lift.set(Value.kReverse);
  }

  public void wristUp(){
    wrist.set(Value.kForward);
  }

  public void wristDown(){
    wrist.set(Value.kReverse);
  }

  public void intakeIn(){
    shoot.set(Value.kReverse);
  }

  public void intakeOut(){
    shoot.set(Value.kForward);
  }

  public void pickupOut() {
    pickup.set(Value.kForward);
  }

  public void pickupIn() {
    pickup.set(Value.kReverse);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
