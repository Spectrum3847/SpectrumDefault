package org.spectrum3847.lib.util;
 
import java.io.*;

import edu.wpi.first.wpilibj.ControllerPower;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import org.spectrum3847.robot.HW;
/**
 * 
 * @author  Based on 1114 - 2015 code
 */
public class Logger {
   
    private BufferedWriter writer;
    private boolean logging =false; 
    private final String loggerBoolean = "Logging";
    private static Logger instance;
    private String fileName ="log";
    private final String SDFileName = "File Name: ";
    DriverStation ds;
    
    private int max = 0;
    
    private String path;
    
    public static Logger getInstance() {
        if(instance == null) {
            instance = new Logger();
        }
        return instance;
    }
 
    private Logger() {
        this.ds = DriverStation.getInstance();
        SmartDashboard.putBoolean(this.loggerBoolean, this.logging);
        this.logging= SmartDashboard.getBoolean(this.loggerBoolean, false);
        SmartDashboard.putString(this.SDFileName, this.fileName);
        this.fileName = SmartDashboard.getString(SDFileName, null);
        File f = new File("/home/lvuser/logs");
        if(!f.exists()) {
        	System.out.println("/logs did not exist!");
        	System.out.println(f.mkdir());
        }
        else{
        	System.out.println("/logs exists!");
        }
        
    	File[] files = new File("/home/lvuser/logs").listFiles();
    	if(files != null) {
	        for(File file : files) {
	            if(file.isFile()) {
	                System.out.println(file.getName());
	                try {
	                    int index = Integer.parseInt(file.getName().split("_")[0]);
	                    if(index > max) {
	                        max = index;
	                    }
	                } catch (Exception e){
	                    e.printStackTrace();
	                }
	            }
	        }
    	} else {
    		max = 0;
    	}
    }
	    
    public void openFile() {
    	if(this.wantToLog() || this.ds.isFMSAttached()){
	        try{
	            path = this.getPath();
	            this.writer = new BufferedWriter(new FileWriter(path));
	            this.writer.write("Time, Battery Voltage, Brownout Stage 1, Brownout Stage 2");
	            this.writer.newLine();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    }
    
    private String getPath() {
    	this.fileName = SmartDashboard.getString(SDFileName, null);
        if(this.ds.isFMSAttached()) {
            return String.format("/home/lvuser/logs/%d_%s_%d_log.txt", ++this.max, this.ds.getAlliance().name(), this.ds.getLocation());
        }else if(this.fileName != null){ 
        	return String.format("/home/lvuser/logs/%d_%s.txt",++this.max,this.fileName);
        }else {
            return String.format("/home/lvuser/logs/%d_log.txt", ++this.max);
        }
    }
   
    public void logAll() {
    	if(this.wantToLog()){
	        try {
	        	this.writer.write(String.format("%f,", Timer.getFPGATimestamp()));
	        	
	        	//Voltage, Currents, Brownout States, Motor Speed Settings, 775 Pro enable/disable
	        	
	        	//Battery Voltage
	        	this.writer.write(String.format("%f", HW.PDP.getVoltage()));
	        	
	        	//Brownout States
	        	this.writer.write(String.format("%.3f,", ControllerPower.getEnabled6V()));
	        	this.writer.write(String.format("%.3f,", ControllerPower.getEnabled5V()));
	        	
	        	
	            
	            
	            this.writer.newLine();
	        }
	        catch (IOException e) {
	            e.printStackTrace();
	        }
    	}
    }
    
    public boolean wantToLog(){
    	this.logging= SmartDashboard.getBoolean(this.loggerBoolean, false);
    	return this.logging;
    }
    
    
    public void close() {
    	if(this.wantToLog()){
	    	if(this.writer != null) {
	            try {
	                this.writer.close();
	            }
	            catch (IOException e) {
	                e.printStackTrace();
	            }
	    	}
    	}
    }
}
