package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Drivebase {

    Hardware hardware;
    Gamepad driver;
    public static double strafe = 1.1;

    public Drivebase() {}
    public Drivebase(Hardware hardware, Gamepad driver) {
        this.hardware = hardware;
        this.driver = driver;
    }

    public void Drive(boolean multiplierToggle, boolean fastMultiplierToggle) {
        double y = -driver.left_stick_y; // Remember, Y stick value is reversed
        double x = driver.left_stick_x * strafe; // Counteract imperfect strafing
        double rx = driver.right_stick_x;

        double denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        double multiplier = fastMultiplierToggle ? 0.75 : multiplierToggle ? 0.3 : 1;

        double frontLeftPower = ((y + x + rx) / denominator) * multiplier;
        double backLeftPower = ((y - x + rx) / denominator) * multiplier;
        double frontRightPower = ((y - x - rx) / denominator) * multiplier;
        double backRightPower = ((y + x - rx) / denominator) * multiplier;

        hardware.setPower(frontLeftPower, backLeftPower, frontRightPower, backRightPower);
    }
}
