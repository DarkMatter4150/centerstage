package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.Hardware;
import org.firstinspires.ftc.teamcode.systems.PID;

public class Pullup {
    Hardware hardware;
    PID pid;

    final int DOWN = 0;
    final int UP = 100;

    public Pullup() {}
    public Pullup(Hardware hardware) {
        this.hardware = hardware;
        pid = new PID(0.2, 0.0, 0.0);
    }

    public void Move(int targetPos) {
        int currentPos = hardware.getPullupPosition();

        ElapsedTime runtime = new ElapsedTime();

        double power = pid.Calculate(targetPos, currentPos, runtime);
        hardware.setPullupPower(power);
    }

    public void Down() {
        this.Move(DOWN);
    }

    public void Up() {
        this.Move(UP);
    }
}
