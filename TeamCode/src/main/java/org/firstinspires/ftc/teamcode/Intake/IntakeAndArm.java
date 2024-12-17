package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Arm;


public class IntakeAndArm {
    private Intake1 intake;
    private Arm arm;
    private ColorSensor colorSensor ;
    private Telemetry telemetry;

    public IntakeAndArm(HardwareMap hardwareMap, Telemetry telemetry) {
        intake = new Intake1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry, "servo1");

        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");

        this.telemetry = telemetry;
    }

    public void searchAndCollectGamePiece(boolean redGroup) {
//        while(!(((redGroup ? colorSensor.blue() : colorSensor.red()) > 200) || colorSensor.green() > 200)) {}
        arm.moveArmP(95);

        intake.waitForGamePiece();
        intake.move(0.5);

        arm.moveArmP(0);
        intake.move(0);
    }
}

