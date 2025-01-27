package org.firstinspires.ftc.teamcode.Drive;




import android.graphics.Color;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;

import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;




    @Autonomous(name = "DriveTest", group = "Examples")
    public class DriveTest extends LinearOpMode {
        private SampleMecanumDrive drive;
        private Pose2d startPose = new Pose2d(0, -72, 0); // 1
        private Arm arm;
        private ElevatorPControl elevatorPControl;
        private Claw Claw;
        private Intake1 Intake;
        private Basket Basket;
        private android.graphics.Color Color;
        private HardwareMap hardwareMap;

        private TouchSensor touchTop;
        private TouchSensor touchBottom;

        private DcMotor leftFront;
        private DcMotor rightFront;
        private DcMotor leftRear;
        private DcMotor rightRear;
        private IMU imu;
        private Telemetry telemetry;

        private void startMotors() {
            leftFront.setPower(1);
            leftRear.setPower(1);
            rightFront.setPower(1);
            rightRear.setPower(1);
        }


        @Override
        public void runOpMode() {

            elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
            leftFront = hardwareMap.get(DcMotor.class, "leftMotor");
            rightFront = hardwareMap.get(DcMotor.class, "rightMotor");
            leftRear = hardwareMap.get(DcMotor.class, "leftBack");
            rightRear = hardwareMap.get(DcMotor.class, "rightBack");
            Claw = new Claw(hardwareMap, telemetry);
            Intake = new Intake1(hardwareMap, telemetry);


            waitForStart();

            while (opModeIsActive()) {
                elevatorPControl.updateBySetPoint();


                while (opModeIsActive()) {
                    elevatorPControl.updateBySetPoint();
                    leftFront.setPower(1);
                    leftRear.setPower(-1);
                    rightFront.setPower(1);
                    rightRear.setPower(-1);

                    sleep(500);

                    leftFront.setPower(0);
                    leftRear.setPower(0);
                    rightFront.setPower(0);
                    rightRear.setPower(0);

                    sleep(100);

                    elevatorPControl.setSetPoint(50);




                    sleep(1000);

                    elevatorPControl.setSetPoint(10);

                    sleep(1000);

                    Claw.open();

                    sleep(500);

                    elevatorPControl.setSetPoint(0);


                }
            }

        }
    }


