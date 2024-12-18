package org.firstinspires.ftc.teamcode.Elevator;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Basket;


import org.firstinspires.ftc.teamcode.PID;import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Claw;
import org.firstinspires.ftc.teamcode.ServoManage;


public class ElevatorAndBasketDaganTest {
    private ElevatorPControl Elevator;
    private ServoManage Busket;
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
    public Basket bsk = new Basket(hardwareMap, telemetry, "bsk");


    public void BasketAndElevator(HardwareMap hardwareMap, Telemetry telemetry){
        Elevator = new ElevatorPControl(hardwareMap, telemetry);
        Elevator.goTo(100);
        bsk.openBasket();
        bsk.closeBasket();
        Elevator.goTo(0);
        //teat complete 100%





    }
}
