package org.firstinspires.ftc.teamcode.systems.subsystems;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Bucket {
    Hardware hardware;

    final double DOWN = 0.0;
    final double UP = 0.0;

    final double ROT_DOWN = 0.0;
    final double ROT_UP = 0.0;

    public Bucket() {}

    public Bucket(Hardware hardware) {
        this.hardware = hardware;
    }

    public void ArmDown() {
        hardware.setArmPosition(DOWN);
    }

    public void ArmUp() {
        hardware.setArmPosition(UP);
    }

    public void RotateDown() {
        hardware.setBucketRotPosition(ROT_DOWN);
    }

    public void RotateUp() {
        hardware.setBucketRotPosition(ROT_UP);
    }
}
