package org.spectrum3847.lib.drivers;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.Robot;
import org.usb4java.BufferUtils;
import org.usb4java.DeviceHandle;
import org.usb4java.LibUsb;

public class AndroidAccessory {
	

private static int IN = 0x85;
private static int OUT = 0x07;

private static int VID = 0x22b8;
private static int PID = 0x2e76;

private static int ACCESSORY_PID = 0x2D01;
private static int ACCESSORY_PID_ALT = 0x2D00;

private static int LEN = 2;

/*
If you are on Ubuntu you will require libusb as well as the headers...
We installed the headers with "apt-get source libusb"
gcc simplectrl.c -I/usr/include/ -o simplectrl -lusb-1.0 -I/usr/include/ -I/usr/include/libusb-1.0

Tested for Nexus S with Gingerbread 2.3.4
*/

//static
static DeviceHandle handle;
static char stop;
static char success = 0;

public static int main (){
	Debugger.println("STARTING ANDROID MAIN", Robot.general, Debugger.error5);
	if(init() < 0){
		Debugger.println("Failed to Init USB", Robot.general, Debugger.error5);
		return 0;
	}
	Debugger.println("ANDROID FINISHED INIT", Robot.general, Debugger.error5);
	
	//doTransfer();
	if(setupAccessory(
			"Manufacturer",
			"Model",
			"Description",
			"VersionName",
			"http://Spectrum3847.org/RIOdroid",
			"2254711SerialNo.") < 0){
		Debugger.println("Error setting up accessory\n", Robot.general, Debugger.error5);
		deInit();
		return -1;
	};
	
	Debugger.println("ANDROID FINISHED SETUP", Robot.general, Debugger.error5);
	
	if(mainPhase() < 0){
		Debugger.println("Error during main phase\n", Robot.general, Debugger.error5);
		deInit();
		return -1;
	}	
	deInit();
	Debugger.println("Done, no errors\n", Robot.general, Debugger.error5);
	return 0;
}

static int mainPhase(){
	ByteBuffer buffer = ByteBuffer.allocate(5000);
	int response = 0;
	IntBuffer trans = IntBuffer.allocate(5000);

	response = LibUsb.bulkTransfer(handle,(byte) IN,buffer, trans, 0);
	if(response < 0){error(response);return -1;}

	response = LibUsb.bulkTransfer(handle,(byte) IN,buffer, trans,0);
	if(response < 0){error(response);return -1;}
	return 0;
}


static int init(){
	LibUsb.init(null);
	if((handle = LibUsb.openDeviceWithVidPid(null, (short)VID, (short)PID)) == null){
		Debugger.println("Problem acquireing handle", Robot.general, Debugger.error5);
		return -1;
	}
	LibUsb.claimInterface(handle, 0);
	return 0;
}

static int deInit(){
	//TODO free all transfers individually...
	//if(ctrlTransfer != NULL)
	//	libusb_free_transfer(ctrlTransfer);
	if(handle != null)
		LibUsb.releaseInterface (handle, 0);
	LibUsb.exit(null);
	return 0;
}

static int setupAccessory(
	String manufacturer,
	String modelName,
	String description,
	String version,
	String uri,
	String serialNumber){

	ByteBuffer ioBuffer = BufferUtils.allocateByteBuffer(2); //unsigned char ioBuffer[2];
	int devVersion;
	int response;
	int tries = 5;

	response = LibUsb.controlTransfer(
		handle, //handle
		(byte) 0xC0, //bmRequestType
		(byte) 51, //bRequest
		(short) 0, //wValue
		(short) 0, //wIndex
		ioBuffer, //data
        (long) 0 //timeout
	);

	if(response < 0){error(response);return-1;}
	devVersion = ioBuffer.get(1) << 8 | ioBuffer.get(0);
	Debugger.println("Verion Code Device: %d", Robot.general, Debugger.error5);
	
	//May have to put back in a short pause
	//usleep(1000);//sometimes hangs on the next transfer :(
    
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52, (short) 0, (short) 0,Util.stringToByteBuffer(manufacturer),0);
	if(response < 0){error(response);return -1;}
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52, (short) 0,(short) 1,Util.stringToByteBuffer(modelName),0);
	if(response < 0){error(response);return -1;}
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52,(short) 0,(short) 2,Util.stringToByteBuffer(description),0);
	if(response < 0){error(response);return -1;}
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52,(short) 0,(short) 3,Util.stringToByteBuffer(version),0);
	if(response < 0){error(response);return -1;}
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52,(short) 0,(short) 4,Util.stringToByteBuffer(uri),0);
	if(response < 0){error(response);return -1;}
	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 52,(short) 0,(short) 5,Util.stringToByteBuffer(serialNumber),0);
	if(response < 0){error(response);return -1;}

	Debugger.println("Accessory Identification sent\n", Robot.general, Debugger.error5);

	response = LibUsb.controlTransfer(handle,(byte) 0x40,(byte) 53,(short)0,(short)0,Util.stringToByteBuffer(" "),0);
	if(response < 0){error(response);return -1;}

	Debugger.println("Attempted to put device into accessory mode\n", Robot.general, Debugger.error5);

	if(handle != null)
		LibUsb.releaseInterface (handle, 0);


	for(;;){//attempt to connect to new PID, if that doesn't work try ACCESSORY_PID_ALT
		tries--;
		if((handle = LibUsb.openDeviceWithVidPid(null, (short) VID, (short) ACCESSORY_PID)) == null){
			if(tries < 0){
				return -1;
			}
		}else{
			break;
		}
		//Removed the sleep command
		//sleep(1);
	}
	LibUsb.claimInterface(handle, 0);
	Debugger.println("Interface claimed, ready to transfer data\n");
	return 0;
}

