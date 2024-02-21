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
    Pose2d startPoseSupport = new Pose2d(-40, 62, Math.toRadians(270));
    //Start Position for Support Robot
    //Y position may need to change to 65

    Vector2d[] boardsBlue = { new Vector2d(48, 30), new Vector2d(48, 36), new Vector2d(48, 42)};
    //Location of Backboard for Blue Alliance

    Vector2d[] stacksBlue = { new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};
    //Location of Stack for Blue Alliance

    Vector2d[] tapesStacks = {new Vector2d(-55, 33), new Vector2d(-40, 23), new Vector2d(-32.75, 30)};
    //Location of Tapes for Blue Alliance Support Role
    Vector2d parkSupport = new Vector2d(62, 15);

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
                        //This is for state
                        .strafeToLinearHeading(tapesStacks[loc.ordinal()], 220)
                        .strafeToLinearHeading(new Vector2d(-45, 10), Math.PI)
                        .strafeToConstantHeading(new Vector2d(48, 12))
                        .strafeTo(boardsBlue[loc.ordinal()])
                        .strafeTo(new Vector2d(36, 12))
                        .strafeTo(stacksBlue[2])
                        .strafeTo(new Vector2d(48, 12))
                        .strafeTo(boardsBlue[0])
                        .strafeTo(new Vector2d(36, 12))
                        .strafeTo(stacksBlue[2])
                        .strafeTo(new Vector2d(48, 12))
                        .strafeTo(boardsBlue[0])
                        .strafeTo(new Vector2d(48, 12))
                        .strafeTo(parkSupport)
////                        .splineTo(new Vector2d(30, 30), Math.PI / 2)
////                        .splineTo(new Vector2d(0, 60), Math.PI)
////                        .build());
//
////                        .strafeToConstantHeading(stacksBlue[1])
////                        //Intake code goes here
////                        .strafeToConstantHeading(new Vector2d(-18, 4))
////                        .strafeToConstantHeading(new Vector2d(48, 18))
////                        .strafeTo(boardsBlue[loc.ordinal()])
////                        //Outtake code goes here
////                        .build());
//                        //.splineToLinearHeading(new Pose2d(-30, 10, Math.toRadians(90)), Math.toRadians(0))
//                        .splineToLinearHeading(new Pose2d(-42, 10, Math.toRadians(90)), Math.toRadians(0))
//                        .strafeToLinearHeading(new Vector2d(42, 24), Math.toRadians(195))
//
//                        //Add distance sensor code here
//                        .strafeTo(new Vector2d(45, 24))
//                        .strafeTo(boardsBlue[loc.ordinal()])
                        .build());


    }

    @Override
    public void loop() {}
}
