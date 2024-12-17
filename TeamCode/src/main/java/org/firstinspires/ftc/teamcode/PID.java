package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PID {
    private Telemetry telemetry;

    public PID(Telemetry telemetry) {
        this.telemetry = telemetry;
    }

    public void p(DcMotor motor, double target, double kp) {
        double error = 1;
        while (error != 0) {
            error = target - motor.getCurrentPosition() / 28f * 12f / 120f * 12.5f; //TODO numbers aren't right probably
            double output = kp * error;

            motor.setPower(output);
            telemetry.addData("Arm position", motor.getCurrentPosition() / 28f * 12f / 120f * 12.5f);
            telemetry.update();
        }
    }
}