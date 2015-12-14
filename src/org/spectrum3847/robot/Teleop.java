package org.spectrum3847.robot;

import org.spectrum3847.lib.drivers.DriveSignal;
import org.spectrum3847.lib.drivers.SharpGP2Y0A60SZ;
import org.spectrum3847.lib.drivers.SpectrumDigitalInput;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.robot.subsystems.MotorWithLimits;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


/**
 *
 * @author 
 */
public class Teleop {
	public static SharpGP2Y0A60SZ IR_Range = new SharpGP2Y0A60SZ(0);
	public static MotorWithLimits motor3 = new MotorWithLimits(new SpectrumSpeedController(new Talon(HW.PWM_3), HW.PWM_3_PDP), new SpectrumDigitalInput(HW.DIGITAL_IO_4), new SpectrumDigitalInput(HW.DIGITAL_IO_5));
	
    public static void init() {
        Scheduler.getInstance().removeAll();
    }

    public static void periodic() {
    	Dashboard.updateDashboard();
        Scheduler.getInstance().run();
        
        //Tank Drive
        Init.drive.setOpenLoop(new DriveSignal(OI.gamepad.getLeftY(), OI.gamepad.getRightY()));
        
    }

    public static void cancel() {
        Scheduler.getInstance().removeAll();
    }
}
