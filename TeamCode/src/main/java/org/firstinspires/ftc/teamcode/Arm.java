package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Arm {
    private DcMotor arm;
    private Telemetry telemetry;
    public Arm(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        arm = hardwareMap.get(DcMotor.class, "leftBack");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;
    }
    public void goToGamepad(Gamepad gamepad) {
        arm.setPower(gamepad.right_stick_y);
    }
}