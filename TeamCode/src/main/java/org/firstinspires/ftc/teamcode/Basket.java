package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Basket {
    private ServoManage servoManage;
    private Telemetry telemetry;
    private double openPos = 0.4;
    private double closePos = 0.6;

    public Basket(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        servoManage = new ServoManage(hardwareMap, telemetry, name);

        this.telemetry = telemetry;
    }

    public void closeBasket() {
        servoManage.servoPositionX(closePos);
    }

    public void openBasket() {
        servoManage.servoPositionX(openPos);
    }

    // starting positions and naming things
    public void openBasketLimited(int length) {
        servoManage.servoPositionX(0.4);

        // sleep
        try {
            Thread.sleep(length);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        servoManage.servoPositionX(0.6);
    }
}
