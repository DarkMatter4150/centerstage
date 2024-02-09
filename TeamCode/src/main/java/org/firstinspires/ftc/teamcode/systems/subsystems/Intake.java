package org.firstinspires.ftc.teamcode.systems.subsystems;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Intake {
    Hardware hardware;
    final double DOWN = 0.0;
    final double UP = 0.5;

    public Intake() {}
    public Intake(Hardware hardware) {
        this.hardware = hardware;
    }

    public void Down() {
        hardware.setIntakeServoPosition(DOWN);
    }

    public void Up() {
        hardware.setIntakeServoPosition(UP);
    }
}
