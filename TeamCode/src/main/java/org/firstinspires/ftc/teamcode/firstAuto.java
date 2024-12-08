package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Claw;

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private double kp =  0.18; // Proportional gain
    private DcMotor motor;
    private  double target;

    @Override
    public void runOpMode() {
        motor = hardwareMap.get(DcMotor.class, "leftMotor");

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        double error = 0;
        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) { target = 0;}
            else if (gamepad1.b) {target = 40;}
            else if (gamepad1.x) {target = 100;}
            else if ( gamepad1.y) {target = 80;}
            error = target - motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f;
            double output = kp * error;

            motor.setPower(output);
           telemetry.addData("leftMotor position" ,motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f);
           telemetry.update();
        }


    }
}