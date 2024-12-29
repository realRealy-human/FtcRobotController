package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class PID {
    private double Kp;

    // define the constructor
    public PID(double Kp) {
       this.Kp = Kp;
    }

    public double calculateP(double target , double current) {
        return (target - current) * Kp;
    }
}