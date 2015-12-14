package org.spectrum3847.robot.subsystems;

import org.spectrum3847.lib.drivers.SpectrumDigitalInput;
import org.spectrum3847.lib.drivers.SpectrumSpeedController;

import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class MotorWithLimits extends Subsystem {

	private SpectrumSpeedController speedController;
	private SpectrumDigitalInput fwdLimit;
	private SpectrumDigitalInput revLimit;
	
	public MotorWithLimits(SpectrumSpeedController sc, SpectrumDigitalInput fwd, SpectrumDigitalInput rev){
		   speedController = sc;
		   fwdLimit = fwd;
		   revLimit = rev;
	}
	
	public MotorWithLimits(int scPWM, int scPDP, int fwd, int rev){
		this(new SpectrumSpeedController(new Talon(scPWM), scPDP), new SpectrumDigitalInput(fwd), new SpectrumDigitalInput(rev));
	}
	

	public void set(double value){
		if (value > 0 && !fwdLimit.get()){
			speedController.set(value);
		} else if (value < 0 && !revLimit.get()){
			speedController.set(value);
		} else {
			speedController.set(0);
		}
	}
	
	@Override
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
	}

}
