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
public class BlueMainCharacter extends OpMode {
    @Override
    public void start() {
        Actions.runBlocking(
                new SequentialAction(
                        toTapes,
                        intake.OutAction(),
                        toBoards,
                        scoringSequence,
                        toPark


                )
        );

    }

    @Override
    public void loop() {
//        loc = pipeline.getPos();
//        telemetry.addData("Camera", "Position: " + loc);
//        telemetry.update();
    }

    Pose2d startPoseMC = new Pose2d(12, 62, Math.toRadians(180));

    Vector2d[] tapes = {new Vector2d(9, 30), new Vector2d(15, 24), new Vector2d(31, 30)};

    Vector2d[] boards = {new Vector2d(48, 30), new Vector2d(48, 36), new Vector2d(48, 42)};

    Vector2d[] stacks = {new Vector2d(-60, 36), new Vector2d(-60, 24), new Vector2d(-60, 12)};

    MecanumDrive drive;

    AutoIntake intake;

    Action toTapes;
    Action toBoards;
    Action toPark;

    SequentialAction scoringSequence;

    OpenCvWebcam camera;

    VisionPipeline pipeline;

    VisionPipeline.vPos loc = VisionPipeline.vPos.LEFT;

    @Override
    public void init() {
        MecanumDrive drive = new MecanumDrive(hardwareMap, startPoseMC);
        pipeline = new VisionPipeline("BLUE");
        intake = new AutoIntake(hardwareMap);
        AutoBucket bucket = new AutoBucket(hardwareMap);
        AutoLift lift = new AutoLift(hardwareMap);

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        //camera.getFocusControl().setMode(OpenCvCamera.FocusControl.Mode.AUTO);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
         @Override
         public void onOpened() {
             camera.startStreaming(pipeline.WIDTH, pipeline.HEIGHT, OpenCvCameraRotation.UPRIGHT);
             camera.setPipeline(pipeline);
         }

         @Override
         public void onError(int errorCode) {
             /*
              * This will be called if the camera could not be opened
              */
         }
     });

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
            scoringSequence = new SequentialAction(
                lift.LevelAction(1),
                bucket.ArmUpAction(),
                        bucket.RotateDownAction(),
                        new SleepAction(1000),
                bucket.RotateUpAction(),
                        bucket.ArmDownAction(),
                        lift.LevelAction(0)
                        );




    }
}