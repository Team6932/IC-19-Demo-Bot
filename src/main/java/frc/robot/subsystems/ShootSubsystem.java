/*
 * LOOK AT IntakeSubsystem.java FOR COMMENTS.
 * 
 * THIS SUBSYSTEM IS ORGANIZED EXACTLY THE SAME AS THAT ONE. 
 * THE ONLY DIFFERENCES ARE THE NAMES OF THE OBJECTS AND METHODS. 
 */
package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.PieceConstants;

public class ShootSubsystem extends SubsystemBase {
    
    private final PWMTalonSRX leftShootMotor = new PWMTalonSRX(PieceConstants.leftShootID);
    private final PWMTalonSRX rightShootMotor = new PWMTalonSRX(PieceConstants.rightShootID);

    private boolean shootLimit;
 
    public ShootSubsystem () {
        rightShootMotor.setInverted(true);
    }

    public void shoot (double shootPower) {

        if (getShootStatus()) {
            leftShootMotor.set(shootPower);
            rightShootMotor.set(shootPower);
        } else {
            leftShootMotor.set(0);
            rightShootMotor.set(0);
        }
    }

    private boolean getShootStatus () {
        return shootLimit;
    }

    public void deactivateShootLimit () {
        shootLimit = true;
    }

    public void activateShootLimit () {
        shootLimit = false;
    }

    @Override
    public void periodic () {
        SmartDashboard.putBoolean("shootStatus", getShootStatus());
    }
}
