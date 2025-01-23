package org.firstinspires.ftc.teamcode;

// import stuff
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.hardware.TouchSensor;
import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class Arm {
    // define the motor, the telemetry and the PID object variables
    private DcMotor arm;
    private Telemetry telemetry;
    private PID pidController;
    private double endPose = -269;
    private double setPoint;

    private TouchSensor touchTop;
    private TouchSensor touchBottom;

    // define the constructor
    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        arm.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        touchTop = hardwareMap.get(TouchSensor.class, "arm_top");
        touchBottom = hardwareMap.get(TouchSensor.class, "arm_bottom");

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

    public void setPower(double power) {
        arm.setPower(power * (60 / 36));
    }

    public void updateBySetPoint() {
        //arm.setPower(pidController.calculateP(getSetPoint() , arm.getCurrentPosition()));

        if (setPoint == -250) {
            if (getPosition() > -60) {
                arm.setPower(-0.4);
                telemetry.addData("arm con", 1);
            } else if (getPosition() > -200) {
                arm.setPower(0.15);
                telemetry.addData("arm con", 2);
            } else {
                arm.setPower(0);
                telemetry.addData("arm con", 3);
            }
        } else {
            if (getPosition() < -50) {
                arm.setPower(0.85);
                telemetry.addData("arm con", 4);
            } else if (getPosition() > -50 && getPosition() < -60) {
                arm.setPower(-0.03);
                telemetry.addData("arm con", 5);
            } else if (getPosition() > -50 && getPosition() < -20) {
                arm.setPower(-0.03);
                telemetry.addData("arm con", 6);
            } else {
                arm.setPower(0);
                telemetry.addData("arm con", 7);
            }
        }
    }
    public void armGoDown(){
        if (!touchBottom.isPressed()){
            arm.setPower(-0.5);
        }else {
            arm.setPower(0);
        }
    }

    public void armGoUp(){
        if (!touchTop.isPressed()){
            arm.setPower(0.5);
        }else {
            arm.setPower(0);
        }
    }

    public boolean atPoint() {
        return Math.abs(getSetPoint() - getPosition()) <= 36;
    }
}