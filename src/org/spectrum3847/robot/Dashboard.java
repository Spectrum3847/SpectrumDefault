package org.spectrum3847.robot;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * @author matthew, JAG
 */
public class Dashboard {
	
	public static final boolean ENABLE_DASHBOARD = true;
	
	
	static final double SHORT_DELAY = .015;
    static final double LONG_DELAY = 1;
    
    static double shortOldTime = 0.0;
    static double longOldTime = 0.0;


    public static void intializeDashboard() {
    	if(ENABLE_DASHBOARD){
    		//SmartDashboard.putBoolean("Relay", false);
    		//SmartDashboard.putNumber("Motor 1", 0);
        	
    		SmartDashboard.putBoolean("Compressor", true);
    		SmartDashboard.putNumber("Shooter PID Front Tilt Speed", -15000);
    		SmartDashboard.putNumber("Shooter PID Front FlatSpeed", 18000);
    		SmartDashboard.putNumber("Shooter PID Middle Speed", -15000);
    		SmartDashboard.putNumber("Shooter PID Rear Speed", 18000);
    		
    		SmartDashboard.putNumber("Shooter P_tilt", .13);
    		SmartDashboard.putNumber("Shooter I_tilt", 0);
    		SmartDashboard.putNumber("Shooter D_tilt", .95);
    		SmartDashboard.putNumber("Shooter F_tilt", .024);
    		SmartDashboard.putNumber("Shooter P_flat", .13);
    		SmartDashboard.putNumber("Shooter I_flat", 0);
    		SmartDashboard.putNumber("Shooter D_flat", .95);
    		SmartDashboard.putNumber("Shooter F_flat", .024);
    		SmartDashboard.putNumber("Shooter P_middle", .13);
    		SmartDashboard.putNumber("Shooter I_middle", 0);
    		SmartDashboard.putNumber("Shooter D_middle", .95);
    		SmartDashboard.putNumber("Shooter F_middle", .024);
    		SmartDashboard.putNumber("Shooter P_rear", .13);
    		SmartDashboard.putNumber("Shooter I_rear", 0);
    		SmartDashboard.putNumber("Shooter D_rear", .95);
    		SmartDashboard.putNumber("Shooter F_rear", .024);
    	}
    }

    private static void updatePutShort() {
    	//SmartDashboard.putNumber("Motor 1", Motor_1.get());
    	SmartDashboard.putNumber("Drive LeftY: ", HW.Driver_Gamepad.getLeftY());
    	SmartDashboard.putNumber("Drive RightX: ", HW.Driver_Gamepad.getRightX());
    	SmartDashboard.putNumber("Drive Trigger Left: ", HW.Driver_Gamepad.getLeftTrigger());
    	SmartDashboard.putNumber("Drive Trigger Right: ", HW.Driver_Gamepad.getRightTrigger());
    	SmartDashboard.putNumber("Drive Left:", Robot.leftDrive.get());
    	SmartDashboard.putNumber("Drive Right:", Robot.rightDrive.get());

    }
    
    private static void updatePutLong(){
    	//SmartDashboard.putBoolean("Compressor", Compressor.enabled());
    	SmartDashboard.putNumber("Compressor Current", Robot.compressor.getCompressorCurrent());
    	SmartDashboard.putNumber("Drive Left Current:", Robot.leftDrive.getSignedCurrent());
    	SmartDashboard.putNumber("Drive Right Current:", Robot.rightDrive.getSignedCurrent());
    	
    }

    
    public static void updateDashboard() {
    	if (ENABLE_DASHBOARD) {
            if ((Timer.getFPGATimestamp() - shortOldTime) > SHORT_DELAY) {
                shortOldTime = Timer.getFPGATimestamp();
                updatePutShort();
            }
            if ((Timer.getFPGATimestamp() - longOldTime) > LONG_DELAY) {
                //Thing that should be updated every LONG_DELAY
                longOldTime = Timer.getFPGATimestamp();
                updatePutLong();
            }
        }
    }
}
