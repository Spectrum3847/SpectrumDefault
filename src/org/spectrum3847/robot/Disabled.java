package org.spectrum3847.robot;

import java.io.IOException;
import java.util.List;

import org.spectrum3847.lib.util.Debugger;
import org.usb4java.Context;
import org.usb4java.Device;
import org.usb4java.DeviceDescriptor;
import org.usb4java.DeviceList;
import org.usb4java.LibUsb;
import org.usb4java.LibUsbException;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import se.vidstige.jadb.JadbConnection;
import se.vidstige.jadb.JadbDevice;
import se.vidstige.jadb.JadbException;
import org.spectrum3847.lib.drivers.AndroidAccessory;

/**
 * @author matthew
 */
public class Disabled {

    static int t = 0;
    static boolean b = true;

    public static void init() {
        Scheduler.getInstance().removeAll();
        //Init.sendCam.start();
       //adbStuff();
       //AndroidAccessory.main();
        usb4javsStuff();
    }
    
    public static void usb4javsStuff(){
    	Context context = new Context();
        int result = LibUsb.init(context);
        if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to initialize libusb.", result);
        Device motoE = findDevice((short)0x22b8, (short) 0x2e76); //Product Vendor and ID for 2015 Moto E I'm using to Test
        Debugger.println(LibUsb.getDeviceAddress(motoE));
    }
    
    public static Device findDevice(short vendorId, short productId)
    {
        // Read the USB device list
        DeviceList list = new DeviceList();
        int result = LibUsb.getDeviceList(null, list);
        if (result < 0) throw new LibUsbException("Unable to get device list", result);

        try
        {
            // Iterate over all devices and scan for the right one
            for (Device device: list)
            {
                DeviceDescriptor descriptor = new DeviceDescriptor();
                result = LibUsb.getDeviceDescriptor(device, descriptor);
                if (result != LibUsb.SUCCESS) throw new LibUsbException("Unable to read device descriptor", result);
                if (descriptor.idVendor() == vendorId && descriptor.idProduct() == productId) return device;
            }
        }
        finally
        {
            // Ensure the allocated device list is freed
            LibUsb.freeDeviceList(list, true);
        }

        // Device not found
        return null;
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
	        Debugger.println("Failed at connection", Robot.general, Debugger.fatal6);
			e.printStackTrace();
		}
        try {
			devices = jadb.getDevices();
		} catch (IOException | JadbException e) {
			// TODO Auto-generated catch block

	        Debugger.println("Failed at devices", Robot.general, Debugger.fatal6);
			e.printStackTrace();
		}
        Debugger.println("Devices: " + devices.toString(), Robot.general, Debugger.fatal6);
        JadbDevice motoE = devices.get(0);
        try {
        	motoE.executeShell("screencap", "/sdcard/screenCODE.png");
        	Debugger.println("Got to shell command " +motoE.getSerial(), Robot.general, Debugger.fatal6);
        	//Debugger.println("State: " + motoE.getState(), Robot.general, Debugger.fatal6);
        } catch  (IOException | JadbException e) {
			// TODO Auto-generated catch block

	        Debugger.println("Failed at activity launch", Robot.general, Debugger.fatal6);
			e.printStackTrace();
		}
    }
}
