package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.util.ElapsedTime;

public class PID {
    private double Kp;
    private double Ki;
    private double Kd;
    private double integral;
    private double previousError;

    public PID(double Kp, double Ki, double Kd) {
        this.Kp = Kp;
        this.Ki = Ki;
        this.Kd = Kd;
    }

    public double Calculate(int targetPos, int currentPos, ElapsedTime runtime) {
        double error = targetPos - currentPos;

        integral += error * runtime.seconds();

        double derivative = (error - previousError) / runtime.seconds();

        previousError = error;

        runtime.reset();

        return Kp * error + Ki * integral + Kd * derivative;
    }

}
