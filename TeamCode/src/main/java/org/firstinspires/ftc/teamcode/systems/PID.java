package org.firstinspires.ftc.teamcode.systems;

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PID {
    private final double Kp;
    private final double Ki;
    private final double Kd;
    private double integral;
    private double previousError;

    public PID(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double Calculate(int targetPos, int currentPos) {
        double error = targetPos - currentPos;

        if(Ki == 0 && Kd == 0) {
            return Kp * error;
        }

        ElapsedTime runtime = new ElapsedTime();

        integral += error * runtime.seconds();

        double derivative = (error - previousError) / runtime.seconds();

        previousError = error;

        runtime.reset();

        return Kp * error + Ki * integral + Kd * derivative;
    }

    public void DashTelemetry(int currentPos, int targetPos){
        FtcDashboard dashboard = FtcDashboard.getInstance();
        Telemetry dashboardTelemetry = dashboard.getTelemetry();

        dashboardTelemetry.addData("currentPos: ", currentPos);
        dashboardTelemetry.addData("targetPos: ", targetPos);
        dashboardTelemetry.update();
    }

}
