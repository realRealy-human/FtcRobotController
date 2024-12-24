//package org.firstinspires.ftc.teamcode.Intake;
//
//import static java.lang.Thread.sleep;
//
//import com.qualcomm.robotcore.hardware.ColorSensor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Arm;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//import org.firstinspires.ftc.teamcode.Basket;
//import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
//import org.firstinspires.ftc.teamcode.ServoManage;
//
//public class AllTheMovesBaifus
//{
//    private Intake1 intake;
//    private Arm arm;
//    private ColorSensor colorSensor ;
//    private Telemetry telemetry;
//    private ElevatorPControl elevator;
//    private ServoManage Busket;
//    private HardwareMap hardwareMap;
//    public Basket bsk = new Basket(hardwareMap, telemetry);
//    public void MovesBaifus() throws InterruptedException {
//        intake = new Intake1(hardwareMap, telemetry);
//        arm = new Arm(hardwareMap, telemetry, "servo1");
//        arm.moveArmP(100);
//        intake.waitForGamePiece();
//        arm.moveArmP(0);
//        intake.move(1);
//        sleep(1000);
//        intake.move(0);
//
//
//
//        elevator.goTo(1000);
//        bsk.openBasket();
//        bsk.closeBasket();
//        elevator.goTo(0);
//
//
//
//
//
//
//
//
//
//
//
//    }
//}
