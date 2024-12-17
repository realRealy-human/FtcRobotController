package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class ElevatorPControl {
    // define the motor and telemetry
    private DcMotor motor;
    private Telemetry telemetry;
    private double kp =  0.18; // Proportional gain

    // define the constructor
    public ElevatorPControl(HardwareMap hardwareMap, Telemetry telemetry) {
        // map the motor
        motor = hardwareMap.get(DcMotor.class, "leftMotor");
        // set the zero power behavior of the motor
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // save the telemetry
        this.telemetry = telemetry;
    }

    public void goTo(double target) {
        double error = target - motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f;
        double output = kp * error;

        motor.setPower(output);
        telemetry.addData("leftMotor position" ,motor.getCurrentPosition()  /28f * 12f / 120f * 12.5f);
        telemetry.update();
    }
}
