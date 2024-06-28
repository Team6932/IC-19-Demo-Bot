package frc.robot.commands.BaseCommands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {
    
    // uses one subsystem and two doubles
    private final DriveSubsystem driveSubsystem;
    private DoubleSupplier drivePower, turnPower;

    public DriveCommand (DriveSubsystem driveSubsystem, DoubleSupplier drivePower, DoubleSupplier turnPower) {
        this.driveSubsystem = driveSubsystem;
        this.drivePower = drivePower;
        this.turnPower = turnPower;
        addRequirements(driveSubsystem);
    }

    // no setup required
    @Override
    public void initialize () {}

    // call the needed method from the subsystem
    // use the input parameters
    @Override
    public void execute () {
        driveSubsystem.drive(drivePower.getAsDouble(), turnPower.getAsDouble());
    }

    // turn the motors off if the command is interrupted
    @Override
    public void end (boolean interrupted) {
        driveSubsystem.drive(0, 0);
    }

    // never stop naturally running
    @Override
    public boolean isFinished () {
        return false;
    }
}
