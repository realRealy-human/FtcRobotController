package org.firstinspires.ftc.teamcode;

// import stuff
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class Arm {
    // define the motor, the telemetry and the PID object variables
    private DcMotor arm;
    private Telemetry telemetry;
    private PID pidController;
    private double endPose = -269;
    private double setPoint;

    // define the constructor
    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pidController = new PID(0.001);

        // save the telemetry
        this.telemetry = telemetry;
    }
    public void setPowerByGamePad(Gamepad gamepad) {
        arm.setPower(gamepad.right_stick_y);
    }

    public double getPosition() {
        return arm.getCurrentPosition();
    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    public double getSetPoint() {
        return setPoint;
    }

    public void updateBySetPoint() {
        //arm.setPower(pidController.calculateP(getSetPoint() , arm.getCurrentPosition()));

        if (setPoint == -230) {
             if (getPosition() > -60) {
                 arm.setPower(-0.2);
                 telemetry.addData("arm con", 1);
             } else if (getPosition() > -200) {
                 arm.setPower(0.1);
                 telemetry.addData("arm con", 2);
             } else {
                 arm.setPower(0);
                 telemetry.addData("arm con", 3);
             }
        } else {
            if (getPosition() > -60 && getPosition() < -10) {
                arm.setPower(0.5);
                telemetry.addData("arm con", 4);
            } else if (getPosition() < -60) {
                arm.setPower(1);
                telemetry.addData("arm con", 5);
            } else {
                arm.setPower(0);
                telemetry.addData("arm con", 6);
            }
        }
    }

    public boolean atPoint() {
        return Math.abs(getSetPoint() - getPosition()) <= 12;
    }
}