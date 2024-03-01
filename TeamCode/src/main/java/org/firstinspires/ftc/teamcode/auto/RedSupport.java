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
import org.firstinspires.ftc.teamcode.vision.VisionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;

@Config
@Autonomous(group = "Autonomous", preselectTeleOp = "Tele")
public class RedSupport extends OpMode {
    Pose2d start = new Pose2d(16, -62, Math.toRadians(90));
    Vector2d[] tapes = { new Vector2d(14, -30), new Vector2d(15, -24), new Vector2d(31, -30)};
    Vector2d[] boards = {new Vector2d(48, -30), new Vector2d(48, -36), new Vector2d(48, -42)};
    int boardsCloseX = 38;
    int boardsFarX = 56;
    double outtakeTime = 1;
    Vector2d park = new Vector2d(62, -60);

    MecanumDrive drive;

    AutoIntake intake;
    AutoBucket bucket;
    AutoLift lift;

    String color = "RED";

    OpenCvWebcam camera;

    VisionPipeline pipeline;

    VisionPipeline.vPos loc = VisionPipeline.vPos.LEFT;

    Action toTapes, toBoards, toBoardClose, toPark;

    SequentialAction placeSequenceUp, placeSequenceDown, outtakeSequence;

    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, start);
        intake = new AutoIntake(hardwareMap);
        bucket = new AutoBucket(hardwareMap);
        lift = new AutoLift(hardwareMap);

        pipeline = new VisionPipeline(color);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        FtcDashboard.getInstance().startCameraStream(camera, 0);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(pipeline.WIDTH, pipeline.HEIGHT, OpenCvCameraRotation.UPRIGHT);
                camera.setPipeline(pipeline);
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
    }

    @Override
    public void init_loop() {
        loc = pipeline.getPos();
        telemetry.addData("Camera Position", loc);
        telemetry.update();
    }

    @Override
    public void start() {
        telemetry.clearAll();
        telemetry.addData("Saved Camera Position", loc);
        telemetry.update();

        toBoards = drive.actionBuilder(drive.pose)
                .strafeToConstantHeading(boards[loc.ordinal()])
                .turn(Math.toRadians(100))
                .build();
        placeSequenceUp = new SequentialAction(
                lift.LevelAction(1),
                bucket.ArmUpAction(),
                new SleepAction(1),
                bucket.RotateUpAction()
        );
        toBoardClose = drive.actionBuilder(new Pose2d(boards[loc.ordinal()], Math.toRadians(180)))
                .strafeTo(new Vector2d(boardsFarX, boards[loc.ordinal()].y))
                .strafeTo(new Vector2d(boardsCloseX, boards[loc.ordinal()].y))
                .build();
        placeSequenceDown = new SequentialAction(
                bucket.RotateDownAction(),
                bucket.ArmDownAction(),
                lift.LevelAction(0)
        );
        toTapes = drive.actionBuilder(new Pose2d(new Vector2d(38, boards[loc.ordinal()].y), Math.toRadians(180)))
                .strafeTo(tapes[loc.ordinal()])
                .build();
        outtakeSequence = new SequentialAction(
                intake.OutAction(),
                new SleepAction(outtakeTime),
                intake.StopAction()
        );
        toPark = drive.actionBuilder(new Pose2d(tapes[loc.ordinal()], Math.toRadians(180)))
                .strafeTo(new Vector2d(38, -58))
                .strafeTo(park)
                .build();

        Actions.runBlocking(
                new SequentialAction(
                        toBoards,
                        placeSequenceUp,
                        toBoardClose,
                        placeSequenceDown,
                        toTapes,
                        outtakeSequence,
                        toPark
                )
        );
    }

    @Override
    public void loop() {}
}
