package org.spectrum3847.lib.drivers;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.Button;

public class SpectrumButton extends Button {

	XboxController controller;
	int button;
	
	public SpectrumButton(XboxController cont, XboxButton butNum){
		controller = cont;
		button = butNum.value;
	}
	
	public enum XboxButton{
		A(1), B(2), X(3), Y(4), BumperLeft(5), BumperRight(6), Back(7), Start(8), StickLeft(9), StickRight(10);
	
		public final int value;
		
		private XboxButton(int value){
			this.value = value;
		}
	}
	
	
	@Override
	public boolean get() {
		// TODO Auto-generated method stub
		return controller.getRawButton(button);	
			
	}

}
