// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc4557.fmf2019;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.GenericHID.Hand;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.kauailabs.navx.frc.AHRS;

import org.usfirst.frc4557.fmf2019.commands.*;
import org.usfirst.frc4557.fmf2019.subsystems.*;
import edu.wpi.first.wpilibj.*;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in the
 * project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();
    Preferences prefs;

    public static OI oi;
    public static Compressor compressor;

    public static DriveBase driveBase;
    public static Climber climber;
    public static Intake intake;
    public static boolean isAutonomous = false;
    public static boolean isTurning = false;
    public static boolean diagnosticMode = false;

    public static ClimberWithStablizer autoclimber;

    public static double DRIVE_SLOW_FACTOR;
    public static double CLIMBER_DRIVE_SPEED;

    private int OPS_BUTTON_INTAKE;
    private int OPS_BUTTON_PICKUP;
    private int OPS_BUTTON_CLIMBER_DRIVE;
    private int OPS_BUTTON_CLIMBER_FRONTPISTON_UP;
    private int OPS_BUTTON_CLIMBER_REAR_UP;
    private TurnToAnglePID t;
    private int DRIVER_BUTTON_SLOW;
    /**
     * This function is run when the robot is first started up and should be used
     * for any initialization code.
     */
    @Override
    public void robotInit() {

        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveBase = new DriveBase();
        climber = new Climber();
        intake = new Intake();

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        // (which it very likely will), subsystems are not guaranteed to be
        // constructed yet. Thus, their requires() statements may grab null
        // pointers. Bad news. Don't move it.
        oi = new OI();

        // Add commands to Autonomous Sendable Chooser
        // compressor = new Compressor(RobotCanID.COMPRESSORID);
        // compressor.setClosedLoopControl(true);

        CameraServer.getInstance().startAutomaticCapture();
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS

        chooser.setDefaultOption("Autonomous Command", new AutonomousCommand());

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=AUTONOMOUS
        SmartDashboard.putData("Auto mode", chooser);
        prefs = Preferences.getInstance();

        DRIVE_SLOW_FACTOR = prefs.getDouble("LowSpeedFactor", 0.7);
        CLIMBER_DRIVE_SPEED = prefs.getDouble("ClimberDriveSpeed", 0.45) * -1;
        diagnosticMode = prefs.getBoolean("DiagnosticMode", false);

        OPS_BUTTON_INTAKE = prefs.getInt("OpsButtonIntake", 6); 
        OPS_BUTTON_PICKUP = prefs.getInt("OpsButtonPickup", 5); 
        OPS_BUTTON_CLIMBER_DRIVE = prefs.getInt("OpsButtonClimberDrive", 3) ;
        OPS_BUTTON_CLIMBER_FRONTPISTON_UP = prefs.getInt("OpsButtonClimberFrontUp", 4); 
        OPS_BUTTON_CLIMBER_REAR_UP = prefs.getInt("OpsButtonlimberRearUp", 2); 


        DRIVER_BUTTON_SLOW = prefs.getInt("DriverButtonSlow", 8);
driveBase.setBrakeMode();
        autoclimber = new ClimberWithStablizer();
        // climber.rearUp();
        // climber.frontUp();
        // intake.up();
        // intake.wristUp();
        // intake.intakeIn();
    }

    /**
     * This function is called when the disabled button is hit. You can use it to
     * reset subsystems before shutting down.
     */
    @Override
    public void disabledInit() {

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null)
            autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Periodic();
        // Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
            autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {

        Periodic();
    }

    private void Periodic() {
        // Slow Down the robot when pressed - L2

        double leftYstick = oi.playstayController.getY(Hand.kLeft);
        double rightYstick = oi.playstayController.getY(Hand.kRight);

        // Dead zone
        if (Math.abs(leftYstick) < 0.10)
            leftYstick = 0;
        if (Math.abs(rightYstick) < 0.10)
            rightYstick = 0;

        

        if (oi.playstayController.getRawButton(2)) {
            System.out.println("Drive Straight");
            driveBase.drive(0.6,0.6);
        
        }  else {
            System.out.println("Drive");
            if (oi.playstayController.getRawButton(DRIVER_BUTTON_SLOW)) {
                driveBase.drive(leftYstick * -1 * DRIVE_SLOW_FACTOR, rightYstick * -1 * DRIVE_SLOW_FACTOR);
            } else {
                driveBase.drive(leftYstick * -1, rightYstick * -1);
            }
        }
        

        if (oi.playstayController.getRawButton(7)) {
            isTurning = true;
            t = new TurnToAnglePID(-75);
            t.start();
        }

        if (oi.playstayController.getRawButton(8)) {
            isTurning = true;
            t = new TurnToAnglePID(75);
            t.start();

        }

        if (oi.playstayController.getBumper(Hand.kRight)) {
            climber.frontDown();
        } else {
            climber.frontStop();
        }
        if (oi.playstayController.getBumper(Hand.kLeft)) {
            climber.rearDown();
        } else {
            climber.rearStop();
        }

        if (oi.xbox.getY(Hand.kRight) >= 0.2) {
            intake.up();
        }
        if (oi.xbox.getY(Hand.kRight) <= -0.2) {
            intake.down();
        }
        if (!(oi.xbox.getY(Hand.kLeft) > -0.2 && oi.xbox.getY(Hand.kLeft) < 0.2)) {
            if (oi.xbox.getY(Hand.kLeft) <= -0.2) {
                intake.wristUp();
            }
            if (oi.xbox.getY(Hand.kLeft) >= 0.2) {
                intake.wristDown();
            }
        }
        onXboxControllerRemap();

        if (oi.playstayController.getXButton()) {
            autoclimber.start();
        } else {

            autoclimber.isDone = true;
        }


        if (Robot.diagnosticMode) {
        
            if (oi.playstayController.getAButton()){
                System.out.println("A Button");
                Robot.driveBase.driveForXrotation(1);
            }
            if (oi.playstayController.getXButton()) {
            isTurning = true;
            TurnToAnglePID t = new TurnToAnglePID(45);
            t.start();
            }
        }
        Scheduler.getInstance().run();
    }

    private void onXboxController() {
        if (oi.xbox.getBumper(Hand.kRight)) {
            intake.intakeIn();
        } else {
            intake.intakeOut();
        }
        if (oi.xbox.getBumper(Hand.kLeft)) {
            intake.pickupOut();
        } else {
            intake.pickupIn();
        }
        
        if (oi.xbox.getBButtonPressed()) {
            climber.driveForward(CLIMBER_DRIVE_SPEED);
        }
        if (oi.xbox.getBButtonReleased()) {
            climber.stop();
        }
        if (oi.xbox.getYButton()) {
            climber.frontUp();
        }
        if (oi.xbox.getAButton()) {
            climber.rearUp();
        }
    }

    private void onXboxControllerRemap() {

        if (oi.xbox.getRawButtonReleased(OPS_BUTTON_INTAKE)) intake.intakeOut(); 
        if (oi.xbox.getRawButtonPressed(OPS_BUTTON_INTAKE)) intake.intakeIn();
        if (oi.xbox.getRawButtonPressed(OPS_BUTTON_PICKUP)) intake.pickupOut();
        if (oi.xbox.getRawButtonReleased(OPS_BUTTON_PICKUP)) intake.pickupIn();
        if (oi.xbox.getRawButtonPressed(OPS_BUTTON_CLIMBER_DRIVE)) climber.driveForward(CLIMBER_DRIVE_SPEED);
        if (oi.xbox.getRawButtonReleased(OPS_BUTTON_CLIMBER_DRIVE)) climber.stop();
        if (oi.xbox.getRawButton(OPS_BUTTON_CLIMBER_FRONTPISTON_UP)) climber.frontUp();
        if (oi.xbox.getRawButton(OPS_BUTTON_CLIMBER_REAR_UP)) climber.rearUp();
    }
}
