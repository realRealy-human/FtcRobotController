package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PID;

// define the class
public class ElevatorPControl {
    // define the motor and telemetry
    private DcMotor motor;
    private Telemetry telemetry;
    private PID pID;

    // define the constructor
    public ElevatorPControl(HardwareMap hardwareMap, Telemetry telemetry) {
        // map the motor
        motor = hardwareMap.get(DcMotor.class, "leftMotor");
        // set the zero power behavior of the motor
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pID = new PID(telemetry);

        // save the telemetry
        this.telemetry = telemetry;
    }

    public void goTo(double target) {
      pID.p(motor, target, 0.18);
    }

    public double FindLocation(){
        return motor.getCurrentPosition();
    }
}
