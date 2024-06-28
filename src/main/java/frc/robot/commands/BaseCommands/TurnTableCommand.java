/*
 * LOOK AT DriveCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.BaseCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.TurnTableSubsystem;

public class TurnTableCommand extends Command {
    
    private final TurnTableSubsystem turnTableSubsystem;
    private double turnTablePower;

    public TurnTableCommand (TurnTableSubsystem turnTableSubsystem, double turnTablePower) {
        this.turnTableSubsystem = turnTableSubsystem;
        this.turnTablePower = turnTablePower;
        addRequirements(turnTableSubsystem);
    }

    @Override
    public void initialize () {}

    @Override
    public void execute () {
        turnTableSubsystem.turnTable(turnTablePower);
    }

    @Override
    public void end (boolean interrupted) {
        turnTableSubsystem.turnTable(0);
    }

    @Override
    public boolean isFinished () {
        return false;
    }
}
