package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Color;
import org.firstinspires.ftc.teamcode.Distance;
// importing functions from other files

public class Intake1 {
    private Color colorSensor;
    private Distance distanceSensor;
    private DcMotor motor;
    private Telemetry telemetry;
    private double speed;
    // defining the main things for the system

    public Intake1(HardwareMap hardwareMap, Telemetry telemetry) {
        colorSensor = new Color(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        motor = hardwareMap.get(DcMotor.class, "intake");
        // fully defining and adding things

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        // telling the motor when to stop

        this.telemetry = telemetry;
        // updating telemetry
    }

    public void waitForGamePieceWIthColor() {
        motor.setPower(0.5);
        while (!colorSensor.searchForObject()) {
            telemetry.addData("red", colorSensor.colorSenseRed());
            telemetry.addData("green", colorSensor.colorSenseGreen());
            telemetry.addData("blue", colorSensor.colorSenseBlue());
            telemetry.addData("alpha", colorSensor.senseObject());
            telemetry.update();
        }
        //when the red colorSensor doesn't sense red it will go backwards
        motor.setPower(0);
        // after that it will stop moving
    }

    public void waitForGamePieceWIthDistance(boolean toWait) {
        // if you are told to wait, wait
        if (toWait) {
            motor.setPower(0.5);
        }

        // report the distance
//        telemetry.addData("distance", distanceSensor.whatDistance(DistanceUnit.CM));
//        telemetry.update();

        //when the distanceSensor sense the game piece it will stop
        if (distanceSensor.senseGamePiece()) {
            motor.setPower(0);
        }
    }
    public void takeOutGamePiece() {
        motor.setPower(0.5);
        while (distanceSensor.senseGamePiece()) { //TODO make use opModeIsActive
//            telemetry.addData("distance", distanceSensor.whatDistance(DistanceUnit.CM));
//            telemetry.update();
        }
        //when the red colorSensor doesn't sense red it will go backwards
        motor.setPower(0);
        // after that it will stop moving
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setPower(double power) {
        motor.setPower(power);
    }

    public double getSpeed() {
        return speed;
    }

    public void moveWithSpeed() {
        motor.setPower(speed);
        // the intake will move at the speed we tell it to
    }

    public boolean isGamePiece(){
        return distanceSensor.senseGamePiece();
    }

    public double getDistanceSensor(){
        return distanceSensor.whatDistance(DistanceUnit.CM);
    }
}