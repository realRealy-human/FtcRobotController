package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Color;
// importing functions from other files

public class Intake1 {
    private Color colorSensor;
    private DcMotor motor;
    private Telemetry telemetry;
    // defining the main things for the system

    public Intake1(HardwareMap hardwareMap, Telemetry telemetry) {
        colorSensor = new Color(hardwareMap, telemetry);
        motor = hardwareMap.get(DcMotor.class, "leftMotor");
        // fully defining and adding things

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // telling the motor when to stop

        this.telemetry = telemetry;
        // updating telemetry
    }

    public void waitForGamePiece() {
        while (colorSensor.colorSenseRed() < 400 || colorSensor.colorSenseBlue() < 400 || colorSensor.colorSenseGreen() < 150 && colorSensor.colorSenseRed() < 150 ) {
            motor.setPower(-0.5);
        }
        //when the red colorSensor doesn't sense red it will go backwards
        motor.setPower(0);
        // after that it will stop moving
    }

    public void move(double speed) {
        motor.setPower(speed);
        // the intake will move at the speed we tell it to
    }
}