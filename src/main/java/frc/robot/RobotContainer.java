package frc.robot;

// extremely chunky import list (could definitely be longer)
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.BaseCommands.DriveCommand;
import frc.robot.commands.BaseCommands.IntakeCommand;
import frc.robot.commands.BaseCommands.ShootCommand;
import frc.robot.commands.BaseCommands.TurnTableCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.ActivateIntakeLimitCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.ActivateShootLimitCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.ActivateTableLimitCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.DeactivateIntakeLimitCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.DeactivateShootLimitCommand;
import frc.robot.commands.DemoSafetyCommands.GamePiece.DeactivateTableLimitCommand;
import frc.robot.commands.DemoSafetyCommands.Movement.DrivePowerDownCommand;
import frc.robot.commands.DemoSafetyCommands.Movement.DrivePowerUpCommand;
import frc.robot.commands.DemoSafetyCommands.Movement.StopMovementCommand;
import frc.robot.commands.DemoSafetyCommands.Movement.TurnPowerDownCommand;
import frc.robot.commands.DemoSafetyCommands.Movement.TurnPowerUpCommand;
import frc.robot.constants.PieceConstants;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.IntakeSubsystem;
import frc.robot.subsystems.ShootSubsystem;
import frc.robot.subsystems.TurnTableSubsystem;

public class RobotContainer {

  // subsystems
  private final DriveSubsystem driveSubsystem = new DriveSubsystem();
  private final IntakeSubsystem intakeSubsystem = new IntakeSubsystem();
  private final ShootSubsystem shootSubsystem = new ShootSubsystem();
  private final TurnTableSubsystem turnTableSubsystem = new TurnTableSubsystem();

  /*
   * I could've probably included more of the game piece related commands up here.
   * However, I'm not sure how declaring stuff up here actually works if you want different input parameters.
   * I'm not an expert in Java and can't frequently test stuff on the actual IC-19 robot. 
   */

  // simple game piece related commands
  private final ActivateIntakeLimitCommand activateIntakeLimitCommand = new ActivateIntakeLimitCommand(intakeSubsystem);
  private final ActivateShootLimitCommand activateShootLimitCommand = new ActivateShootLimitCommand(shootSubsystem);
  private final ActivateTableLimitCommand activateTableLimitCommand = new ActivateTableLimitCommand(turnTableSubsystem);
  private final DeactivateIntakeLimitCommand deactivateIntakeLimitCommand = new DeactivateIntakeLimitCommand(intakeSubsystem);
  private final DeactivateShootLimitCommand deactivateShootLimitCommand = new DeactivateShootLimitCommand(shootSubsystem);
  private final DeactivateTableLimitCommand deactivateTableLimitCommand = new DeactivateTableLimitCommand(turnTableSubsystem);

  // simple driving related commands
  private final StopMovementCommand stopMovement = new StopMovementCommand(driveSubsystem);
  private final DrivePowerUpCommand increaseDrive = new DrivePowerUpCommand(driveSubsystem);
  private final DrivePowerDownCommand decreaseDrive = new DrivePowerDownCommand(driveSubsystem);
  private final TurnPowerUpCommand increaseTurn = new TurnPowerUpCommand(driveSubsystem);
  private final TurnPowerDownCommand decreaseTurn = new TurnPowerDownCommand(driveSubsystem);

  // logitech attack 3
  private final Joystick controller = new Joystick (0);

  public RobotContainer() {

    configureBindings();

    /*
     * This is the default (and only) command for driving. 
     */
    driveSubsystem.setDefaultCommand(new DriveCommand(driveSubsystem, 
      () -> -controller.getY(), () -> -controller.getX()));
  }

