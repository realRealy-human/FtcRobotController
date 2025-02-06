package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;



public class Arm {

    private Servo master;
    private Servo slave;
    private double setPoint;
    public Arm(HardwareMap hardwareMap, Telemetry telemetry){
        master = hardwareMap.get(Servo.class, "masterServo");
        slave = hardwareMap.get(Servo.class, "slaveServo");
    }
    public void armSetSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    public double getSetPoint() {
    return setPoint;

    }

    public void armUpdateBySetPoint(){
        master.setPosition(setPoint);
        slave.setPosition(1 - setPoint);
    }

}





