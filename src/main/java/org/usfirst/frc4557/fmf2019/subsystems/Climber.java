/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

import java.util.concurrent.ForkJoinTask;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.DoubleSolenoid.Value;
/**
 * Add your docs here.
 */



public class Climber extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  DoubleSolenoid frontValve;
  DoubleSolenoid rearValve;

  private WPI_TalonSRX leftMotor;
  private WPI_TalonSRX rightMotor;
  private SpeedControllerGroup rearDrive;
  private DifferentialDrive drive;

  public Climber() {

    frontValve = new DoubleSolenoid(12, 1, 0);
    //rearValve  = new DoubleSolenoid(12, 6, 7);
    rearValve = new DoubleSolenoid(11,3, 2);

    leftMotor = new WPI_TalonSRX(6);
    rightMotor = new WPI_TalonSRX(7);
    rearDrive = new SpeedControllerGroup(leftMotor, rightMotor);
  }

  public void frontUp(){
    frontValve.set(Value.kReverse);
  }

  public void frontDown(){
    frontValve.set(Value.kForward);
  }

  public void rearUp(){
    rearValve.set(Value.kReverse);
  }

  public void rearDown(){
    rearValve.set(Value.kForward);
  }

  public void driveForward(double speed, double turnRate){
    drive.arcadeDrive(speed, turnRate);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
