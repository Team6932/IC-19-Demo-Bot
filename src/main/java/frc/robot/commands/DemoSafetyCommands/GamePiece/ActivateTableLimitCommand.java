/*
 * LOOK AT ActivateIntakeLimitCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.DemoSafetyCommands.GamePiece;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurnTableSubsystem;

public class ActivateTableLimitCommand extends Command {

    private final TurnTableSubsystem turnTableSubsystem;
    
    public ActivateTableLimitCommand (TurnTableSubsystem turnTableSubsystem) {
        this.turnTableSubsystem = turnTableSubsystem;
    }

    @Override
    public void initialize () {
        turnTableSubsystem.activateTableLimit();
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
