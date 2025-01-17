package org.firstinspires.ftc.teamcode.Intake;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.ServoManage;

// define the class
public class Claw {
    // define the servoManage object, telemetry and open and close position of the claw
    private ServoManage servo;
    private Telemetry telemetry;
    private double openPos = 0.232;
    private double closePos = 0.055;

    // define the constructor
    public Claw(HardwareMap hardwareMap, Telemetry telemetry) {
        // create the servoManage object
        servo = new ServoManage(hardwareMap, telemetry, "claw");

        // save the telemetry
        this.telemetry = telemetry;
    }

    public void toggleClaw(boolean toggle) {
        // if you toggle...
        if (toggle) {
            // and the claw position is greater than 0.15...
            if (servo.getPos() > 0.15) {
                // go to the closed position
                servo.servoPositionX(closePos);
            }
            // and the claw position is lower than 0.15...
            else {
                // go to the opened position
                servo.servoPositionX(openPos);
            }
        }

        // tell the telemetry where is the claw
        telemetry.addData("servo pos", servo.getPos());
        telemetry.update();
    }

    public void openOrCloseClaw(boolean toOpen, boolean toClose) {
        // if you are told to open...
        if (toOpen) {
            // open the claw
            servo.servoPositionX(openPos);
        }
        // if you are told to close...
        else if (toClose) {
            // close the claw
            servo.servoPositionX(closePos);
        }

        // tell the telemetry where is the claw
        telemetry.addData("servo pos", servo.getPos());
        telemetry.update();
    }
    public void open(){
        servo.servoPositionX(openPos);
    }
    public void close(){
        servo.servoPositionX(closePos);
    }
}