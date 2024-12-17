package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PID;

public class ElevatorPControl {
    private DcMotor motor;
    private Telemetry telemetry;
    private PID pID;

    public ElevatorPControl(HardwareMap hardwareMap, Telemetry telemetry) {
        motor = hardwareMap.get(DcMotor.class, "leftMotor");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pID = new PID(telemetry);

        this.telemetry = telemetry;
    }

    public void goTo(double target) {
      pID.p(motor, target, 0.18);
    }

    public double FindLocation(){
        return motor.getCurrentPosition();
    }
}
