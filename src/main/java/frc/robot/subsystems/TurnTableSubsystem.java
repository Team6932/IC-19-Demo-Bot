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

public class TurnTableSubsystem extends SubsystemBase {
    
    private final PWMTalonSRX turnTableMotor = new PWMTalonSRX(PieceConstants.turnTableID);

    private boolean tableLimit;

    public TurnTableSubsystem () {}

    public void turnTable (double turnTablePower) {

        if (getTableStatus()) {
            turnTableMotor.set(turnTablePower);
        } else {
            turnTableMotor.set(0);
        }
    }
    
    private boolean getTableStatus () {
        return tableLimit;
    }

    public void deactivateTableLimit () {
        tableLimit = true;
    }

    public void activateTableLimit () {
        tableLimit = false;
    }

    @Override
    public void periodic () {
        SmartDashboard.putBoolean("tableLimit", getTableStatus());
    }
}
