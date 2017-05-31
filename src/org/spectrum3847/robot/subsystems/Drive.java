package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.command.Subsystem;

public class Drive extends Subsystem {

    public SpectrumSpeedControllerCAN m_left_motor;
    public SpectrumSpeedControllerCAN m_right_motor;
    private SpectrumSolenoid brakes;

    public Drive(String name, SpectrumSpeedControllerCAN left_drive, SpectrumSpeedControllerCAN right_drive, SpectrumSolenoid brakeSol){
    	this.m_left_motor = left_drive;
    	this.m_right_motor = right_drive;
    	brakes= brakeSol;
    }
    
    public Drive(String name, SpectrumSpeedControllerCAN left_drive, SpectrumSpeedControllerCAN right_drive){
    	this.m_left_motor = left_drive;
    	this.m_right_motor = right_drive;
    }

    private void setDriveOutputs(DriveSignal signal) {
        m_left_motor.set(signal.leftMotor);
        m_right_motor.set(-signal.rightMotor);
    }
    
    public void arcadeDrive(double throttle, double turnPower, double deadband, boolean squaredInputs){
    	double leftMotorSpeed;
        double rightMotorSpeed;
        
        throttle = Util.limit(throttle, 1);
        turnPower = Util.limit(turnPower, 1);
        
        //Scales the joystick range beyond the deadband to 0-1
        if (Math.abs(throttle) < deadband){
      	  throttle = 0;
        } else if (throttle < 0){
            throttle = (throttle + deadband) / (1-deadband);
        } else {
        	throttle = (throttle - deadband) / (1-deadband);
        }
        
        if (Math.abs(turnPower) < deadband){
      	  turnPower = 0;
        } else if (turnPower < 0){
        	turnPower = (turnPower + deadband) / (1-deadband);
        } else {
        	turnPower = (turnPower - deadband) / (1-deadband);
        }
        
        
        if (squaredInputs) {
          // square the inputs (while preserving the sign) to increase fine control
          // while permitting full power
          if (throttle >= 0.0) {
            throttle = (throttle * throttle);
          } else {
            throttle = -(throttle * throttle);
          }
          
          if (turnPower >= 0.0) {
            turnPower = (turnPower * turnPower);
          } else {
            turnPower = -(turnPower * turnPower);
          }
        }
        
        //Positive Turn Power turns left
        if (throttle > 0.0) {
            if (turnPower > 0.0) {
              leftMotorSpeed = throttle - turnPower;
              rightMotorSpeed = Math.max(throttle, turnPower);
            } else {
              leftMotorSpeed = Math.max(throttle, -turnPower);
              rightMotorSpeed = throttle + turnPower;
            }
          } else {
            if (turnPower > 0.0) {
              leftMotorSpeed = -Math.max(-throttle, turnPower);
              rightMotorSpeed = throttle + turnPower;
            } else {
              leftMotorSpeed = throttle - turnPower;
              rightMotorSpeed = -Math.max(-throttle, -turnPower);
            }
          }
        
        
          this.setDriveOutputs(new DriveSignal(leftMotorSpeed, rightMotorSpeed));
    	
          //System.out.println("ARCADE DRIVE");
          
    }
    
    public void extendBrakes(){
    	brakes.set(true);
    	System.out.println("EXTEND");
    }
    
    public void retractBrakes(){
    	System.out.println("RETRACT");
    	brakes.set(false);
    }

    public void talonBrakeMode(boolean brakeMode) {
		if(brakeMode){
			Robot.left_drive_talon_1.enableBrakeMode(true);
			Robot.left_drive_talon_2.enableBrakeMode(true);
			Robot.left_drive_talon_3.enableBrakeMode(true);
			Robot.right_drive_talon_1.enableBrakeMode(true);
			Robot.right_drive_talon_2.enableBrakeMode(true);
			Robot.right_drive_talon_3.enableBrakeMode(true);
		}
		else{
			Robot.left_drive_talon_1.enableBrakeMode(false);
			Robot.left_drive_talon_2.enableBrakeMode(false);
			Robot.left_drive_talon_3.enableBrakeMode(false);
			Robot.right_drive_talon_1.enableBrakeMode(false);
			Robot.right_drive_talon_2.enableBrakeMode(false);
			Robot.right_drive_talon_3.enableBrakeMode(false);
		}
		
	}
    
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}
}
