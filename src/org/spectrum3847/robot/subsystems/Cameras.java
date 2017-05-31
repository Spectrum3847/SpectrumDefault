package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSolenoid;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.cscore.VideoSink;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Cameras extends Subsystem{

	private int p;
	static UsbCamera cam0;	
	static UsbCamera cam1;

	static VideoSink server;
	
	public Cameras() {
		// TODO Auto-generated constructor stub

	}

	public void intailizeCamera0(){
		if (server == null){
		    server = CameraServer.getInstance().addServer("serve_" + "CAMS");
		}
		server.setSource(null);
		if (cam0 == null){
			cam0 = new UsbCamera("Cam " + 0, 0);
		    server.setSource(cam0);
		    cam0.setExposureAuto();
        	cam0.setWhiteBalanceAuto();
		} else{
			server.setSource(cam0);
		}
	}
	
	public void intailizeCamera1(){
		if (server == null){
		    server = CameraServer.getInstance().addServer("serve_" + "CAMS");
		}
		server.setSource(null);
		if (cam1 == null){
			cam1 = new UsbCamera("Cam " + 1, 1);
		    server.setSource(cam1);
		    cam1.setExposureAuto();
        	cam1.setWhiteBalanceAuto();
		} else{
			server.setSource(cam1);
		}
	}
	
	public void removeCamera(){
		//CameraServer.getInstance().removeCamera("Cam " + p);
		//CameraServer.getInstance().removeServer("serve_" + cam.getName());
		server.setSource(null);
		//cam.free();
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
	}

}
