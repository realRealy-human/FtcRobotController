package org.firstinspires.ftc.teamcode.Drive;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class PController {
    private double kp; // Proportional gain
    private Telemetry telemetry;
    private DcMotor motor;

    public PController(HardwareMap hardwareMap, Telemetry telemetry) {
        motor = hardwareMap.get(DcMotor.class, "leftMotor");

        this.telemetry = telemetry;
    }

    public PController(double kp) {
        this.kp = kp;
    }

    public void calculate(double setpoint, double currentValue) {
        double error = setpoint - currentValue;
        double output = kp * error;

        motor.setPower(output);
    }
}
