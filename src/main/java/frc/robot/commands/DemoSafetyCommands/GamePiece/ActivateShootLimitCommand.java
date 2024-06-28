/*
 * LOOK AT ActivateIntakeLimitCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.DemoSafetyCommands.GamePiece;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootSubsystem;

public class ActivateShootLimitCommand extends Command {

    private final ShootSubsystem shootSubsystem;
    
    public ActivateShootLimitCommand (ShootSubsystem shootSubsystem) {
        this.shootSubsystem = shootSubsystem;
    }

    @Override
    public void initialize () {
        shootSubsystem.activateShootLimit();
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