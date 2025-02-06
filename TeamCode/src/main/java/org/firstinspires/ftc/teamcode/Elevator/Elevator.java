package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.PID;

// define the class
public class Elevator {
    // define the motor, telemetry and the PID object
    private DcMotor master;
    private DcMotor slave;
    private Telemetry telemetry;
    private PID pidController;
    private double conversion = 1;
    private double setPoint;

    // define the constructor
    public Elevator(HardwareMap hardwareMap, Telemetry telemetry) {
        // map the motor
        master = hardwareMap.get(DcMotor.class, "master");
        slave = hardwareMap.get(DcMotor.class, "slave");
        // set the zero power behavior of the motor
        master.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        master.setDirection(DcMotorSimple.Direction.REVERSE);
        master.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        master.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slave.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        slave.setDirection(DcMotorSimple.Direction.FORWARD);
        slave.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slave.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // create the PID object
        pidController = new PID(0.001);

    }

    public void setSetPoint(double setPoint) {
        this.setPoint = setPoint;
    }

    public double getSetPoint() {
        return setPoint;
    }

    public void updateBySetPoint() {
        double power = pidController.calculateP(getSetPoint() * 23 , master.getCurrentPosition());
        master.setPower(power);
        slave.setPower(power);
    }

    public boolean atPoint() {
        return Math.abs(getSetPoint() - getPosition()) <= 0.03;
    }

    public double getPosition(){
        return master.getCurrentPosition();

    }

    public void setPower(double power) {

    }
}
