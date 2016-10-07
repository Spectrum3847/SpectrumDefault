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

	
	public SpectrumSpeedControllerCAN m_left_motor;
	public SpectrumSpeedControllerCAN m_right_motor;

	
	public Encoder m_only_encoder;
	
	public int m_right_PDP;
	public int m_left_PDP;
	
	public SpectrumSolenoid carriage_solenoid;
	
	private double max = 1;
	private double min = -1;
	private double maxCurrentFwd = 10000;
	private double maxCurrentRev = -10000;
	private boolean currentLimit = false;
	
	public ShooterWheel(String name, SpectrumSpeedControllerCAN right_motor, SpectrumSpeedControllerCAN left_motor,  
			Encoder only_encoder,
			int left_PDP, int right_PDP,
			SpectrumSolenoid carriage){
		
		this.m_left_motor = left_motor;
		this.m_right_motor = right_motor;
		
		this.m_only_encoder = only_encoder;
	
		this.m_right_PDP = right_PDP;
		this.m_left_PDP = left_PDP;
		
		this.carriage_solenoid = carriage;
	}

	public ShooterWheel(String name, SpectrumSpeedControllerCAN only_motor,  
			Encoder only_encoder,
			int only_PDP,
			SpectrumSolenoid carriage){
		
		this.m_left_motor = only_motor;
		
		this.m_only_encoder = only_encoder;
	
		this.m_left_PDP = only_PDP;
		
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
