// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.


package org.usfirst.frc4557.fmf2019.subsystems;


import org.usfirst.frc4557.fmf2019.Robot;
import org.usfirst.frc4557.fmf2019.commands.*;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.kauailabs.navx.frc.AHRS;
import com.kauailabs.navx.frc.AHRS.SerialDataType;

// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;

// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=IMPORTS


/**
 *
 */
public class DriveBase extends Subsystem {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    private WPI_TalonSRX lTalonSRX1;
    private WPI_TalonSRX lTalonSRX2;
    private SpeedControllerGroup leftSpeedController;
    private WPI_TalonSRX rTalonSRX1;
    private WPI_TalonSRX rTalonSRX2;
    private SpeedControllerGroup rightSpeedController;
    private DifferentialDrive diffDrive;

    AHRS ahrs;

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    public DriveBase() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        lTalonSRX1 = new WPI_TalonSRX(0);
        
        
        
        lTalonSRX2 = new WPI_TalonSRX(6);
        
        
        
        leftSpeedController = new SpeedControllerGroup(lTalonSRX1, lTalonSRX2  );
        addChild("LeftSpeedController",leftSpeedController);
        
        
        rTalonSRX1 = new WPI_TalonSRX(2);
        
        
        
        rTalonSRX2 = new WPI_TalonSRX(3);
        
        
        
        rightSpeedController = new SpeedControllerGroup(rTalonSRX1, rTalonSRX2  );
        addChild("RightSpeedController",rightSpeedController);
        
        
        diffDrive = new DifferentialDrive(leftSpeedController, rightSpeedController);
        addChild("DiffDrive",diffDrive);
        diffDrive.setSafetyEnabled(false);
        diffDrive.setExpiration(0.1);
        diffDrive.setMaxOutput(1.0);

        

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS

