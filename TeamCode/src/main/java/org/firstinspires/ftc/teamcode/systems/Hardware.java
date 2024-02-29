package org.firstinspires.ftc.teamcode.systems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

public class Hardware {
    private OpMode opMode = null;

    //drivebase
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

    //bucket
    private Servo armLeft = null;
    private Servo armRight = null;
    private Servo bucketRot = null;

    //plane
    private Servo plane = null;

    //pullup
    private DcMotor pullup = null;

    public Hardware() {}
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
        liftLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftLeft.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        liftRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        liftRight.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        intake = opMode.hardwareMap.get(DcMotor.class, "intake");
        intakeServoLeft = opMode.hardwareMap.get(Servo.class, "intakeServoLeft");
        intakeServoRight = opMode.hardwareMap.get(Servo.class, "intakeServoRight");

        armLeft = opMode.hardwareMap.get(Servo.class, "armLeft");
        armRight = opMode.hardwareMap.get(Servo.class, "armRight");
        bucketRot = opMode.hardwareMap.get(Servo.class, "bucketRot");

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

    public int getLiftPosition() {
        return liftLeft.getCurrentPosition();
    }

    public void setIntakePower(double power) {
        intake.setPower(power);
    }

    public void setIntakeServoPositionLeft(double position) {
        intakeServoLeft.setPosition(position);
    }

    public void setIntakeServoPositionRight(double position) {
        intakeServoRight.setPosition(position);
    }

    public void setArmServoPositionLeft(double position) {
        armLeft.setPosition(position);
    }

    public void setArmServoPositionRight(double position) {
        armRight.setPosition(position);
    }

    public void setBucketRotPosition(double position) {
        bucketRot.setPosition(position);
    }

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