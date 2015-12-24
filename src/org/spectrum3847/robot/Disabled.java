package org.spectrum3847.robot;

import java.io.IOException;
import java.util.List;

import org.spectrum3847.lib.util.Debugger;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;

/**
 * @author matthew
 */
public class Disabled {

    static int t = 0;
    static boolean b = true;

    public static void init() {
        Scheduler.getInstance().removeAll();
        //Init.sendCam.start();
       adbStuff();
    }

    //Periodic method called roughly once every 20ms
    public static void periodic() {
        //Flash a light on the dashboard while disabled, know that the dashboard is refreshing.
        if (t > 20) {
            t = 0;
            b = !b;
            SmartDashboard.putBoolean("Disabled Toggle", b);
        }
        t++;
        Scheduler.getInstance().run();
        Dashboard.updateDashboard();
        Timer.delay(0.001);
    }
    
    private static void adbStuff(){
        JadbConnection jadb = null;
        List<JadbDevice> devices = null;
		try {
			jadb = new JadbConnection();
		} catch (IOException e) {
			// TODO Auto-generated catch block
	        Debugger.println("Failed at connection", Robot.general, Debugger.info3);
			e.printStackTrace();
		}
        try {
			devices = jadb.getDevices();
		} catch (IOException | JadbException e) {
			// TODO Auto-generated catch block

	        Debugger.println("Failed at devices", Robot.general, Debugger.info3);
			e.printStackTrace();
		}
        Debugger.println("Devices: " + devices.toString(), Robot.general, Debugger.info3);
    }
}
