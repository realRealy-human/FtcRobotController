package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;

// define the class
public class intake {
    // define the servo object and the telemetry variables
    private CRServo servoOne;
    private CRServo servoTwo;

    private TouchSensor touchIntake;
    // define the constructor
    public intake(HardwareMap hardwareMap, Telemetry telemetry) {
        servoOne = hardwareMap.get(CRServo.class, "intakeServo1");
        servoTwo = hardwareMap.get(CRServo.class, "intakeServo2");
    }
//
    public void setPower(double power) {
        servoOne.setPower(power);
        servoTwo.setPower(-power);
    }
    // setting the servo position and printing it onto the telemetry
    public void collectGamePiece(double power){
        if (!touchIntake.isPressed()){
            servoOne.setPower(power);
            servoTwo.setPower(-power);
        }
    }

    public void takeOutTheGamePiece(double power){
        if (touchIntake.isPressed()){
            servoOne.setPower(-power);
            servoTwo.setPower(power);
        }
    }


}