        ahrs.zeroYaw();
    }

    @Override
    public void initDefaultCommand() {
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND


        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }

    @Override
    public void periodic() {
        // Put code here to be run every loop

         /* Display 6-axis Processed Angle Data                                      */
         SmartDashboard.putBoolean(  "IMU_Connected",        ahrs.isConnected());
         SmartDashboard.putBoolean(  "IMU_IsCalibrating",    ahrs.isCalibrating());
         SmartDashboard.putNumber(   "IMU_Yaw",              ahrs.getYaw());
         SmartDashboard.putNumber(   "IMU_Pitch",            ahrs.getPitch());
         SmartDashboard.putNumber(   "IMU_Roll",             ahrs.getRoll());
         
         /* Display tilt-corrected, Magnetometer-based heading (requires             */
         /* magnetometer calibration to be useful)                                   */
         
         SmartDashboard.putNumber(   "IMU_CompassHeading",   ahrs.getCompassHeading());
         
         /* Display 9-axis Heading (requires magnetometer calibration to be useful)  */
         SmartDashboard.putNumber(   "IMU_FusedHeading",     ahrs.getFusedHeading());

         /* These functions are compatible w/the WPI Gyro Class, providing a simple  */
         /* path for upgrading from the Kit-of-Parts gyro to the navx-MXP            */
         
         SmartDashboard.putNumber(   "IMU_TotalYaw",         ahrs.getAngle());
         SmartDashboard.putNumber(   "IMU_YawRateDPS",       ahrs.getRate());

         /* Display Processed Acceleration Data (Linear Acceleration, Motion Detect) */
         
         SmartDashboard.putNumber(   "IMU_Accel_X",          ahrs.getWorldLinearAccelX());
         SmartDashboard.putNumber(   "IMU_Accel_Y",          ahrs.getWorldLinearAccelY());
         SmartDashboard.putBoolean(  "IMU_IsMoving",         ahrs.isMoving());
         SmartDashboard.putBoolean(  "IMU_IsRotating",       ahrs.isRotating());

         /* Display estimates of velocity/displacement.  Note that these values are  */
         /* not expected to be accurate enough for estimating robot position on a    */
         /* FIRST FRC Robotics Field, due to accelerometer noise and the compounding */
         /* of these errors due to single (velocity) integration and especially      */
         /* double (displacement) integration.                                       */
         
         SmartDashboard.putNumber(   "Velocity_X",           ahrs.getVelocityX());
         SmartDashboard.putNumber(   "Velocity_Y",           ahrs.getVelocityY());
         SmartDashboard.putNumber(   "Displacement_X",       ahrs.getDisplacementX());
         SmartDashboard.putNumber(   "Displacement_Y",       ahrs.getDisplacementY());
         
         /* Display Raw Gyro/Accelerometer/Magnetometer Values                       */
         /* NOTE:  These values are not normally necessary, but are made available   */
         /* for advanced users.  Before using this data, please consider whether     */
         /* the processed data (see above) will suit your needs.                     */
         
         SmartDashboard.putNumber(   "RawGyro_X",            ahrs.getRawGyroX());
         SmartDashboard.putNumber(   "RawGyro_Y",            ahrs.getRawGyroY());
         SmartDashboard.putNumber(   "RawGyro_Z",            ahrs.getRawGyroZ());
         SmartDashboard.putNumber(   "RawAccel_X",           ahrs.getRawAccelX());
         SmartDashboard.putNumber(   "RawAccel_Y",           ahrs.getRawAccelY());
         SmartDashboard.putNumber(   "RawAccel_Z",           ahrs.getRawAccelZ());
         SmartDashboard.putNumber(   "RawMag_X",             ahrs.getRawMagX());
         SmartDashboard.putNumber(   "RawMag_Y",             ahrs.getRawMagY());
         SmartDashboard.putNumber(   "RawMag_Z",             ahrs.getRawMagZ());
         SmartDashboard.putNumber(   "IMU_Temp_C",           ahrs.getTempC());
         
         /* Omnimount Yaw Axis Information                                           */
         /* For more info, see http://navx-mxp.kauailabs.com/installation/omnimount  */
         AHRS.BoardYawAxis yaw_axis = ahrs.getBoardYawAxis();
         SmartDashboard.putString(   "YawAxisDirection",     yaw_axis.up ? "Up" : "Down" );
         SmartDashboard.putNumber(   "YawAxis",              yaw_axis.board_axis.getValue() );
         
         /* Sensor Board Information                                                 */
         SmartDashboard.putString(   "FirmwareVersion",      ahrs.getFirmwareVersion());
         
         /* Quaternion Data                                                          */
         /* Quaternions are fascinating, and are the most compact representation of  */
         /* orientation data.  All of the Yaw, Pitch and Roll Values can be derived  */
         /* from the Quaternions.  If interested in motion processing, knowledge of  */
         /* Quaternions is highly recommended.                                       */
         SmartDashboard.putNumber(   "QuaternionW",          ahrs.getQuaternionW());
         SmartDashboard.putNumber(   "QuaternionX",          ahrs.getQuaternionX());
         SmartDashboard.putNumber(   "QuaternionY",          ahrs.getQuaternionY());
         SmartDashboard.putNumber(   "QuaternionZ",          ahrs.getQuaternionZ());
         
         /* Connectivity Debugging Support                                           */
         SmartDashboard.putNumber(   "IMU_Byte_Count",       ahrs.getByteCount());
         SmartDashboard.putNumber(   "IMU_Update_Count",     ahrs.getUpdateCount());


    }

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS


    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CMDPIDGETTERS

    // Put methods for controlling this subsystem
    // here. Call these from Commands.


    public void resetGyro(){

        ahrs.zeroYaw();

    }

    public void drive(double left, double right) {
    
        diffDrive.tankDrive(left, right);
    
    }

    public double getTalonRightSpeed() {
        return rTalonSRX1.getSelectedSensorVelocity();
        
        
    }

    public double getTalonLeftSpeed(){
        return lTalonSRX1.getSelectedSensorVelocity();
    }

    class PlotThread implements Runnable {
        Robot robot;
        

		public PlotThread(Robot robot) {
			this.robot = robot;
		}

		public void run() {

			/**
			 * Speed up network tables, this is a test project so eat up all of
			 * the network possible for the purpose of this test.
			 */

			while (true) {
				/* Yield for a Ms or so - this is not meant to be accurate */
				try {
					Thread.sleep(1);
				} catch (Exception e) {
					/* Do Nothing */
				}

				/* Grab the latest signal update from our 1ms frame update */
				double velocityright = this.robot.driveBase.getTalonRightSpeed();
                SmartDashboard.putNumber("vel right", velocityright);
                double velocityleft = this.robot.driveBase.getTalonLeftSpeed();
                SmartDashboard.putNumber("vel left", velocityleft);
            }
		}
	}
}

