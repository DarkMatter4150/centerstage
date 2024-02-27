package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Input {
    private OpMode opMode = null;
    public Gamepad currentGamepad1, currentGamepad2, previousGamepad1, previousGamepad2;

    public boolean multiplierToggle = false;

    public boolean manualLift = false;

    public int liftLevel = 0;

    public int intakeLevel = 0;

    public double intakePower = 0.0;
    public boolean intakeServo = false;

    public boolean bucketArm = false;

    public boolean bucketRot = false;

    public boolean plane = false;

    public int pullupLevel = 0;

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

    public void loop(int liftNumPos, int intakeNumPos, int pullupNumPos) {
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
            liftLevel %= liftNumPos;
        }
        else if (currentGamepad2.dpad_down && !previousGamepad2.dpad_down) {
            liftLevel--;
            liftLevel = (liftLevel + liftNumPos) % liftNumPos;
        }

        if(currentGamepad2.y && !previousGamepad2.y) {
            manualLift = !manualLift;
        }

        if (currentGamepad2.dpad_right && !previousGamepad2.dpad_right) {
            intakeLevel++;
            intakeLevel %= intakeNumPos;
        }
        else if (currentGamepad2.dpad_left && !previousGamepad2.dpad_left) {
            intakeLevel--;
            intakeLevel = (intakeLevel + intakeNumPos) % intakeNumPos;
        }

        if(currentGamepad1.right_bumper && !previousGamepad1.right_bumper) {
            multiplierToggle = !multiplierToggle;
        }

        intakePower = currentGamepad2.right_trigger - currentGamepad2.left_trigger;

        if (currentGamepad2.x && !previousGamepad2.x) {
            intakeServo = !intakeServo;
        }

        if (currentGamepad2.a && !previousGamepad2.a) {
            bucketArm = !bucketArm;
        }

        if (currentGamepad2.b && !previousGamepad2.b) {
            if(bucketArm) {
                bucketRot = !bucketRot;
            }
        }

        if(!bucketArm) {
            bucketRot = false;
        }

        if (currentGamepad2.left_bumper && !previousGamepad2.left_bumper) {
            plane = !plane;
        }

        if (currentGamepad2.right_bumper && !previousGamepad2.right_bumper) {
            pullupLevel++;
            if(pullupLevel >= pullupNumPos) pullupLevel = pullupNumPos - 1;
        }

        opMode.telemetry.addData("Speed", multiplierToggle ? "30%" : "100%");
        opMode.telemetry.addData("Intake Level", intakeLevel);
        opMode.telemetry.addData("Lift Level", liftLevel);
        opMode.telemetry.update();
    }

}
