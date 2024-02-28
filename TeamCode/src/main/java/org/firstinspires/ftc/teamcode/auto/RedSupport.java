package org.firstinspires.ftc.teamcode.auto;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.teamcode.roadrunner.MecanumDrive;
import org.firstinspires.ftc.teamcode.vision.VisionPipeline;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvWebcam;
@Config
@Autonomous(group = "Autonomous", preselectTeleOp = "Tele")
public class RedSupport extends OpMode {
    Pose2d startPoseSupport = new Pose2d(-40, -62, Math.toRadians(90));

    Vector2d[] tapes = {new Vector2d(-24, -36), new Vector2d(-36, -30), new Vector2d(-48, -36)};

    Vector2d[] boards = {new Vector2d(48, -30), new Vector2d(48, -36), new Vector2d(48, -42)};

    Vector2d[] stacks = {new Vector2d(-60, -36), new Vector2d(-60, -24), new Vector2d(-60, -12)};

    MecanumDrive drive;

    OpenCvWebcam camera;

    VisionPipeline pipeline;

    VisionPipeline.vPos loc = VisionPipeline.vPos.LEFT;

    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, startPoseSupport);
        pipeline = new VisionPipeline("RED");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam 1");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        //camera.getFocusControl().setMode(OpenCvCamera.FocusControl.Mode.AUTO);

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
    public void start() {
        Actions.runBlocking(
                drive.actionBuilder(startPoseSupport)
                        //TODO: Add actions here

                        .lineToY(-36)
                        .strafeToConstantHeading(tapes[loc.ordinal()])
                        //TODO: Add outtake here
                        .strafeToConstantHeading(new Vector2d(-36, -36))
                        .strafeToConstantHeading(new Vector2d(-35, -4))
                        .strafeToLinearHeading(new Vector2d(48, -13),Math.PI)
                        .waitSeconds(2.6)
                        .strafeTo(boards[loc.ordinal()])
                        //TODO: Add slides and bucket code here
                        .strafeTo(new Vector2d(48, -12))
                        .strafeTo(new Vector2d(72, -10))
                        .build());

    }

    @Override
    public void loop() {
//        loc = pipeline.getPos();
//        telemetry.addData("Camera", "Position: " + loc);
//        telemetry.update();
    }
}
