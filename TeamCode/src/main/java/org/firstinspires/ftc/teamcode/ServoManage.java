package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.Servo;
// importing functions

public class ServoManage {
    private Servo servo;
    private Telemetry telemetry;
    public ServoManage(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        servo = hardwareMap.get(Servo.class, name);
        // defining things so the system will work

        this.telemetry = telemetry;
        // updating telemetry
    }
    public void servoPosition1() {
        servo.setPosition(0);
    }
    public void servoPosition2() {
        servo.setPosition(1);
    }

    public void servoPositionX(double pos) {
        servo.setPosition(pos);
    }
    public double getPos() {
        return servo.getPosition();
    }
    // setting the servo position and printing it onto the telemetry
}
