package org.firstinspires.ftc.teamcode.Elevator;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Claw;

public class ElevatorAndClaw {
    private Claw claw;
    private ElevatorPControl elevator;
    private int buttonPresses = 0;

    public ElevatorAndClaw(HardwareMap hardwareMap, Telemetry telemetry) {
        claw = new Claw(hardwareMap, telemetry);
        elevator = new ElevatorPControl(hardwareMap, telemetry);
    }

    public void ElevatorUpDown1() {
        buttonPresses++;
        switch (buttonPresses) {
            case 1:
                claw.openOrCloseClaw(true , false);
                elevator.goTo(500);

                break;
            case 2:
                claw.openOrCloseClaw(false, true);
                elevator.goTo(700);

                break;
            case 3:
                claw.openOrCloseClaw(true, false);
                elevator.goTo(1000);

                break;
            default:
                elevator.goTo(0);

                buttonPresses = 0;
        }
    }
}
