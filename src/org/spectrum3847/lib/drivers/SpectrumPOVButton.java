package org.spectrum3847.lib.drivers;

import org.spectrum3847.lib.util.Debugger;
import org.spectrum3847.robot.Robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class SpectrumPOVButton extends Button {

	XboxController controller;
	XboxPOV povDirection;
	double value;
	boolean greater;
	
	public SpectrumPOVButton(XboxController cont, XboxPOV pov){
		controller = cont;
		this.povDirection = pov;
		
	}
	
	public enum XboxPOV{
		Unpressed(-1), Up(0), UpRight(45), Right(90),  DownRight(135), Down(180), DownLeft(225), Left(270), UpLeft(315);
	
		public final int value;
		
		private XboxPOV(int value){
			this.value = value;
		}
	}
	
	
	@Override
	public boolean get() {
		double val = this.povDirection.value;
		double currentVal = controller.getPOV(0);
		if (currentVal == val){
			Debugger.println("POV Current Value: )" + currentVal + " value: " + val, Robot.commands, Debugger.verbose1);
			return true;
		} else{
			return false;
		}
	}

}
