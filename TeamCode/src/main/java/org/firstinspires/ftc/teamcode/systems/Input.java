package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Input {
    private OpMode opMode = null;
    private Gamepad currentGamepad1, currentGamepad2, previousGamepad1, previousGamepad2;

    public int liftLevel = 0;

    public double intakePower = 0.0;
    public boolean intakeServo = false;

    public boolean bucketArm = false;
    public boolean bucketRot = false;

    public boolean plane = false;

    public boolean pullup = false;

    public Input() {}

    public Input(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        currentGamepad1 = new Gamepad();
        currentGamepad2 = new Gamepad();
        previousGamepad1 = new Gamepad();
        previousGamepad2 = new Gamepad();
    }

    public void loop() {
        try {
            previousGamepad1.copy(currentGamepad1);
            previousGamepad2.copy(currentGamepad2);

            currentGamepad1.copy(opMode.gamepad1);
            currentGamepad2.copy(opMode.gamepad2);
        }
        catch (Exception e) {
            // e.printStackTrace();
        }

        if (currentGamepad2.dpad_up && !previousGamepad2.dpad_up) {
            liftLevel++;
            liftLevel %= 4;
        }
        else if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {
            liftLevel--;
            liftLevel %= 4;
        }

        intakePower = currentGamepad2.right_trigger - currentGamepad2.left_trigger;

        if (currentGamepad2.x && !previousGamepad2.x) {
            intakeServo = !intakeServo;
        }

        if (currentGamepad2.a && !previousGamepad2.a) {
            bucketArm = !bucketArm;
        }

        if (currentGamepad2.b && !previousGamepad2.b) {
            bucketRot = !bucketRot;
        }

        if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
            plane = !plane;
        }

        if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
            pullup = !pullup;
        }
    }

}
