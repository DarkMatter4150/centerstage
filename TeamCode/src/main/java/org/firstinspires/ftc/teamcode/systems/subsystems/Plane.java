package org.firstinspires.ftc.teamcode.systems.subsystems;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class Plane {
    Hardware hardware;
    final double LOAD = 0.0;
    final double SHOOT = 0.5;

    public Plane() {}

    public Plane(Hardware hardware) {
        this.hardware = hardware;
    }

    public void Load() {
        hardware.setPlanePosition(LOAD);
    }

    public void Shoot() {
        hardware.setPlanePosition(SHOOT);
    }
}
