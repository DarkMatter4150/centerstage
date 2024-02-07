package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

public class TeleOp extends OpMode {

    Hardware hardware = new Hardware(this);
    Pullup pullup;

    public int targetPos;

    @Override
    public void init() {
        hardware.init();
        pullup = new Pullup(hardware);
    }

    @Override
    public void loop() {
        targetPos -= (-gamepad2.left_stick_y * 10);
        pullup.Move(targetPos);
    }
}
