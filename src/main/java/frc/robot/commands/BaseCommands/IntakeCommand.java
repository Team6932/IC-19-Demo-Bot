/*
 * LOOK AT DriveCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.BaseCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.IntakeSubsystem;

public class IntakeCommand extends Command {
    
    private final IntakeSubsystem intakeSubsystem;
    private double intakePower;

    public IntakeCommand (IntakeSubsystem intakeSubsystem, double intakePower) {
        this.intakeSubsystem = intakeSubsystem;
        this.intakePower = intakePower;
        addRequirements(intakeSubsystem);
    }

    @Override
    public void initialize () {}

    @Override
    public void execute () {
        intakeSubsystem.intake(intakePower);
    }

    @Override
    public void end (boolean interrupted) {
        intakeSubsystem.intake(0);
    }

    @Override
    public boolean isFinished () {
        return false;
    }
}
