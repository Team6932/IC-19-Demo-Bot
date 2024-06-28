/*
 * LOOK AT ActivateIntakeLimitCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.DemoSafetyCommands.GamePiece;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class DeactivateIntakeLimitCommand extends Command {

    private final IntakeSubsystem intakeSubsystem;
    
    public DeactivateIntakeLimitCommand (IntakeSubsystem intakeSubsystem) {
        this.intakeSubsystem = intakeSubsystem;
    }

    @Override
    public void initialize () {
        intakeSubsystem.deactivateIntakeLimit();
    }

    @Override
    public void execute () {}

    @Override
    public void end (boolean interrupted) {}

    @Override
    public boolean isFinished () {
        return true;
    }
}
