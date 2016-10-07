package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterOn extends Command{
	private CANTalon tilt_motor;
	private CANTalon flat_motor;
	private CANTalon middle_motor;
	private CANTalon rear_motor;
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
	private double tilt_speed;
	private double flat_speed;
	private double middle_speed;
	private double rear_speed;
	
	public ShooterON(){
		requires(Robot.ShooterWheel);
	}
	
	//called before furst run
	protected void initialize(){
		tilt_speed = SmartDashboard.getNumber("Shooter PID Front Tilt Speed", 0);
		flat_speed = SmartDashboard.getNumber("Shooter PID Front Flat Speed", 0);
		middle_speed = SmartDashboard.getNumber("Shooter PID Middle Speed", 0);
		rear_speed = SmartDashboard.getNumber("Shooter PID Rear Speed", 0);
		
		
	}
}
