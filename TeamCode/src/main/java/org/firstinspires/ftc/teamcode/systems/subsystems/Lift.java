package org.firstinspires.ftc.teamcode.systems.subsystems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.systems.Hardware;
import org.firstinspires.ftc.teamcode.systems.PID;

@Config
public class Lift {
    Hardware hardware;
    PID pid;

    public static double Kp = 0.005;
    public static double Ki = 0.0;
    public static double Kd = 0.0;

    final int[] POSITIONS = { 0, -222, -444, -645 };

    int targetPos = 0;

    public Lift() {}
    public Lift(Hardware hardware) {
        this.hardware = hardware;
        pid = new PID(Kp, Ki, Kd);
    }

    public void Move(int targetPosIndex) {
        targetPos = POSITIONS[targetPosIndex];

        int currentPos = hardware.getLiftPosition();

        double power = pid.Calculate(targetPos, currentPos);
        hardware.setLiftPower(power);

        pid.DashTelemetry(currentPos, targetPos);
    }

    public void Move(double joystick) {
        targetPos -= (joystick * 10);

        if(targetPos < -750) targetPos = -750;
        if(targetPos > 0) targetPos = 0;

        int currentPos = hardware.getLiftPosition();

        double power = pid.Calculate(targetPos, currentPos);
        hardware.setLiftPower(power);

        pid.DashTelemetry(currentPos, targetPos);
    }


    public int GetNumPositions() {
        return POSITIONS.length;
    }
}
