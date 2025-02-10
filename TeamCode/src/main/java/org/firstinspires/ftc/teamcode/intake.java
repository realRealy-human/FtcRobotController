package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// define the class
public class intake {
    // define the servo object and the telemetry variables
    private CRServo master;
    private CRServo slave;
    private Distance distanceSensor;

    private TouchSensor touchIntake;

    // define the constructor
    public intake(HardwareMap hardwareMap, Telemetry telemetry) {
        master = hardwareMap.get(CRServo.class, "master1");
        slave = hardwareMap.get(CRServo.class, "slave1");
        distanceSensor = new Distance(hardwareMap, telemetry);
    }

    //
    public void collectgamepiece() {
        if (!distanceSensor.senseGamePiece()) {
            master.setPower(1);
            slave.setPower(-1);
        }

    }

    public Distance getDistanceSensor() {
        return distanceSensor;
    }
    public void spitOutGamePiece(){
        if (distanceSensor.senseGamePiece()) {
            master.setPower(-1);
            slave.setPower(1);
        }
    }

    public void setintake() {
        master.setPower(1);
        slave.setPower(-1);
    }

    public void intakespeed(double power) {
        master.setPower(power);
        slave.setPower(-power);
    }


    // setting the servo position and printing it onto the telemetry


}