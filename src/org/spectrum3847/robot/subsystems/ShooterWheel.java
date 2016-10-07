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
	
	private double max = 1;
	private double min = -1;
	private double maxCurrentFwd = 10000;
	private double maxCurrentRev = -10000;
	private boolean currentLimit = false;
	

	public ShooterWheel(String name, SpectrumSpeedControllerCAN motor,  
			SpectrumSolenoid carriage){
		
		this.m_motor = motor;
	
		this.carriage_solenoid = carriage;
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
		
	public void setOpenLoop(double value){
		value = Util.limit(value, max, min);
		
		//Check the current limit if it is enabled
		if (currentLimit){
			double current = getCurrent();
			if (current > maxCurrentFwd && value > 0){
				value = 0;
			} else if (current < maxCurrentRev && value < 0){
				value = 0;
			}
		}
		
		m_left_motor.set(value);
		Debugger.println("MOTOR - " + getName() + ": " + value, Robot.output, Debugger.debug2);
	}
	
	public void setMax(double m){
		max = m;
	}
	
	public void setMin(double m){
		min = m;
	}
	
	//Set the max fwd current
	public void setMaxCurrentFwd(double c){
		maxCurrentFwd = c;
		currentLimit = true;
	}
	
	//Set the max rev current, SHOULD BE NEGATIVE
	public void setMaxCurrentRev(double c){
		maxCurrentRev = c;
		currentLimit = true;
	}
	
	public void disableCurrentLimit(){
		currentLimit = false;
	}
	
	public void enableCurrentLimit (){
		currentLimit = true;
	}
	
	public double getSpeed(){
		return m_left_motor.get();
	}
	
	public CANTalon getTalon(){
		return m_left_motor.getTalon();
	}
	
	public double getCurrent(){
		return m_left_motor.getCurrent();
	}
	
	public void setInverted(boolean value){
		m_left_motor.setInverted(value);
	}
	
	public void disable(){
		m_left_motor.disable();
	}



	
	
}
