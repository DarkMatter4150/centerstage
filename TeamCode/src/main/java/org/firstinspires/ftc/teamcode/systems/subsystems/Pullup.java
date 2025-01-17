package org.firstinspires.ftc.teamcode.systems.subsystems;

import static android.os.SystemClock.sleep;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.systems.Hardware;
import org.firstinspires.ftc.teamcode.systems.PID;

public class Pullup {
    Hardware hardware;
    PID pid;

    public static double Kp = 0.01;
    public static double Ki = 0.0;
    public static double Kd = 0.0;

    final int[] POSITIONS = { 0, 1200, 2500, 1200};

    public Pullup() {}
    public Pullup(Hardware hardware) {
        this.hardware = hardware;
        pid = new PID(Kp, Ki, Kd);
    }

    public void Move(int targetPosIndex) {
        int targetPos = POSITIONS[targetPosIndex];

        int currentPos = hardware.getPullupPosition();

        double power = pid.Calculate(targetPos, currentPos);
        hardware.setPullupPower(power);

        pid.DashTelemetry(currentPos, targetPos);
    }

    public int GetNumPositions() {
        return POSITIONS.length;
    }
}
