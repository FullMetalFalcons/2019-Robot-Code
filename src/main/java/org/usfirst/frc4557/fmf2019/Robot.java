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

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.properties file in 
 * the project.
 */
public class Robot extends TimedRobot {

    Command autonomousCommand;
    SendableChooser<Command> chooser = new SendableChooser<>();

    public static OI oi;
    public static Compressor compressor;
    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
    public static DriveBase driveBase;
    public static Climber climber;
    public static Intake intake;
    private ClimberUp climbcommand;
    public AHRS ahrs;
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    @Override
    public void robotInit() {

       
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        driveBase = new DriveBase();
        climber = new Climber();
        intake = new Intake();


        
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTORS
        // OI must be constructed after subsystems. If the OI creates Commands
        //(which it very likely will), subsystems are not guaranteed to be
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
        //climber.rearUp();
        //climber.frontUp();
        //intake.up();
        //intake.wristUp();
        //intake.intakeIn();
    }

    /**
     * This function is called when the disabled button is hit.
     * You can use it to reset subsystems before shutting down.
     */
    @Override
    public void disabledInit(){

    }

    @Override
    public void disabledPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        autonomousCommand = chooser.getSelected();
        // schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }

    /**
     * This function is called periodically during autonomous
     */
    @Override
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    @Override
    public void teleopInit() {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    }

    /**
     * This function is called periodically during operator control
     */
    @Override
    public void teleopPeriodic() {

        driveBase.drive(oi.playstayController.getY(Hand.kLeft) / -1.1, oi.playstayController.getY(Hand.kRight) / -1.1 );

        if (oi.xbox.getY(Hand.kRight) >= 0.2){
            intake.up();
        }

        if (oi.xbox.getY(Hand.kRight) <= -0.2){
            intake.down();
        }

        if (oi.xbox.getAButtonPressed()){
            climber.driveForward(-0.45);
        }

        if (oi.xbox.getAButtonReleased()){
            climber.stop();;
        }

        if (oi.xbox.getBumper(Hand.kLeft)){
            intake.down();
            intake.wristDown();
        }

        if (oi.xbox.getBumper(Hand.kRight)){
            TurnToAngle turncmd = new TurnToAngle(20);
            turncmd.start();
        }

        if (oi.xbox.getStickButton(Hand.kRight)){
           //ClimberWithStablizer climb = new ClimberWithStablizer();
           //climb.start(); 
            climber.frontUp();
            
            //climber.frontDown();
        }

        if (oi.xbox.getStickButton(Hand.kLeft)){
            //climber.frontUp();
            //climber.rearUp();

            climber.frontDown();
        }

        if (oi.xbox.getBackButton()){
            climber.frontDown();
        }

        if  (!oi.xbox.getBackButton() && !oi.xbox.getStickButton(Hand.kRight) && !oi.xbox.getStickButton(Hand.kLeft)) {            
            climber.frontStop();
        }

        if (oi.xbox.getStartButton()){
            climber.rearDown();
        }

        if (oi.xbox.getYButton()){
            climber.rearUp();
        }
        
        if (!oi.xbox.getYButton() &&  !oi.xbox.getStartButton()) {
            climber.rearStop();
        }
        if (oi.xbox.getBButton()){
            intake.intakeOut();
            
        }

        if (oi.xbox.getXButton()){
            //intake.intakeIn();
            ClimberWithStablizer climb = new ClimberWithStablizer();
            climb.start(); 
        }

        

        


        if (oi.playstayController.getBumper(Hand.kLeft)) {
        driveBase.drive(oi.playstayController.getY(Hand.kLeft) / -1, oi.playstayController.getY(Hand.kRight) / -1 );
        }

        if (oi.playstayController.getBumper(Hand.kRight)){
        driveBase.drive(oi.playstayController.getY(Hand.kLeft) / -1.5, oi.playstayController.getY(Hand.kRight) / -1.5 );
        }

        Scheduler.getInstance().run();
    }
}
