package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;

public class Drivebase {

    Hardware hardware;
    Gamepad driver;
    public static double strafe = 1.1;
    public static double multiplier = 1.0;

    public Drivebase() {}
    public Drivebase(Hardware hardware, Gamepad driver) {
        this.hardware = hardware;
        this.driver = driver;
    }

    public void Drive() {
        double y = -driver.left_stick_y; // Remember, Y stick value is reversed
        double x = driver.left_stick_x * strafe; // Counteract imperfect strafing
        double rx = driver.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        SetMultiplier();

        double frontLeftPower = ((y + x + rx) / denominator) * multiplier;
        double backLeftPower = ((y - x + rx) / denominator) * multiplier;
        double frontRightPower = ((y - x - rx) / denominator) * multiplier;
        double backRightPower = ((y + x - rx) / denominator) * multiplier;

        hardware.setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower);
    }

    private void SetMultiplier() {
        //TODO: implement multiplier
    }
}
