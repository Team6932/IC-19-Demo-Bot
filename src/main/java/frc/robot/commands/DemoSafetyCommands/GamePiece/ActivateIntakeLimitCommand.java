/*
 * This is a very simple command. It only runs a single method from a single subsystem once. 
 * I put it in its own file for organization purposes. Proper organization is crucial for efficient programming. 
 * I probably could've coded it using InstantCommand stuff, but I never looked into it. 
 */
package frc.robot.commands.DemoSafetyCommands.GamePiece;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class ActivateIntakeLimitCommand extends Command {

    // only uses one subsystem
    private final IntakeSubsystem intakeSubsystem;
    
    public ActivateIntakeLimitCommand (IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
    }

    // call the corresponding method in the subsystem once upon initialization
    @Override
    public void initialize () {
        intakeSubsystem.activateIntakeLimit();
    }

    // do nothing 
    @Override
    public void execute () {}

    // do nothing
    @Override
    public void end (boolean interrupted) {}

    // always return true (command only runs once)
    @Override
    public boolean isFinished () {
        return true;
    }
}
