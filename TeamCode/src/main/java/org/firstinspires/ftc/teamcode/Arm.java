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
    public Arm(HardwareMap hardwareMap, Telemetry telemetry, String name) {
        arm = hardwareMap.get(DcMotor.class, "leftBack");
        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;
    }
    public void goToGamePad(Gamepad gamepad) {
        arm.setPower(gamepad.right_stick_y);
    }
    public double moveArmP(double target) {
        double kp =  0.5; // Proportional gain
        double error = target - arm.getCurrentPosition()  /28f * 12f / 120f * 12.5f; //TODO numbers aren't right probably
        double output = kp * error;

        arm.setPower(output);
        telemetry.addData("Arm position" ,arm.getCurrentPosition()  /28f * 12f / 120f * 12.5f);
        telemetry.update();

        return arm.getCurrentPosition();
    }
}