package org.spectrum3847.robot.commands;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.robot.subsystems.ShooterWheel;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class ShooterOn extends Command{
	private double p = 0;
	private double i = 0;
	private double d = 0;
	private double f = 0;
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
		
		Robot.shooterWheelFrontTilt.setPID(p, i, d, f, 0, 100, 0);
		Robot.shooterWheelFrontTilt.set(tilt_speed);
		
		Robot.shooterWheelFrontFlat.setPID(p, i, d, f, 0, 100, 0);
		Robot.shooterWheelFrontFlat.set(flat_speed);
		
		Robot.shooterWheelMiddle.setPID(p, i, d, f, 0, 100, 0);
		Robot.shooterWheelMiddle.set(middle_speed);
		
		Robot.shooterWheelRear.setPID(p, i, d, f, 0, 100, 0);
		Robot.shooterWheelRear.set(middle_speed);
		
		Debugger.println("Front Tilt PID Setpoint: " +Robot.shooterWheelFrontTilt.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelFrontFlat.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelMiddle.getSpeed() +
							"Front Tilt PID Setpoint: " + Robot.shooterWheelRear.getSpeed() +
							" P: " + p + " D: " + d + " F: " + f + " \n", Robot.commands, Debugger.warning4);
		
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
