package org.firstinspires.ftc.teamcode.Elevator;
import static java.lang.Thread.sleep;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;


import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.ServoManage;


public class AllTheMovesDAGAN {
    private ElevatorPControl elevator;
    private Intake1 intake;
    private Arm arm;
    private ColorSensor colorSensor ;
    private Telemetry telemetry;
    private ServoManage Busket;
    private HardwareMap hardwareMap;
    private DcMotor Motor;

    public Basket bsk = new Basket(hardwareMap, telemetry, "bsk");
    public void allTheMovesDagan() throws InterruptedException {
        intake = new Intake1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry, "servo1");
        arm.moveArmP(80);
        intake.waitForGamePiece();
        arm.moveArmP(0);
        intake.move(-1);
        sleep(1000);
        intake.move(0);



        elevator.goTo(100);
        bsk.openBasket();
        bsk.closeBasket();
        elevator.goTo(0);











    }

}
