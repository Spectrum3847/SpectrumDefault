package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import org.spectrum3847.lib.drivers.SpectrumSpeedControllerCAN;
import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.lib.util.Util;
import org.spectrum3847.robot.Robot;
import org.spectrum3847.lib.drivers.SpectrumSolenoid;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.command.Subsystem;


public class ShooterWheel extends Subsystem{

	
	public SpectrumSpeedControllerCAN m_motor;


	public SpectrumSolenoid carriage_solenoid;

	

	public ShooterWheel(String name, SpectrumSpeedControllerCAN motor,  
			SpectrumSolenoid carriage){
		
		this.m_motor = motor;
	
		this.carriage_solenoid = carriage;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

	
	public void setPID(double p, double i, double d, double f, int izone, double closeLoopRampRate, int profile){
		m_motor.getTalon().setPID(p, i, d, f, izone, closeLoopRampRate, profile);
	}
	
	public void set(double speed){
		m_motor.getTalon().set(speed);
	}
	
	public double getSpeed(){
		return m_motor.get();
	}
	
	public double getError(){
		return m_motor.getTalon().getError();
	}
	
	public CANTalon getTalon(){
		return m_motor.getTalon();
	}
	
	public double getCurrent(){
		return m_motor.getCurrent();
	}
	
	public void setInverted(boolean value){
		m_motor.setInverted(value);
	}
	
	public void disable(){
		m_motor.disable();
	}



	
	
}
