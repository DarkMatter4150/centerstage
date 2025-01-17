package org.firstinspires.ftc.teamcode.auto.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.systems.Hardware;
import org.firstinspires.ftc.teamcode.systems.PID;
import org.firstinspires.ftc.teamcode.systems.subsystems.Lift;

public class AutoLift {
    private DcMotor liftLeft = null;
    private DcMotor liftRight = null;

    HardwareMap hardwareMap;

    PID pid;

    public static double Kp = 0.005;
    public static double Ki = 0.0;
    public static double Kd = 0.0;

    final int[] POSITIONS = { 0, -82, -100, -645 };

    int targetPos = 0;

    public AutoLift() {}

    public AutoLift(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        liftLeft = hardwareMap.get(DcMotor.class, "liftLeft");
        liftRight = hardwareMap.get(DcMotor.class, "liftRight");
        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        pid = new PID(Kp, Ki, Kd);
    }

    public class LevelAction implements Action {
        int targetPosIndex;
        public LevelAction(int targetPosIndex) {
            this.targetPosIndex = targetPosIndex;
        }

        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            int currentPos = liftLeft.getCurrentPosition();

            targetPos = POSITIONS[targetPosIndex];

            double error = targetPos - currentPos;

            double power = Kp * error;

            liftLeft.setPower(power);
            liftRight.setPower(-power);

            return Math.abs(error) > 75;
        }
    }

    public Action LevelAction(int targetPosIndex) {
        return new LevelAction(targetPosIndex);
    }
}
