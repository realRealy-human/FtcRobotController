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
    private PID pID;

    // define the constructor
    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        arm = hardwareMap.get(DcMotor.class, "arm");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pID = new PID(telemetry);

        // save the telemetry
        this.telemetry = telemetry;
    }
    public void goToGamePad(Gamepad gamepad) {
        arm.setPower(gamepad.right_stick_y);
    }
    // making the arm move when the right stick is moved
    public void moveArmP(double target) {
        pID.p(arm, target, 0.5);
    }
    // the P in PID of the arm
}