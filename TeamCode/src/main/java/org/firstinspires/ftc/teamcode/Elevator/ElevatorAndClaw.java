package org.firstinspires.ftc.teamcode.Elevator;

// import stuff
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Claw;

// define the class
public class ElevatorAndClaw {
    // define the claw object, the elevatorPControl object and the button presses variables
    private Claw claw;
    private ElevatorPControl elevator;
    private int buttonPresses = 0;

    // define the constructor
    public ElevatorAndClaw(HardwareMap hardwareMap, Telemetry telemetry) {
        // create the claw and elevator object
        claw = new Claw(hardwareMap, telemetry);
        elevator = new ElevatorPControl(hardwareMap, telemetry);
    }

    public void ElevatorUpDown1() {
        // add 1 to the button presses variable
        buttonPresses++;

        switch (buttonPresses) {
            // if the button presses variable is equal to 1...
            case 1:
                // open the claw and move the elevator to the height 500
                claw.openOrCloseClaw(true , false);
                elevator.goTo(500);

                // break out of the switch
                break;

            // if the button presses variable is equal to 2...
            case 2:
                // close the claw and move the elevator to the height 700
                claw.openOrCloseClaw(false, true);
                elevator.goTo(700);

                // break out of the switch
                break;

            // if the button presses variable is equal to 3...
            case 3:
                // open the claw and move the elevator to the height 1000
                claw.openOrCloseClaw(true, false);
                elevator.goTo(1000);

                // break out of the switch
                break;

            // if the button presses variable is equal to 4 or anything else...
            default:
                // move the elevator to the floor
                elevator.goTo(0);

                // initialise the button presses variable
                buttonPresses = 0;
        }
    }
}
