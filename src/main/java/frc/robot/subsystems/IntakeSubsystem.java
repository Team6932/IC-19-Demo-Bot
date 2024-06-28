package frc.robot.subsystems;

import edu.wpi.first.wpilibj.motorcontrol.PWMTalonSRX;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.constants.PieceConstants;

public class IntakeSubsystem extends SubsystemBase {
    
    // Talon motor controller using PWM
    private final PWMTalonSRX intakeMotor = new PWMTalonSRX(PieceConstants.intakeID);

    // boolean for safety features (default to false)
    private boolean intakeLimit;

    public IntakeSubsystem () {}

    /*
     * If intakeLimit is true, allow the intake to run normally. 
     * I probably should've remained intakeLimit to something more intuitive like intakeAllow. 
     * I could just do ctrl+H, but I'm lazy. 
     */
    public void intake (double intakePower) {
        if (intakeLimit) {
            intakeMotor.set(intakePower);
        } else {
            intakeMotor.set(0);
        }
    }

    // get the current value of intakeLimit
    private boolean getIntakeStatus () {
        return intakeLimit;
    }

    // set intakeLimit to true (bad variable naming)
    public void deactivateIntakeLimit () {
        intakeLimit = true;
    }

    // set intakeLimit to false (bad variable naming)
    public void activateIntakeLimit () {
        intakeLimit = false;
    }

    // display information
    @Override
    public void periodic () {
        SmartDashboard.putBoolean("intakeLimit", getIntakeStatus());
    }
}
