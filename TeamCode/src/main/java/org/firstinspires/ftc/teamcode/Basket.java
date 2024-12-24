package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class Basket {
    // define the servoManage object, the telemetry and the open and close position variables
    private ServoManage servoManage;
    private Telemetry telemetry;
    private double openPos = 0.35;
    private double closePos = 0.6;

    // define the constructor
    public Basket(HardwareMap hardwareMap, Telemetry telemetry) {
        servoManage = new ServoManage(hardwareMap, telemetry, "basket");

        // save the telemetry
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
