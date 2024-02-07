package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.util.ElapsedTime;

public class Pullup {
    Hardware hardware;
    PID pid;

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
}
