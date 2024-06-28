/*
 * LOOK AT DriveCommand.java FOR COMMENTS.
 * 
 * THIS Command IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.commands.BaseCommands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.ShootSubsystem;

public class ShootCommand extends Command {
    
    private ShootSubsystem shootSubsystem;
    private double shootPower;

    public ShootCommand (ShootSubsystem shootSubsystem, double shootPower) {
        this.shootSubsystem = shootSubsystem;
        this.shootPower = shootPower;
        addRequirements(shootSubsystem);
    }

    @Override
    public void initialize () {}

    @Override
    public void execute () {
        shootSubsystem.shoot(shootPower);
    }

    @Override
    public void end (boolean interrupted) {
        shootSubsystem.shoot(0);
    }
    
    @Override
    public boolean isFinished () {
        return false;
    }
}