static void error(int code){
	switch(code){
	case LibUsb.ERROR_IO:
		Debugger.println("Error: LIBUSB_ERROR_IO\nInput/output error.\n");
		break;
	case LibUsb.ERROR_INVALID_PARAM:
		Debugger.println("Error: LIBUSB_ERROR_INVALID_PARAM\nInvalid parameter.\n");
		break;
	case LibUsb.ERROR_ACCESS:
		Debugger.println("Error: LIBUSB_ERROR_ACCESS\nAccess denied (insufficient permissions).\n");
		break;
	case LibUsb.ERROR_NO_DEVICE:
		Debugger.println("Error: LIBUSB_ERROR_NO_DEVICE\nNo such device (it may have been disconnected).\n");
		break;
	case LibUsb.ERROR_NOT_FOUND:
		Debugger.println("Error: LIBUSB_ERROR_NOT_FOUND\nEntity not found.\n");
		break;
	case LibUsb.ERROR_BUSY:
		Debugger.println("Error: LIBUSB_ERROR_BUSY\nResource busy.\n");
		break;
	case LibUsb.ERROR_TIMEOUT:
		Debugger.println("Error: LIBUSB_ERROR_TIMEOUT\nOperation timed out.\n");
		break;
	case LibUsb.ERROR_OVERFLOW:
		Debugger.println("Error: LIBUSB_ERROR_OVERFLOW\nOverflow.\n");
		break;
	case LibUsb.ERROR_PIPE:
		Debugger.println("Error: LIBUSB_ERROR_PIPE\nPipe error.\n");
		break;
	case LibUsb.ERROR_INTERRUPTED:
		Debugger.println("Error:LIBUSB_ERROR_INTERRUPTED\nSystem call interrupted (perhaps due to signal).\n");
		break;
	case LibUsb.ERROR_NO_MEM:
		Debugger.println("Error: LIBUSB_ERROR_NO_MEM\nInsufficient memory.\n");
		break;
	case LibUsb.ERROR_NOT_SUPPORTED:
		Debugger.println("Error: LIBUSB_ERROR_NOT_SUPPORTED\nOperation not supported or unimplemented on this platform.\n");
		break;
	case LibUsb.ERROR_OTHER:
		Debugger.println("Error: LIBUSB_ERROR_OTHER\nOther error.\n");
		break;
	default:
		Debugger.println("Error: unkown error\n");
	}
}

static void status(int code){
	switch(code){
		case LibUsb.TRANSFER_COMPLETED:
			Debugger.println("Success: LIBUSB_TRANSFER_COMPLETED\nTransfer completed.\n");
			break;
		case LibUsb.TRANSFER_ERROR:
			Debugger.println("Error: LIBUSB_TRANSFER_ERROR\nTransfer failed.\n");
			break;
		case LibUsb.TRANSFER_TIMED_OUT:
			Debugger.println("Error: LIBUSB_TRANSFER_TIMED_OUT\nTransfer timed out.\n");
			break;
		case LibUsb.TRANSFER_CANCELLED:
			Debugger.println("Error: LIBUSB_TRANSFER_CANCELLED\nTransfer was cancelled.\n");
			break;
		case LibUsb.TRANSFER_STALL:
			Debugger.println("Error: LIBUSB_TRANSFER_STALL\nFor bulk/interrupt endpoints: halt condition detected (endpoint stalled).\nFor control endpoints: control request not supported.\n");
			break;
		case LibUsb.TRANSFER_NO_DEVICE:
			Debugger.println("Error: LIBUSB_TRANSFER_NO_DEVICE\nDevice was disconnected.\n");
			break;
		case LibUsb.TRANSFER_OVERFLOW:
			Debugger.println("Error: LIBUSB_TRANSFER_OVERFLOW\nDevice sent more data than requested.\n");
			break;
		default:
			Debugger.println("Error: unknown error\nTry again(?)\n");
			break;
	}
}


}
