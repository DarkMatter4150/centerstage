package org.firstinspires.ftc.teamcode.auto.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class AutoBucket {
    private Servo armLeft = null;
    private Servo armRight = null;
    private Servo bucketRot = null;

    HardwareMap hardwareMap;
    final double DOWN = 0.45;
    final double UP = 1.0;

    final double ROT_DOWN = 0.02;
    final double ROT_UP = 0.34;

    public AutoBucket() {}

    public AutoBucket(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        armLeft = hardwareMap.get(Servo.class, "armLeft");
        armRight = hardwareMap.get(Servo.class, "armRight");
        bucketRot = hardwareMap.get(Servo.class, "bucketRot");
    }
    public class ArmDownAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(1 - DOWN);
            armRight.setPosition(DOWN);
            return false;
        }
    }

    public class ArmUpAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            armLeft.setPosition(1 - UP);
            armRight.setPosition(UP);
            return false;
        }
    }

    public class RotateDownAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bucketRot.setPosition(ROT_DOWN);
            return false;
        }
    }

    public class RotateUpAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket telemetryPacket) {
            bucketRot.setPosition(ROT_UP);
            return false;
        }
    }

    public Action ArmDownAction() {
        return new ArmDownAction();
    }

    public Action ArmUpAction() {
        return new ArmUpAction();
    }

    public Action RotateDownAction() {
        return new RotateDownAction();
    }

    public Action RotateUpAction() { return new RotateUpAction(); }
}
