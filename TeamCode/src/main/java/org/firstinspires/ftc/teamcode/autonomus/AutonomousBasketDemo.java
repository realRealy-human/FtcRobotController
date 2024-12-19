package org.firstinspires.ftc.teamcode.autonomus;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Claw;

import org.firstinspires.ftc.teamcode.Arm;

import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Intake.Intake1;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import org.firstinspires.ftc.teamcode.PID;

@Autonomous(name = "AutonomousBasketDemo", group = "Examples")
public class AutonomousBasketDemo extends LinearOpMode {
    private DcMotor leftFront;
    private DcMotor rightFront;
    private DcMotor leftRear;
    private DcMotor rightRear;
    private IMU imu;
    private Telemetry telemetry;
    private PID pID;
    private Claw claw;
    private ElevatorPControl elevator;
    private Intake1 intake;
    private Arm arm;
    private ColorSensor colorSensor;
    private Basket Busket;
    private HardwareMap hardwareMap;

    @Override
    public void runOpMode() {
        leftFront = hardwareMap.get(DcMotor.class, "leftMotor");
        rightFront = hardwareMap.get(DcMotor.class, "rightMotor");
        leftRear = hardwareMap.get(DcMotor.class, "leftBack");
        rightRear = hardwareMap.get(DcMotor.class, "rightBack");
        imu = hardwareMap.get(IMU.class, "imu");
        claw = hardwareMap.get(Claw.class, "claw");
        elevator = hardwareMap.get(ElevatorPControl.class, "elevator");
        intake = hardwareMap.get(Intake1.class, "intake");
        arm = hardwareMap.get(Arm.class, "arm");
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        Busket = hardwareMap.get(Basket.class, " Busket");

        waitForStart();

        startMotors();
        sleep(2000);

        elevator.goTo(1000);
        startMotors();
        sleep(200);

        elevator.goTo(700);

        claw.openOrCloseClaw(true, false);

        goBackwards();

        sleep(1000);

        elevator.goTo(0);
        claw.openOrCloseClaw(false, true);

        goRight();

        sleep(500);

        goBackwards();
        sleep(1500);

        intake.waitForGamePiece();
        goRight();
        sleep(400);

        startMotors();
        sleep(700);

        arm.moveArmP(0);
        intake.move(1);
        sleep(500);

        elevator.goTo(1000);
        Busket.openBasket();
        Busket.closeBasket();
        elevator.goTo(0);
        arm.moveArmP(100);

        goLeft();
        sleep(100);

        goBackwards();
        sleep(1500);

        intake.waitForGamePiece();
        startMotors();
        sleep(1000);
            //erase later

        arm.moveArmP(0);
        intake.move(1);
        sleep(500);

        elevator.goTo(1000);
        Busket.openBasket();
        Busket.closeBasket();
        elevator.goTo(0);
        arm.moveArmP(100);
        goLeft();
        sleep(100);

        goBackwards();
        sleep(700);

        intake.waitForGamePiece();
        startMotors();
        sleep(700);

        arm.moveArmP(0);
        intake.move(1);
        sleep(500);

        elevator.goTo(1000);
        Busket.openBasket();
        Busket.closeBasket();
        elevator.goTo(0);
        arm.moveArmP(100);
    }

    public void startMotors() {
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
    }

    public void stopMotors() {
        //stops the motors
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }

    public void goBackwards() {
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);

    }

    public void goLeft() {
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);
    }

    public void goRight() {
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(1);
        rightRear.setPower(1);
    }
}
