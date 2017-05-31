package org.spectrum3847.robot;


import org.spectrum3847.lib.drivers.SpectrumButton;
import org.spectrum3847.lib.drivers.SpectrumButton.XboxButton;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    //// CREATING BUTTONS
	//SpectrumDefault includes a number of ways of interfacing with your gamepad
	//One of these is through the variety of "buttons" - implementations of WPILib's Button abstract
	//Types of buttons include: Buttons, AxisButtons, and POVButtons
	//All are used in a similar fashion, below is an example use of the SpectrumButton Class
	//new SpectrumButton(HW.Operator_Gamepad, XboxButton.B).toggleWhenPressed(new ShooterOn());
	
	//Refer to the SpectrumButton, SpectrumAxisButton, and SpectrumPOVButton classes for more use cases
	
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // four ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
	
	// Toggle a command when the button is pushed each time, on, off, on, off, etc
	// button.toggleWhenPressed(new ExampleCommand());

    //Use this constructor to setup up button schedulers for commands
    public OI() {
    	//Driver
    	
    	//Operator
    	
    }
}

