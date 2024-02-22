package org.firstinspires.ftc.teamcode.systems.subsystems;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Intake {
    Hardware hardware;
    final double[] POSITIONS = {1.0, 0.93, 0.8, 0.7};
    final double DOWN = 0.7;

    public Intake() {}
    public Intake(Hardware hardware) {
        this.hardware = hardware;
    }

    public void Down(int intakeLevel) {
        hardware.setIntakeServoPositionLeft(1 - POSITIONS[intakeLevel]);
        hardware.setIntakeServoPositionRight(POSITIONS[intakeLevel]);
    }

    public void Up() {
        hardware.setIntakeServoPositionLeft(1 - DOWN);
        hardware.setIntakeServoPositionRight(DOWN);
    }

    public int GetNumPositions() {
        return POSITIONS.length;
    }
}
