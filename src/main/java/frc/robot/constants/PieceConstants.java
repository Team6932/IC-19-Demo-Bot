package frc.robot.constants;

public class PieceConstants {
    
    // PWM IDs (what port they are connected to on the roboRIO)
    public static final int intakeID = 3;
    public static final int turnTableID = 6;
    public static final int leftShootID = 2; // switched from 1
    public static final int rightShootID = 4;

    /*
     * There are very few constants for motor power levels [-1 to 1] because I didn't want to add more and never found a need to. 
     * It is good practice to have a designated spot to declare constants instead of sporadically hard coding them.  
     */
    public static final double intakePower = 1.0;
    public static final double turnTablePower = 1.0;

    public static final double shootHighPower = 1.0;
    public static final double shootMidPower = 0.55;
    public static final double shootLowPower = 0.35;
}
