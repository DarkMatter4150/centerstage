package org.firstinspires.ftc.teamcode.auto;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.auto.subsystems.AutoBucket;
import org.firstinspires.ftc.teamcode.auto.subsystems.AutoIntake;
import org.firstinspires.ftc.teamcode.auto.subsystems.AutoLift;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.systems.Robot;
import org.firstinspires.ftc.teamcode.systems.subsystems.Bucket;
import org.firstinspires.ftc.teamcode.systems.subsystems.Intake;
import org.firstinspires.ftc.teamcode.systems.subsystems.Lift;
import org.firstinspires.ftc.teamcode.vision.VisionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
@Config
@Autonomous(group = "Autonomous", preselectTeleOp = "Tele")
public class BlueSupport extends OpMode {
    Pose2d startPoseSupport = new Pose2d(-40, 62, Math.toRadians(270));

    Vector2d[] tapes = {new Vector2d(-24, 36), new Vector2d(-36, 30), new Vector2d(-48, 36)};

    Vector2d[] boards = {new Vector2d(48, 30), new Vector2d(48, 36), new Vector2d(48, 42)};

    Vector2d[] stacks = {new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};

    AutoIntake intake;

    OpenCvWebcam camera;

    VisionPipeline pipeline;

    VisionPipeline.vPos loc = VisionPipeline.vPos.LEFT;

    //Lines 41 - 53 Initialize Mecanum Drive, Intake, Bucket, Lift, Camera, and the Pipeline for Vision

    Action toTapes, toBoards, toPark;
    SequentialAction placeSequence;
    @Override
    public void init() {
        intake = new AutoIntake(hardwareMap);
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPoseSupport);
        AutoBucket bucket = new AutoBucket(hardwareMap);
        AutoLift lift = new AutoLift(hardwareMap);
        VisionPipeline pipeline = new VisionPipeline("BLUE");
        //Initialize All motors used


        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(pipeline.WIDTH, pipeline.HEIGHT, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(pipeline);
                //Sets loc variable
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        //Declare all variables
        toTapes = drive.actionBuilder(drive.pose)
                .lineToY(-36)
                .strafeToConstantHeading(tapes[loc.ordinal()])
                .build();
        toBoards = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(new Vector2d(-36, -36))
                .strafeToConstantHeading(new Vector2d(-35, -4))
                .strafeToLinearHeading(new Vector2d(48, -13),Math.PI)
                .waitSeconds(2.6)
                .strafeTo(boards[loc.ordinal()])
                .build();
        toPark = drive.actionBuilder(drive.pose)
                .strafeTo(new Vector2d(48, -12))
                .strafeTo(new Vector2d(72, -10))
                .build();
        placeSequence = new SequentialAction(
                lift.LevelAction(1),
                bucket.ArmUpAction(),
                bucket.RotateDownAction(),
                new SleepAction(1000),
                bucket.RotateUpAction(),
                bucket.ArmDownAction(),
                lift.LevelAction(0)
        );
    }

    @Override
    public void init_loop() {
        loc = pipeline.getPos();
        telemetry.addData("Camera", "Position: " + loc);
        telemetry.update();
        //Displays on Driver Station
    }

    @Override
    public void start() {
        Actions.runBlocking(
            new SequentialAction(
                toTapes,
                intake.OutAction(),
                toBoards,
                placeSequence,
                toPark
            )

        );



    }

    @Override
    public void loop() {
    }
}
