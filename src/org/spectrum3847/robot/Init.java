package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.SpectrumEncoder;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.robot.commands.CommandBase;
import org.spectrum3847.robot.subsystems.Drive;
import org.spectrum3847.robot.subsystems.MotorWithLimits;

import edu.wpi.first.wpilibj.Talon;

/**
 *
 * @author Spectrum
 */
public class Init {

	public static Drive drive;
	public static MotorWithLimits motor3; 
	

    public static void init() {
        setupSubsystems();
    	CommandBase.init();
        System.out.println("init");
        Dashboard.intializeDashboard();
    }

    public static void periodic() {
    }
    
    public static void setupSubsystems(){
    	drive = new Drive("defaultDrive", 
    						HW.LEFT_DRIVE_MOTOR_0, HW.LEFT_DRIVE_MOTOR_0_PDP, 
    						HW.RIGHT_DRIVE_MOTOR_9, HW.RIGHT_DRIVE_MOTOR_9_PDP, 
    						new SpectrumEncoder(0, 1, 240), new SpectrumEncoder(8,9, 240)
    						);
    	
    	//If used the HW elements should be refactor-renamed to avoid them being used in another part of the code.
    	motor3 = new MotorWithLimits(HW.PWM_3, HW.PWM_3_PDP, HW.DIGITAL_IO_4, HW.DIGITAL_IO_5);
    }
}
