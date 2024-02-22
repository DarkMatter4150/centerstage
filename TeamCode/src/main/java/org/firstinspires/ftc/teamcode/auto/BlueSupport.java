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

@Config
@Autonomous(group = "Autonomous", preselectTeleOp = "Tele")
public class BlueSupport extends OpMode {
    Pose2d startPoseSupport = new Pose2d(-40, 62, Math.toRadians(270));

    MecanumDrive drive;

    OpenCvCamera camera;

    VisionPipeline pipeline;

    VisionPipeline.vPos loc = VisionPipeline.vPos.CENTER;

    @Override
    public void init() {
        drive = new MecanumDrive(hardwareMap, startPoseSupport);
        pipeline = new VisionPipeline("BLUE");

        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "NAME_OF_CAMERA_IN_CONFIG_FILE");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                camera.startStreaming(1280, 720);
                camera.setPipeline(pipeline);

                telemetry.addData("Camera", "Initialized");
                telemetry.update();
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });

        telemetry.addData("Camera", "Position: " + loc);
        telemetry.update();
    }

    @Override
    public void start() {
        camera.closeCameraDeviceAsync(() -> {
            loc = pipeline.getPos();
            telemetry.addData("Camera", "Saved Position: " + loc);
            telemetry.update();
        });

        Actions.runBlocking(
                drive.actionBuilder(startPoseSupport)
                    //TODO: Add actions here
                    .build());
    }

    @Override
    public void loop() {}
}