  /**
   * @Intake_2
   * @HighPow_1
   * @MidPow_4
   * @LowPow_5
   * @HighPow_Intake_3_AND_high_throttle
   * @MidPow_Intake_3_AND_mid_throttle
   * @LowPow_Intake_3_AND_low_throttle
   * @Increase-drive_6
   * @Decrease-drive_7
   * @Increase-turn_11
   * @Decrease-turn_10
   * @Start-game-piece_10_AND_11
   * @Stop-PIECE_6_AND_7
   * @Stop-MOVE_8_OR_9
   * @Stop-ALL_8_AND_9
   **/
  private void configureBindings() {

    new Trigger(() -> controller.getRawButton(1)).whileTrue(shootHigh());
    new Trigger(() -> controller.getRawButton(4)).whileTrue(shootMid());
    new Trigger(() -> controller.getRawButton(5)).whileTrue(shootLow());
    new Trigger(() -> controller.getRawButton(2)).whileTrue(intake());
    new Trigger(() -> controller.getRawButton(3)).and(() -> controller.getZ() < -0.3).whileTrue(shootHighIntake());
    new Trigger(() -> controller.getRawButton(3)).and(() -> controller.getZ() > -0.3)
      .and(() -> controller.getZ() < 0.3).whileTrue(shootMidIntake());
    new Trigger(() -> controller.getRawButton(3)).and(() -> controller.getZ() > 0.3).whileTrue(shootLowIntake());

    new Trigger(() -> controller.getRawButton(6)).and(() -> !controller.getRawButton(7)).onTrue(increaseDrive);
    new Trigger(() -> controller.getRawButton(7)).and(() -> !controller.getRawButton(6)).onTrue(decreaseDrive);
    new Trigger(() -> controller.getRawButton(11)).and(() -> !controller.getRawButton(10)).onTrue(increaseTurn);
    new Trigger(() -> controller.getRawButton(10)).and(() -> !controller.getRawButton(11)).onTrue(decreaseTurn);

    new Trigger(() -> controller.getRawButton(10))
      .and(() -> controller.getRawButton(11)).onTrue(deactivateGamePieceLimits());
    new Trigger(() -> controller.getRawButton(6))
      .and(() -> controller.getRawButton(7)).onTrue(activateGamePieceLimits());

    new Trigger(() -> controller.getRawButton(8)).or(() -> controller.getRawButton(9)).onTrue(stopMovement);

    new Trigger(() -> controller.getRawButton(8)).and(() -> controller.getRawButton(9)).onTrue(stopAll());
  }

  /* 
   * I didn't feel like doing anything fancy with organizing command groups. 
   * I just created some methods to return each of them and called them when necessary in configureBindings() above.
  */
  private Command shootHigh() {
    return new SequentialCommandGroup(
      new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootHighPower)
      )
    );
  }

  private Command shootMid() {
    return new SequentialCommandGroup(
      new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootMidPower)
      )
    );
  }

  private Command shootLow() {
    return new SequentialCommandGroup(
      new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootLowPower)
      )
    );
  }

  private Command intake() {
    return new ParallelCommandGroup(
      new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
      new IntakeCommand(intakeSubsystem, PieceConstants.intakePower)
    );
  }

  private Command shootHighIntake() {
    return new SequentialCommandGroup(
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower).withTimeout(0.5)),
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootHighPower),
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower)
      )
    );
  }

  private Command shootMidIntake() {
    return new SequentialCommandGroup(
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower).withTimeout(0.5)),
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootMidPower),
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower)
      )
    );
  }

  private Command shootLowIntake() {
    return new SequentialCommandGroup(
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, -PieceConstants.turnTablePower).withTimeout(0.5), 
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower).withTimeout(0.5)),
      new ParallelCommandGroup(
        new TurnTableCommand(turnTableSubsystem, PieceConstants.turnTablePower),
        new ShootCommand(shootSubsystem, PieceConstants.shootLowPower),
        new IntakeCommand(intakeSubsystem, PieceConstants.intakePower)
      )
    );
  }

  private Command deactivateGamePieceLimits() {
    return new ParallelCommandGroup(deactivateIntakeLimitCommand, deactivateShootLimitCommand, deactivateTableLimitCommand);
  }

  private Command activateGamePieceLimits() {
    return new ParallelCommandGroup(activateIntakeLimitCommand, activateShootLimitCommand, activateTableLimitCommand);
  }

  private Command stopAll() {
    return new ParallelCommandGroup(activateGamePieceLimits(), stopMovement);
  }
  
  /*
   * This could be a future project for someone who wants to create an autonomous routine for IC-19. 
   * I didn't want to work on it, so the method just returns null. 
   */
  public Command getAutonomousCommand() {
    return null;
  }
}
