package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {

    private OpMode opMode = null;

    //wheels
    private DcMotor frontLeft = null;
    private DcMotor backLeft = null;
    private DcMotor frontRight = null;
    private DcMotor backRight = null;

    //lift
    private DcMotor liftLeft = null;
    private DcMotor liftRight = null;

    //intake
    private DcMotor intake = null;
    private Servo intakeServoLeft = null;
    private Servo intakeServoRight = null;

    //TODO: bucket

    //plane
    private Servo plane = null;

    //pullup
    private DcMotor pullup = null;


    public Hardware(OpMode opMode) {
        this.opMode = opMode;
    }

    public void init() {
        frontLeft = opMode.hardwareMap.get(DcMotor.class, "frontLeft");
        backLeft = opMode.hardwareMap.get(DcMotor.class, "backLeft");
        frontRight = opMode.hardwareMap.get(DcMotor.class, "frontRight");
        backRight = opMode.hardwareMap.get(DcMotor.class, "backRight");

        liftLeft = opMode.hardwareMap.get(DcMotor.class, "liftLeft");
        liftRight = opMode.hardwareMap.get(DcMotor.class, "liftRight");

        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
        intakeServoLeft = opMode.hardwareMap.get(Servo.class, "intakeServoLeft");
        intakeServoRight = opMode.hardwareMap.get(Servo.class, "intakeServoRight");

        //TODO: bucket

        plane = opMode.hardwareMap.get(Servo.class, "plane");

        pullup = opMode.hardwareMap.get(DcMotor.class, "pullup");
        pullup.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pullup.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }

    public void setPower(double power) {
        frontLeft.setPower(power);
        backLeft.setPower(power);
        frontRight.setPower(power);
        backRight.setPower(power);
    }

    public void setPower(double frontLeftPower, double backLeftPower, double frontRightPower, double backRightPower) {
        frontLeft.setPower(frontLeftPower);
        backLeft.setPower(backLeftPower);
        frontRight.setPower(frontRightPower);
        backRight.setPower(backRightPower);
    }

    public void setLiftPower(double power) {
        liftLeft.setPower(power);
        liftRight.setPower(-power);
    }

    public void setIntakePower(double power) {
        intake.setPower(power);
    }

    public void setIntakeServoPosition(double position) {
        intakeServoLeft.setPosition(position);
        intakeServoRight.setPosition(position);
    }

    //TODO: bucket

    public void setPlanePosition(double position) {
        plane.setPosition(position);
    }

    public void setPullupPower(double power) {
        pullup.setPower(power);
    }

    public int getPullupPosition() {
        return pullup.getCurrentPosition();
    }
}