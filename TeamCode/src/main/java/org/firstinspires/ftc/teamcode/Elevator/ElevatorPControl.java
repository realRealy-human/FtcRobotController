package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ElevatorPControl {
    private DcMotor motor;
    private Telemetry telemetry;
    private double kp =  0.18; // Proportional gain

    public ElevatorPControl(HardwareMap hardwareMap, Telemetry telemetry) {
        motor = hardwareMap.get(DcMotor.class, "leftMotor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        this.telemetry = telemetry;
    }

    public void goTo(double target) {
        double error = target - motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f;
        double output = kp * error;

        motor.setPower(output);
        telemetry.addData("leftMotor position" ,motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f);
        telemetry.update();
    }

    public double FindLocation(){
        return motor.getCurrentPosition();
    }
}
