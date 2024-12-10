package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private Basket basket;

    @Override
    public void runOpMode() {
        basket = new Basket(hardwareMap, telemetry, "servo1");

        basket.closeBasket();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                basket.openBasketLimited(1000);
            }
        }
    }
}