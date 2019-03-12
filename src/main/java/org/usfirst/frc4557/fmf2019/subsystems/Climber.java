/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package org.usfirst.frc4557.fmf2019.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import java.util.concurrent.ForkJoinTask;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.DoubleSolenoid;
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

  private AnalogInput frontultrasonic = null;
  private AnalogInput rearultrasonic = null;

  private  final AnalogInput pressureSensor;

  public enum PistonPosition {
    FRONT, REAR
  }
  public Climber() {

    frontValve = new DoubleSolenoid(12, 1, 0);
    //rearValve  = new DoubleSolenoid(12, 6, 7);
    rearValve = new DoubleSolenoid(11,3, 2);

    leftMotor = new WPI_TalonSRX(12);
    rightMotor = new WPI_TalonSRX(1);

    frontultrasonic = new AnalogInput(0);
    rearultrasonic = new AnalogInput(1);

    pressureSensor = new AnalogInput(2);
  }

  public void frontUp(){
    frontValve.set(Value.kReverse);
  }

  public void frontDown(){
    frontValve.set(Value.kForward);
  }
  public void frontStop(){
    frontValve.set(Value.kOff);
  }
  public void rearUp(){
    rearValve.set(Value.kReverse);
  }

  public void rearDown(){
    rearValve.set(Value.kForward);
  }

  public void rearStop() {
    rearValve.set(Value.kOff);
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
    leftMotor.set(speed);
    rightMotor.set(-speed);
  }
  public double getFrontChasisHeight()
  {
    // Ultrasound scaling factor ~9.8mV/in
    return frontultrasonic.getVoltage() * 1000 / 9.8;
  }

  public double getRearChasisHeight()
  {
    return rearultrasonic.getVoltage() * 1000 / 9.8;
  }

  public double getPressure(){
    // Output: 0.5V–4.5V linear voltage output. 0 psi outputs 0.5V, 100 psi outputs 2.5V, 200 psi outputs 4.5V
    return 200 * (pressureSensor.getVoltage() - 0.5) / 4.0;
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }

  public void periodic()  {
    //System.out.println(frontultrasonic.getVoltage());
    SmartDashboard.putNumber("front height", this.getFrontChasisHeight());
    SmartDashboard.putNumber("rear height", this.getRearChasisHeight());
    SmartDashboard.putNumber("Pressure", this.getPressure());
  }

}
