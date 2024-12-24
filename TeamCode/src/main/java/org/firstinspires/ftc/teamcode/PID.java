package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.robotcore.external.Telemetry;

// define the class
public class PID {
    // define the telemetry variable
    private Telemetry telemetry;
    //creating telemetry

    // define the constructor
    public PID(Telemetry telemetry) {
        // save the telemetry
        this.telemetry = telemetry;
    }

    public void p(DcMotor motor, double target, double kp) {
        double error = 1;
        // defining the kp to later on change the error
        while (error != 0) {
            error = target - motor.getCurrentPosition();
            double output = kp * error;
            // when there is an error it will get the position on the motor and based on the numbers it will fix the error

            motor.setPower(output);

            // now the motor speed is the changed number which is right
            telemetry.addData("Arm position", motor.getCurrentPosition() );
            // adding data to telemetry
            telemetry.update();
            //updating the telemetry

        }
    }
}