package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.*;
import org.firstinspires.ftc.teamcode.systems.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.systems.subsystems.Drivebase;
import org.firstinspires.ftc.teamcode.systems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.systems.subsystems.Lift;
import org.firstinspires.ftc.teamcode.systems.subsystems.Plane;
import org.firstinspires.ftc.teamcode.systems.subsystems.Pullup;

public class Robot {
    private OpMode opMode = null;
    private Hardware hardware;
    private Input input;

    private Drivebase drivebase;
    private Lift lift;
    private Intake intake;
    private Bucket bucket;
    private Plane plane;
    private Pullup pullup;

    public Robot() {}

    public Robot(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        hardware = new Hardware(opMode);
        hardware.init();

        input = new Input(opMode);
        input.init();

        drivebase = new Drivebase(hardware, opMode.gamepad1);
        lift = new Lift(hardware);
        intake = new Intake(hardware);
        bucket = new Bucket(hardware);
        plane = new Plane(hardware);
        pullup = new Pullup(hardware);
    }

    public void Drive() {
        drivebase.Drive();
    }

    public void Lift() {
        lift.Move(input.liftLevel);
    }

    public void Intake() {
        if(input.intakeServo) {
            intake.Up();
        }
        else {
            intake.Down();
        }
    }

    public void Bucket() {
        if(input.bucketArm) {
            bucket.ArmUp();
        }
        else {
            bucket.ArmDown();
        }

        if(input.bucketRot) {
            bucket.RotateUp();
        }
        else {
            bucket.RotateDown();
        }
    }

    public void Plane() {
        if(input.plane) {
            plane.Shoot();
        }
        else {
            plane.Load();
        }
    }

    public void Pullup() {
        if(input.pullup) {
            pullup.Up();
        }
        else {
            pullup.Down();
        }
    }
}
