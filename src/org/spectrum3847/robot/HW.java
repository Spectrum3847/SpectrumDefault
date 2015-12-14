package org.spectrum3847.robot;

import edu.wpi.first.wpilibj.PowerDistributionPanel;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class HW {
    // For example to map the left and right motors, you could define the
    // following variables to use with your drivetrain subsystem.
    // public static int leftMotor = 1;
    // public static int rightMotor = 2;
	//Use ecplise refacotr tool to rename values for your specific robot
	
	//PDP Panel
	public static PowerDistributionPanel PDP = new PowerDistributionPanel();
    /**PDP Slots**/
	public static final int LEFT_DRIVE_MOTOR_0_PDP = 0;
	public static final int LEFT_DRIVE_MOTOR_1_PDP = 1;
	public static final int PWM_2_PDP = 2;
	public static final int PWM_3_PDP = 3;
	public static final int PWM_4_PDP = 4;
	public static final int PWM_5_PDP = 5;
	public static final int PWM_6_PDP = 6;
	public static final int PWM_7_PDP = 7;
	public static final int RIGHT_DRIVE_MOTOR_8_PDP = 8;
	public static final int RIGHT_DRIVE_MOTOR_9_PDP = 9;
	
	
	/**MOTOR ASSIGNMENTS (PWM)**/
    public static final int LEFT_DRIVE_MOTOR_0 = 0;
    public static final int LEFT_DRIVE_MOTOR_1 = 1;
    public static final int PWM_2 = 2;
    public static final int PWM_3 = 3;
    public static final int PWM_4 = 4;
    public static final int PWM_5 = 5;
    public static final int PWM_6 = 6;
    public static final int PWM_7 = 7;
    public static final int RIGHT_DRIVE_MOTOR_8 = 8;
    public static final int RIGHT_DRIVE_MOTOR_9 = 9;
    public static final int PWM_10 = 10;
    public static final int PWM_11 = 11;
    public static final int PWM_12 = 12;
    public static final int PWM_13 = 13;
    public static final int PWM_14 = 14;
    public static final int PWM_15 = 15;
    public static final int PWM_16 = 16;
    public static final int PWM_17 = 17;
    public static final int PWM_18 = 18;
    public static final int PWM_19 = 19;


    /**NON-DRIVEBASE MOTOR ASSIGNMENTS (CAN)**/
    public static final int CAN_MOTOR_1 = 1;
    public static final int CAN_MOTOR_2 = 2;
    public static final int CAN_MOTOR_3 = 3;
    public static final int CAN_MOTOR_4 = 4;
    
    /**DIGITAL SENSOR ALLOCATIONS**/
    public static final int DIGITAL_IO_1 = 1; 
    public static final int DIGITAL_IO_2 = 2;
    public static final int DIGITAL_IO_3 = 3;
    public static final int DIGITAL_IO_4 = 4; 
    public static final int DIGITAL_IO_5 = 5;
    public static final int DIGITAL_IO_6 = 6; 
    public static final int DIGITAL_IO_7 = 7; 
    public static final int DIGITAL_IO_8 = 8;
    public static final int DIGITAL_IO_9 = 9;  
    
    /**Pneumatics**/
    public static final int SOL_0 = 0;
    public static final int SOL_1 = 1;
    public static final int SOL_2 = 2;
    public static final int SOL_3 = 3;
    public static final int SOL_4 = 4;
    public static final int SOL_5 = 5;
    public static final int SOL_6 = 6;
    public static final int SOL_7 = 7;
    
    /**ANALOG SENSOR ALLOCATIONS**/
    public static final int ANALOG_IN_0 = 0;
    public static final int ANALOG_IN_1 = 1;
    public static final int ANALOG_IN_2 = 2;
    public static final int ANALOG_IN_3 = 3;
    
    /**RELAY ALLOCATIONS**/
    public static final int RELAY_ZERO = 0;
    public static final int RELAY_ONE = 1;
    public static final int RELAY_TWO = 2;
    public static final int RELAY_THREE = 3;

    /**JOYSTICKS/GAMEPAD ASSIGNMENTS**/
    public static final int USBPORT_0 = 0;
    public static final int USBPORT_1 = 1;
    public static final int USBPORT_2 = 2;
    public static final int USBPORT_3 = 3;
    public static final int USBPORT_4 = 4;
    public static final int DSControllerPort = 5;
}