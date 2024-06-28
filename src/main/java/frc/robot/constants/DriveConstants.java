package frc.robot.constants;

public class DriveConstants {

    // PWM IDs
    public static final int leftDriveID = 5;
    public static final int rightDriveID = 0;

    /*
     * These are two arrays used for safety and demo (demonstration) bot features. 
     * When turning the robot on, it will always default to the first index (value) of these arrays (which is 0.0). 
     * The speed levels of the robot can then be changed while the robot is in teleop mode. 
     * At demonstrations and camps, drivers (or team members) can easily limit the speed to a comfortable amount. 
     * 
     * Note: Java indices start at 0, so drivePercents[0] returns 0.0
     */
    public static final double[] drivePercents = {0, 0.5, 0.6, 0.7, 0.75, 0.8, 0.85, 0.9, 0.95, 1.0};
    public static final double[] turnPercents = {0, 0.75, 0.8, 0.85, 0.9, 0.95, 1.0};
}
