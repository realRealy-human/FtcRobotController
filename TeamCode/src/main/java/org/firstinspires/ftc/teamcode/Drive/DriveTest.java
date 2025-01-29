package org.firstinspires.ftc.teamcode.Drive;




import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;

import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;


@Autonomous(name = "DriveTest", group = "Examples")
    public class DriveTest extends LinearOpMode {
        private Pose2d startPose = new Pose2d(0, -72, 0); // 1
        private Arm arm;
        private ElevatorPControl elevatorPControl;
        private Claw Claw;
        private Intake1 Intake;
        private Basket Basket;
        private android.graphics.Color Color;

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


            leftFront = hardwareMap.get(DcMotor.class, "leftMotor");
            rightFront = hardwareMap.get(DcMotor.class, "rightMotor");
            leftRear = hardwareMap.get(DcMotor.class, "leftBack");
            rightRear = hardwareMap.get(DcMotor.class, "rightBack");
            Claw = new Claw(hardwareMap, telemetry);
            Intake = new Intake1(hardwareMap, telemetry);
            //elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
            imu = hardwareMap.get(IMU.class, "imu");
            //arm = hardwareMap.get(Arm.class, "arm");


            waitForStart();
            //while (opModeIsActive()) {
                //elevatorPControl.updateBySetPoint();


                while (opModeIsActive()) {
                    //elevatorPControl.updateBySetPoint();
                    leftFront.setPower(0.7);
                    leftRear.setPower(-0.7);
                    rightFront.setPower(0.7);
                    rightRear.setPower(-0.7);

                    sleep(900);

                    leftFront.setPower(0);
                    leftRear.setPower(0);
                    rightFront.setPower(0);
                    rightRear.setPower(0);

                    sleep(29300);



                    //

//                    elevatorPControl.setSetPoint(50);
//
//                    sleep(100);
//
//                    leftFront.setPower(-0.4);
//                    leftRear.setPower(-0.4);
//                    rightFront.setPower(0.4);
//                    rightRear.setPower(0.4);
//
//                    sleep(3000);
//
//                    leftFront.setPower(0);
//                    leftRear.setPower(0);
//                    rightFront.setPower(0);
//                    rightRear.setPower(0);
//
//                    sleep(250);
//
//                    elevatorPControl.setSetPoint(10);
//
//                    sleep(500);
//
//                    Claw.open();
//
//                    sleep(500);
//
//                    elevatorPControl.setSetPoint(0);
//
//                    leftFront.setPower(0.7);
//                    leftRear.setPower(0.7);
//                    rightFront.setPower(-0.7);
//                    rightRear.setPower(-0.7);
//
//                    sleep(500);
//
//                    leftFront.setPower(0);
//                    leftRear.setPower(0);
//                    rightFront.setPower(0);
//                    rightRear.setPower(0);
//
//                    sleep(200);
//
//                    leftFront.setPower(-0.3);
//                    leftRear.setPower(-0.3);
//                    rightFront.setPower(-0.3);
//                    rightRear.setPower(-0.3);
//
//                    sleep(300);
//
//                    leftFront.setPower(0);
//                    leftRear.setPower(0);
//                    rightFront.setPower(0);
//                    rightRear.setPower(0);
//
//                    sleep(100);
//
//                    leftFront.setPower(-0.5);
//                    leftRear.setPower(-0.5);
//                    rightFront.setPower(0.5);
//                    rightRear.setPower(0.5);
//
//                    sleep(300);
//
//                    leftFront.setPower(0);
//                    leftRear.setPower(0);
//                    rightFront.setPower(0);
//                    rightRear.setPower(0);
//
//                    sleep(200);





                }
            }

        }



