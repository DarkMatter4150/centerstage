package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.systems.Hardware;
import org.firstinspires.ftc.teamcode.systems.PID;

public class Lift {
    Hardware hardware;
    PID pid;

    final int[] POSITIONS = { 0, -222, -444, -645 };

    public Lift() {}
    public Lift(Hardware hardware) {
        this.hardware = hardware;
        pid = new PID(0.2, 0.0, 0.0);
    }

    public void Move(int targetPosIndex) {
        int targetPos = POSITIONS[targetPosIndex];

        int currentPos = hardware.getLiftPosition();

        ElapsedTime runtime = new ElapsedTime();

        double power = pid.Calculate(targetPos, currentPos, runtime);
        hardware.setLiftPower(power);
    }
}
