package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.ShooterWheel;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterOn extends Command{
	private double p_tilt = 0;
	private double i_tilt = 0;
	private double d_tilt = 0;
	private double f_tilt = 0;
	private double p_flat = 0;
	private double i_flat = 0;
	private double d_flat = 0;
	private double f_flat = 0;
	private double p_middle = 0;
	private double i_middle = 0;
	private double d_middle = 0;
	private double f_middle = 0;
	private double p_rear = 0;
	private double i_rear = 0;
	private double d_rear = 0;
	private double f_rear = 0;
	private double tilt_speed;
	private double flat_speed;
	private double middle_speed;
	private double rear_speed;
	
	
	public void ShooterON(){
		requires(Robot.shooterWheelFrontTilt);
		requires(Robot.shooterWheelFrontFlat);
		requires(Robot.shooterWheelMiddle);
		requires(Robot.shooterWheelRear);
	}
	
	//called before first run
	protected void initialize(){
		tilt_speed = SmartDashboard.getNumber("Shooter PID Front Tilt Speed", 0);
		flat_speed = SmartDashboard.getNumber("Shooter PID Front Flat Speed", 0);
		middle_speed = SmartDashboard.getNumber("Shooter PID Middle Speed", 0);
		rear_speed = SmartDashboard.getNumber("Shooter PID Rear Speed", 0);
	
		p_tilt =   SmartDashboard.getNumber("Shooter P_tilt",0);  
		i_tilt =   SmartDashboard.getNumber("Shooter I_tilt",0);  
		d_tilt =   SmartDashboard.getNumber("Shooter D_tilt",0);  
		f_tilt =   SmartDashboard.getNumber("Shooter F_tilt",0);  
		p_flat =   SmartDashboard.getNumber("Shooter P_flat",0);  
		i_flat =   SmartDashboard.getNumber("Shooter I_flat",0);  
		d_flat =   SmartDashboard.getNumber("Shooter D_flat",0);  
		f_flat =   SmartDashboard.getNumber("Shooter F_flat",0);  
		p_middle = SmartDashboard.getNumber("Shooter P_middle",0);
		i_middle = SmartDashboard.getNumber("Shooter I_middle",0);
		d_middle = SmartDashboard.getNumber("Shooter D_middle",0);
		f_middle = SmartDashboard.getNumber("Shooter F_middle",0);
		p_rear =   SmartDashboard.getNumber("Shooter P_rear",0);  
		i_rear =   SmartDashboard.getNumber("Shooter I_rear",0);  
		d_rear =   SmartDashboard.getNumber("Shooter D_rear",0);  
		f_rear =   SmartDashboard.getNumber("Shooter F_rear",0);  
		
		Robot.shooterWheelFrontTilt.setPID(p_tilt, i_tilt, d_tilt, f_tilt, 0, 100, 0);
		Robot.shooterWheelFrontTilt.set(tilt_speed);
		
		Robot.shooterWheelFrontFlat.setPID(p_flat, i_flat, d_flat, f_flat, 0, 100, 0);
		Robot.shooterWheelFrontFlat.set(flat_speed);
		
		Robot.shooterWheelMiddle.setPID(p_middle, i_middle, d_middle, f_middle, 0, 100, 0);
		Robot.shooterWheelMiddle.set(middle_speed);
		
		Robot.shooterWheelRear.setPID(p_rear, i_rear, d_rear, f_rear, 0, 100, 0);
		Robot.shooterWheelRear.set(middle_speed);
		
		Debugger.println("Front Tilt PID Setpoint: " +Robot.shooterWheelFrontTilt.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelFrontFlat.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelMiddle.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelRear.getSpeed() +
							" P_tilt: " + p_tilt + " D_tilt: " + d_tilt + " F_tilt: " + f_tilt + " \n"+
							" P_flat: " + p_flat + " D_flat: " + d_flat + " F_flat: " + f_flat + " \n"+
							" P_middle: " + p_middle + " D_middle: " + d_middle + " F_middle: " + f_middle + " \n"+
							" P_rear: " + p_rear + " D_rear: " + d_rear + " F_rear: " + f_rear + " \n", Robot.commands, Debugger.warning4);
		
	}

	@Override
	protected void execute() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean isFinished() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	protected void end() {
		// TODO Auto-generated method stub
		Robot.shooterWheelFrontTilt.set(0);
		
		Robot.shooterWheelFrontFlat.set(0);
		
		Robot.shooterWheelMiddle.set(0);
		
		Robot.shooterWheelRear.set(0);
		
	}

	@Override
	protected void interrupted() {
		// TODO Auto-generated method stub
		end();
		
	}
}
