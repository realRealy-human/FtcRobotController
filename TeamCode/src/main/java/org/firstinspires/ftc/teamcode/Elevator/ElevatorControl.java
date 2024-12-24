package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class ElevatorControl {
    // define the motor and telemetry
    private DcMotor elevatorMotor;
    private Telemetry telemetry;

    //
    public ElevatorControl(HardwareMap hardwareMap, Telemetry telemetry) {
        elevatorMotor = hardwareMap.get(DcMotor.class, "elevator");

        this.telemetry = telemetry;
    }

    public void controlElevator(Double power) {
        elevatorMotor.setPower(power);
    }
}