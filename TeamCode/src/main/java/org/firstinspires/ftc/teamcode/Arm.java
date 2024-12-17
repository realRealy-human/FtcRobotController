package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    private DcMotor arm;
    private Telemetry telemetry;
    private PID pID;

    // defining everything

    public Arm(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        arm = hardwareMap.get(DcMotor.class, "leftBack");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pID = new PID(telemetry);
        // defining things so the function works

        this.telemetry = telemetry;
        // updating telemetry
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