package org.firstinspires.ftc.teamcode.autonomus;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Intake.Claw;

import org.firstinspires.ftc.teamcode.Arm;

import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.ServoManage;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Color;
import org.firstinspires.ftc.teamcode.Elevator.AllTheMovesDAGAN;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorAndBasketDaganTest;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorAndClaw;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorControl;
import org.firstinspires.ftc.teamcode.Intake.Claw;
import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.IntakeAndArm;
import org.firstinspires.ftc.teamcode.Drive.Drive;
import org.firstinspires.ftc.teamcode.firstAuto;
import org.firstinspires.ftc.teamcode.PID;
import org.firstinspires.ftc.teamcode.ServoManage;











public class AutonomusBasketDemo {
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
    private ElapsedTime timer;



    private void Define(){
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
        timer = new ElapsedTime();



    }
    public void startMotors() {
        Define();
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(1);
        rightRear.setPower(1);
    }

    public void stopMotors() {
        //stops the motors
        Define();
        leftFront.setPower(0);
        leftRear.setPower(0);
        rightFront.setPower(0);
        rightRear.setPower(0);
    }
    public void goBackwards(){
        Define();
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);

    }
    public void goLeft(){
        Define();
        leftFront.setPower(1);
        leftRear.setPower(1);
        rightFront.setPower(-1);
        rightRear.setPower(-1);
    }
    public void goRight(){
        Define();
        leftFront.setPower(-1);
        leftRear.setPower(-1);
        rightFront.setPower(1);
        rightRear.setPower(1);
    }
    private void autonomusBasket (){
        Define();
      startMotors();
        timer.reset();
        while(timer.seconds() < 2) {

        }
        elevator.goTo(1000);
        startMotors();
        timer.reset();
        while(timer.seconds() < 0.5) {

        }
        elevator.goTo(700);

        claw.openOrCloseClaw(true, false);

        goBackwards();

        timer.reset();
        while(timer.seconds() < 1) {

        }

        elevator.goTo(0);
        claw.openOrCloseClaw(false, true);

        goRight();

        timer.reset();
        while(timer.seconds() < 0.5) {

        }
        goBackwards();
        timer.reset();
        while(timer.seconds() < 1.5) {

        }
        intake.waitForGamePiece();
        goRight();
        timer.reset();
        while(timer.seconds() < 1) {

        }
        startMotors();
        timer.reset();
        while(timer.seconds() < 0.7) {

        }
        intake.move(1);
        timer.reset();
        while(timer.seconds() < 0.5) {

        }
        elevator.goTo(100);
        Busket.openBasket();
        Busket.closeBasket();
        elevator.goTo(0);
        arm.moveArmP(0);

        elevator.goTo(100);
        Busket.openBasket();
        Busket.closeBasket();
        elevator.goTo(0);

        goLeft();
        timer.reset();
        while(timer.seconds() < 0.1) {

        }
        goBackwards();
        timer.reset();
        while(timer.seconds() < 1.5) {

        }
        intake.waitForGamePiece();
        startMotors();
        timer.reset();
        while(timer.seconds() < 1) {

        }
















    }
}
