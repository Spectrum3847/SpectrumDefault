package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumSpeedController;
import edu.wpi.first.wpilibj.CANTalon;

public class CANMotorSubsystem extends subsystem{

	private CANTalon tal;
	private SpectrumSpeedController speedController;
	
	public CANMotorSubsystem(String n, CANTalon t, int PDPnum) {
		super(n);
		tal = t;
		speedController = new SpectrumSpeedController(tal, PDPnum);
		
	}

	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}
	
	
	public CANTalon getTalon(){
		return this.tal;
	}
	
}
