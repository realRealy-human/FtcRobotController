package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Intake.Claw;

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private Claw claw;

    @Override
    public void runOpMode() {
        claw = new Claw(hardwareMap, telemetry);

        waitForStart();

        while (opModeIsActive()) {
            claw.openOrCloseClaw(gamepad1.a, gamepad1.y);

            sleep(200);
        }
    }
}