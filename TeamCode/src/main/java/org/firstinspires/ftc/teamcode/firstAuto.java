package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.configuration.typecontainers.MotorConfigurationType;

import org.firstinspires.ftc.teamcode.Intake.Claw;

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private DcMotor arm;
    @Override
    public void runOpMode() {
        arm = hardwareMap.get(DcMotor.class, "leftBack");

        arm.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        waitForStart();

        while (opModeIsActive()) {
            arm.setPower(gamepad1.right_stick_y);
        }
    }
}