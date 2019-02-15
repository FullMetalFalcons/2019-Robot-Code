/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.ForkJoinTask;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.Ultrasonic;
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

  private AnalogInput ultrasonic = new AnalogInput(0);

  public Climber() {

    frontValve = new DoubleSolenoid(12, 1, 0);
    //rearValve  = new DoubleSolenoid(12, 6, 7);
    rearValve = new DoubleSolenoid(11,3, 2);

    leftMotor = new WPI_TalonSRX(12);
    rightMotor = new WPI_TalonSRX(1);

  
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

  public void driveForward(double speed){
    leftMotor.set(speed);
    rightMotor.set(-speed);
  }

  public void stop() {
    leftMotor.set(0);
    rightMotor.set(0);
  }

  public void driveReverse(double speed){
    leftMotor.set(-speed);
    rightMotor.set(speed);
  }

  public void UltrasonicSensor() {
    SmartDashboard.putData("range", ultrasonic);
    double currentDistance = ultrasonic.getVoltage();
    System.out.println(currentDistance);
  }
  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic()  {

    
      SmartDashboard.putData("range", ultrasonic);
      double currentDistance = ultrasonic.getVoltage();
      System.out.println(currentDistance);
    

  }
}
