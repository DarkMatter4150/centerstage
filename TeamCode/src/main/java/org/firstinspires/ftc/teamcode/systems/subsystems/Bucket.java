package org.firstinspires.ftc.teamcode.systems.subsystems;

import static android.os.SystemClock.sleep;

import android.os.AsyncTask;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Bucket {
    Hardware hardware;

    final double DOWN = 0.45;
    final double UP = 1.0;

    final double ROT_DOWN = 0.02;
    final double ROT_UP = 0.34;

    public Bucket() {}

    public Bucket(Hardware hardware) {
        this.hardware = hardware;
    }

    public void ArmDown() {
        hardware.setArmServoPositionLeft(1 - DOWN);
        hardware.setArmServoPositionRight(DOWN);
    }

    public void ArmUp() {
        hardware.setArmServoPositionLeft(1 - UP);
        hardware.setArmServoPositionRight(UP);
    }

    public void RotateDown() {
        hardware.setBucketRotPosition(ROT_DOWN);
    }

    public void RotateUp() {
        hardware.setBucketRotPosition(ROT_UP);
    }
}