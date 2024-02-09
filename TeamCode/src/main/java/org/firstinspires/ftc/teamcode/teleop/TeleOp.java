package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.Robot;

public class TeleOp extends OpMode {

    Robot robot = new Robot(this);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        robot.Drive();
        robot.Lift();
        robot.Intake();
        robot.Bucket();
        robot.Plane();
        robot.Pullup();
    }
}
