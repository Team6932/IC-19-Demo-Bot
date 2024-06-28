/*
 * LOOK AT ActivateIntakeLimitCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.DemoSafetyCommands.Movement;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class TurnPowerDownCommand extends Command {

    private final DriveSubsystem driveSubsystem;
    
    public TurnPowerDownCommand (DriveSubsystem driveSubsystem) {
        this.driveSubsystem = driveSubsystem;
    }

    @Override
    public void initialize () {
        driveSubsystem.decreaseTurnPercent();
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
