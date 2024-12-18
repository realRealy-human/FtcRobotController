package org.firstinspires.ftc.teamcode.Drive;

// import stuff
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

// define class
public class Drive {
    // define motors, imu and telemetry
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private IMU imu;
    private Telemetry telemetry;

    // define constructor
    public Drive(HardwareMap hardwareMap, Telemetry telemetry) {
        // map motors to hardware
        leftFront = hardwareMap.get(DcMotor.class, "leftMotor");
        rightFront = hardwareMap.get(DcMotor.class, "rightMotor");
        leftRear = hardwareMap.get(DcMotor.class, "leftBack");
        rightRear = hardwareMap.get(DcMotor.class, "rightBack");

        // map imu to harware
        imu = hardwareMap.get(IMU.class, "imu");

        // define motors direction
        leftFront.setDirection(DcMotorSimple.Direction.FORWARD);
        rightFront.setDirection(DcMotorSimple.Direction.REVERSE);
        leftRear.setDirection(DcMotorSimple.Direction.FORWARD);
        rightRear.setDirection(DcMotorSimple.Direction.REVERSE);

        // define imu
        IMU.Parameters parameters = new IMU.Parameters(new RevHubOrientationOnRobot(
                RevHubOrientationOnRobot.LogoFacingDirection.DOWN,
                RevHubOrientationOnRobot.UsbFacingDirection.FORWARD));
        imu.initialize(parameters);

        // save telemetry
        this.telemetry = telemetry;
    }

    // define drive variables
    double y;
    double x;
    double rx;
    double denominator;
    double botHeading;
    double rotX;
    double rotY;
    double frontLeftPower;
    double backLeftPower;
    double frontRightPower;
    double backRightPower;

    private void checkGamepad(Gamepad gamepad) {
        y = -gamepad.left_stick_y;
        x = gamepad.left_stick_x;
        rx = -gamepad.right_stick_x;
    }

    public void driveUsingGamepad(Gamepad gamepad) {
        checkGamepad(gamepad);

        // calculate denominator
        denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);

        // power motors
        leftFront.setPower(y + x + rx / denominator);
        leftRear.setPower(y - x + rx / denominator);
        rightFront.setPower(y - x - rx / denominator);
        rightRear.setPower(y + x - rx / denominator);
    }

    public void fieldCentricUsingGamePad(Gamepad gamepad) {
        checkGamepad(gamepad);

        // reset position when option button is clicked
        // This button choice was made so that it is hard to hit on accident,
        // it can be freely changed based on preference.
        // The equivalent button is start on Xbox-style controllers.
        if (gamepad.options) {
            imu.resetYaw();
        }

        // calculate the bot's heading
        botHeading = imu.getRobotYawPitchRollAngles().getYaw(AngleUnit.RADIANS);

        // report the bot's heading to the imu
        telemetry.addData("imu", botHeading);
        telemetry.update();

        // Rotate the movement direction counter to the bot's rotation
        rotX = x * Math.cos(-botHeading) - y * Math.sin(-botHeading);
        rotY = x * Math.sin(-botHeading) + y * Math.cos(-botHeading);

        rotX = rotX * 1.1;  // Counteract imperfect strafing

        // calculate the denominator
        // Denominator is the largest motor power (absolute value) or 1
        // This ensures all the powers maintain the same ratio,
        // but only if at least one is out of the range [-1, 1]
        denominator = Math.max(Math.abs(rotY) + Math.abs(rotX) + Math.abs(rx), 1);

        // calculate motor power
        frontLeftPower = (rotY + rotX + rx) / denominator;
        backLeftPower = (rotY - rotX + rx) / denominator;
        frontRightPower = (rotY - rotX - rx) / denominator;
        backRightPower = (rotY + rotX - rx) / denominator;


    }
    public void startMotors() {
        leftFront.setPower(frontLeftPower);
        leftRear.setPower(backLeftPower);
        rightFront.setPower(frontRightPower);
        rightRear.setPower(backRightPower);
    }

    public void stopMotors() {
        //stops the motors
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }
}






