package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;

// define the class
public class intake {
    // define the servo object and the telemetry variables
    private CRServo servoOne;
    private CRServo servoTwo;
    // define the constructor
    public intake(HardwareMap hardwareMap, Telemetry telemetry) {
        servoOne = hardwareMap.get(CRServo.class, "intake");
        servoTwo = hardwareMap.get(CRServo.class, "intake1");
    }
//
    public void setPower(double power) {
        servoOne.setPower(power);
        servoTwo.setPower(-power);
    }
    // setting the servo position and printing it onto the telemetry
}
