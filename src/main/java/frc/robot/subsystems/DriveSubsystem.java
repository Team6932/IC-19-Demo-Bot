package frc.robot.subsystems;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.DriveConstants;

public class DriveSubsystem extends SubsystemBase {
    
    // Talon motor controllers using PWM
    private final PWMTalonSRX leftDriveMotor = new PWMTalonSRX(DriveConstants.leftDriveID);
    private final PWMTalonSRX rightDriveMotor = new PWMTalonSRX(DriveConstants.rightDriveID);

    // differential drive system
    private final DifferentialDrive driveSystem = new DifferentialDrive(leftDriveMotor, rightDriveMotor);

    // integers used for safety features (default to 0)
    private int drivePercentID, turnPercentID;

    public DriveSubsystem () {
        // invert one motor
        rightDriveMotor.setInverted(true);
    }

    // drive based on input speeds and the safety limits (multiply input speeds by a percent)
    public void drive (double drivePower, double turnPower) {
        driveSystem.arcadeDrive(drivePower * DriveConstants.drivePercents[drivePercentID], 
            turnPower * DriveConstants.turnPercents[turnPercentID]);
    }

    // get what percent to multiply drive input speeds by
    private double getDrivePercent () {
        return DriveConstants.drivePercents[drivePercentID];
    }

    // get what drive array id (array found in DriveConstants.java)
    private int getDrivePercentID () {
        return drivePercentID;
    }

    // step to the next value of the drive array unless it is at the max value
    public void increaseDrivePercent () {
        drivePercentID = getDrivePercentID() + 1;

        if (getDrivePercentID() >= DriveConstants.drivePercents.length) {
            drivePercentID = DriveConstants.drivePercents.length - 1;
        }
    }

    // step to the previous value of the drive array unless it is at the min value
    public void decreaseDrivePercent () {
        drivePercentID = getDrivePercentID() - 1;

        if (getDrivePercentID() < 0) {
            drivePercentID = 0;
        }
    }

    // immediately return to the first value of the drive array (which should always be 0)
    public void stopDrive () {
        drivePercentID = 0;
    }

    // get what percent to multiple turning speeds by
    private double getTurnPercent () {
        return DriveConstants.turnPercents[turnPercentID];
    }

    // get what turning array id (array found in DriveConstants.java)
    private int getTurnPercentID () {
        return turnPercentID;
    }

    // step to the next value of the turning array unless it is at the max value
    public void increaseTurnPercent () {
        turnPercentID = getTurnPercentID() + 1;

        if (getTurnPercentID() >= DriveConstants.turnPercents.length) {
            turnPercentID = DriveConstants.turnPercents.length - 1;
        }
    }

    // step to the previous value of turning array unless it is at the min value
    public void decreaseTurnPercent () {
        turnPercentID = getTurnPercentID() - 1;

        if (getTurnPercentID() < 0) {
            turnPercentID = 0;
        }
    }

    // immediately return to the first value of the turning array (which should always be 0)
    public void stopTurn () {
        turnPercentID = 0;
    }

    // get if either the drive array id or the turning array id are above 0
    private boolean getMovementStatus () {

        if (getDrivePercentID() == 0 && getTurnPercentID() == 0) {
            return false;
        } else {
            return true; 
        }
    }

    // display information
    @Override
    public void periodic () {
        SmartDashboard.putNumber("drivePercent", getDrivePercent());
        SmartDashboard.putNumber("turnPercent", getTurnPercent());

        SmartDashboard.putNumber("driveID", getDrivePercentID());
        SmartDashboard.putNumber("turnID", getTurnPercentID());

        SmartDashboard.putBoolean("movementStatus", getMovementStatus());
    }
}
