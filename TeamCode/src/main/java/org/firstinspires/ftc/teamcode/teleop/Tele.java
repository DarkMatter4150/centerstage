package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.systems.Input;
import org.firstinspires.ftc.teamcode.systems.Robot;

@TeleOp(group = "TeleOp")
public class Tele extends OpMode {

    Robot robot = new Robot(this);

    @Override
    public void init() {
        robot.init();
    }

    @Override
    public void loop() {
        robot.RefreshInput();

        robot.Drive();
        robot.Lift();
        robot.Intake();
        robot.Bucket();
        robot.Plane();
        robot.Pullup();
    }
}
