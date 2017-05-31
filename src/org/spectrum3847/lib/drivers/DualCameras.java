package org.spectrum3847.lib.drivers;

import java.util.ArrayList;
import org.opencv.core.*;   // MatOfPoint, Rect, etc.
import org.opencv.imgproc.Imgproc;
import org.spectrum3847.vision.LiftTracker;

import edu.wpi.cscore.*;  // CvSink, UsbCamera, etc.
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Timer;

public class DualCameras {

	public static final int IMG_WIDTH = 320;
	public static final int IMG_HEIGHT = 240;

	private static final int FPS = 30;

	//private static final int EXPOSURE = 4;  // filter out most natural light (to focus on LED-tape)

 
	/* Minimal vars specific to GRIP-auton logic.
	*/
	private static LiftTracker pipeline;
	public static ArrayList<Rect> rectangles = new ArrayList<Rect>();
	public static boolean isImageReady = false;
	
	
	private static boolean useGearCamera = false;
	// !gearCam implies shoot_other_cam...

	private static CvSink cvSink2 = null;
	private static CvSink cvSink1 = null;

	private static final Mat image = new Mat();
	private static final Scalar WHITE = new Scalar(255, 255, 255);

	public static void cameraInit() {
		cameraSwitcher ();
	
		/* uncomment this after you have generated and imported such code.
		pipeline = new GripPipeline();
		*/
	
	}
	
	public static void useFrontCam(){
		useCam0();
	}
	
	public static void useRearCam(){
		useCam1();
		
	}
	
	public static void useCam0() {
		if (useGearCamera)
			flipcams();
	}
	
	public static void useCam1() {
		if (!useGearCamera)
			flipcams();
	}
	
	public static boolean flipcams() {
	
		useGearCamera = !useGearCamera;
		if(useGearCamera){
			cvSink2.setEnabled(false);
			if (FPS > 12) Timer.delay(.25);
			cvSink1.setEnabled(true);
		} 
		else{
			cvSink1.setEnabled(false);
			if (FPS > 12) Timer.delay(.25);
			cvSink2.setEnabled(true);
		}
		return useGearCamera;
	}
	
	private static void processImage()
	{
		pipeline.process(image);
		ArrayList<MatOfPoint> contours = pipeline.filterContoursOutput(); 
	
		/* If vision-control is desired, 
		 *   then autonomousPeriodic or Vision-Command.execute methods need to 
		 *   sync on 'DualCameraStarter.rectancles' just like this does.
		 *		 *  Such code should skip unless if(...THIS...isImageReady),
		 *     and within that if{} they need to reset ...THIS...isImageReady back to false.
		 */
  		synchronized(rectangles) 
  		{
  			rectangles.clear();  // keep this same instance, but reset it.
  			
			for (int x = 0; x < contours.size(); x++) {
				Rect r = Imgproc.boundingRect(contours.get(x));
				rectangles.add(r);

				Imgproc.rectangle(image,
						new Point(r.x, r.y),
						new Point(r.x+r.width, r.y+r.height),
						WHITE, 5);
			} //end for loop 
	
			isImageReady = true;  
			//  now next cycle of Vision-Commands will have latest contour data.
			//     (such periodic/executes methods should use the else{} to safety-off or slow motors...)
				
  		}  //  now Vision-Commands can get latest distances via TargetAnalysis calcs...
	}
	
	private static void cameraSwitcher (){
		
    	Thread switcherThread = new Thread(() -> {
	    		
            UsbCamera camera1 = CameraServer.getInstance().startAutomaticCapture(0);
            camera1.setResolution(IMG_WIDTH, IMG_HEIGHT);
            camera1.setFPS(FPS);
            camera1.setExposureAuto();//setExposureManual(EXPOSURE);
            UsbCamera camera2 = CameraServer.getInstance().startAutomaticCapture(1);
            camera2.setResolution(IMG_WIDTH, IMG_HEIGHT);
            camera2.setFPS(FPS);
            camera2.setExposureAuto();//.setExposureManual(EXPOSURE);

            cvSink2 = CameraServer.getInstance().getVideo(camera2);
            cvSink2.setEnabled(false);

            cvSink1 = CameraServer.getInstance().getVideo(camera1);

            CvSource outputStream = CameraServer.getInstance().putVideo("Switcher", IMG_WIDTH, IMG_HEIGHT);
	            
	           
            while(!Thread.interrupted()) {
	            
            	long framestatus = (useGearCamera)
            		? cvSink1.grabFrame(image) 
            		: cvSink2.grabFrame(image);     
	                
            	if (framestatus == 0) continue;
	            	
            	if (pipeline != null) 
            		//processImage();  // do GRIP stuff...
	
            	outputStream.putFrame(image);
	
            	System.gc();
            }
	            
	    });
    	
	    switcherThread.start();
	}

}
