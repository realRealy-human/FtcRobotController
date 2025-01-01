package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PID;

// define the class
public class ElevatorPControl {
    // define the motor, telemetry and the PID object
    private DcMotor motor;
    private Telemetry telemetry;
    private PID pidController;
    private double conversion = 2600 / 125.6;
    private double setPoint;

    // define the constructor
    public ElevatorPControl(HardwareMap hardwareMap, Telemetry telemetry) {
        // map the motor
        motor = hardwareMap.get(DcMotor.class, "elevator");
        // set the zero power behavior of the motor
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        motor.setDirection(DcMotorSimple.Direction.REVERSE);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // create the PID object
        pidController = new PID(0.001);

        // save the telemetry
        this.telemetry = telemetry;
    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    public double getSetPoint() {
        return setPoint;
    }

    public void updateBySetPoint() {
        motor.setPower(pidController.calculateP(getSetPoint() * 23f , motor.getCurrentPosition()));
    }

    public boolean atPoint() {
        return Math.abs(getSetPoint() - getPosition()) <= 2;
    }

    public double getPosition(){
        return motor.getCurrentPosition() /23f;
    }
}
