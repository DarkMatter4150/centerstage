package org.firstinspires.ftc.teamcode.auto.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.systems.Hardware;

public class AutoIntake {
    private DcMotor intake = null;
    private Servo intakeServoLeft = null;
    private Servo intakeServoRight = null;

    HardwareMap hardwareMap;
    final double[] POSITIONS = {1.0, 0.93, 0.8, 0.7};
    final double DOWN = 0.7;

    public AutoIntake() {}
    public AutoIntake(HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        intake = hardwareMap.get(DcMotor.class, "intake");
        intakeServoLeft = hardwareMap.get(Servo.class, "intakeServoLeft");
        intakeServoRight = hardwareMap.get(Servo.class, "intakeServoRight");
    }

    public class UpAction implements Action {
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeServoLeft.setPosition(1 - DOWN);
            intakeServoRight.setPosition(DOWN);
            return false;
        }
    }

    public class DownAction implements Action {
        int intakeLevel;
        public DownAction(int intakeLevel) {
            this.intakeLevel = intakeLevel;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intakeServoLeft.setPosition(1 - POSITIONS[intakeLevel]);
            intakeServoRight.setPosition(POSITIONS[intakeLevel]);
            return false;
        }
    }

    public class InAction implements Action {
        int seconds;
        public InAction(int seconds) {
            this.seconds = seconds;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(1);
            new SleepAction(seconds).run(packet);
            intake.setPower(0);
            return false;
        }
    }

    public class OutAction implements Action {
        int seconds;
        public OutAction(int seconds) {
            this.seconds = seconds;
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(-1);
            new SleepAction(seconds).run(packet);
            intake.setPower(0);
            return false;
        }
    }

    public class StopAction implements Action {
        public StopAction() {
        }
        @Override
        public boolean run(@NonNull TelemetryPacket packet) {
            intake.setPower(0);
            return false;
        }
    }

    public Action UpAction() {
        return new UpAction();
    }

    public Action DownAction(int intakeLevel) {
        return new DownAction(intakeLevel);
    }

    public Action InAction(int seconds) {
        return new InAction(seconds);
    }

    public Action OutAction(int seconds) {
        return new OutAction(seconds);
    }

    public Action StopAction() {
        return new StopAction();
    }
}
