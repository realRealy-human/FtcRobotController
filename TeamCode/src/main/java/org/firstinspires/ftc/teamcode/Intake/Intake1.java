package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import com.qualcomm.robotcore.hardware.Servo;
import org.firstinspires.ftc.teamcode.Distance;
import org.firstinspires.ftc.teamcode.ServoManage;
// importing functions from other files

public class Intake1 {

    private Distance distanceSensor;
    private DcMotor motor;
    private Telemetry telemetry;
    private double speed;
    private ServoManage servoManage;
    // defining the main things for the system

    public Intake1(HardwareMap hardwareMap, Telemetry telemetry) {
        servoManage = new ServoManage(hardwareMap, telemetry);

        // save the telemetry
        this.telemetry = telemetry;
    }

    public void setPower(double power) {
        servoManage.setPower(power);
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isGamePiece() {
        return distanceSensor.senseGamePiece();
    }

    public double getDistanceSensor(){
        return distanceSensor.whatDistance(DistanceUnit.CM);
    }



    public void takeOut(){
        if (distanceSensor.senseGamePiece()){
            motor.setPower(0.5);
        }
        else {
            motor.setPower(0);
        }
    }
    public void takeOut2(){
        if (distanceSensor.senseGamePiece()){
            motor.setPower(-0.5);
        }
        else {
            motor.setPower(0);
        }
    }
    public void takeOutGamePiece() {
        motor.setPower(-0.5);
        while (distanceSensor.senseGamePiece()) {}
        //when the red colorSensor doesn't sense red it will go backwards
        motor.setPower(0);
        // after that it will stop moving
    }
}


