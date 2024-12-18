package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.Servo;

// define the class
public class ServoManage {
    // define the servo object and the telemetry variables
    private Servo servo;
    private Telemetry telemetry;

    // define the constructor
    public ServoManage(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        servo = hardwareMap.get(Servo.class, name);

        // save the telemetry
        this.telemetry = telemetry;
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
