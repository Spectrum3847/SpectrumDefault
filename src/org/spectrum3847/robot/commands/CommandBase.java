package org.spectrum3847.robot.commands;

import org.spectrum3847.robot.OI;
import org.spectrum3847.robot.subsystems.*;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public abstract class CommandBase extends Command {
    
    // Create a single static instance of all of your subsystems
    public static void init() {
        // This MUST be here. If the OI creates Commands (which it very likely
        // will), constructing it during the construction of CommandBase (from
        // which commands extend), subsystems are not guaranteed to be
        // yet. Thus, their requires() statements may grab null pointers. Bad
        // news. Don't move it.
    }

    public CommandBase(String name) {
        super(name);
    }

    public CommandBase() {
        super();
    }

}
