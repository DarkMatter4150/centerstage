package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;

@Config
@Autonomous(group = "Autonomous", preselectTeleOp = "Tele")
public class BlueSupport extends OpMode {
    Pose2d startPoseSupport = new Pose2d(-40, 65, Math.toRadians(-90));
    //Start Position for Support Robot

    Vector2d[] boardsBlue = { new Vector2d(48, 30), new Vector2d(48, 36), new Vector2d(48, 42)};
    //Location of Backboard for Blue Alliance

    Vector2d[] stacksBlue = { new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};
    //Location of Stack for Blue Alliance

    MecanumDrive drive;

    enum TeamPropLoc {
        LEFT, CENTER, RIGHT
    }

    TeamPropLoc loc = TeamPropLoc.CENTER;


    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, startPoseSupport);
    }

    @Override
    public void start() {
        Actions.runBlocking(
                drive.actionBuilder(startPoseSupport)
//                        .splineTo(new Vector2d(30, 30), Math.PI / 2)
//                        .splineTo(new Vector2d(0, 60), Math.PI)
//                        .build());

//                        .strafeToConstantHeading(stacksBlue[1])
//                        //Intake code goes here
//                        .strafeToConstantHeading(new Vector2d(-18, 4))
//                        .strafeToConstantHeading(new Vector2d(48, 18))
//                        .strafeTo(boardsBlue[loc.ordinal()])
//                        //Outtake code goes here
//                        .build());
                        //.splineToLinearHeading(new Pose2d(-30, 10, Math.toRadians(90)), Math.toRadians(0))
                        .splineToLinearHeading(new Pose2d(-42, 10, Math.toRadians(90)), Math.toRadians(0))
                        .strafeToLinearHeading(new Vector2d(42, 24), Math.toRadians(180))
                        .strafeTo(new Vector2d(60, 24))
                        .lineToX(48)
                        //Add distance sensor code here
                        .strafeTo(boardsBlue[loc.ordinal()])
                        .splineTo(new Vector2d(0,0), Math.toRadians(90))
                        .build());


    }

    @Override
    public void loop() {}
}
