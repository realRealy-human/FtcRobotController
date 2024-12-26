package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Drive.Drive1;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;

import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;


@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private Intake1 intake;
    private Basket basket;
    private Claw Claw;
    private ElevatorPControl elevatorPControl;
    private boolean open = false;
    private double openPos = 0.232;
    private double closePos = 0.055;
    private Distance distanceSensor;
    private Drive1 drive;
    // defining the basic things for the system


    @Override
      public void runOpMode() {
        basket = new Basket(hardwareMap, telemetry);
        elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
        Claw = new Claw(hardwareMap, telemetry);
        intake = new Intake1(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        drive = new Drive1(hardwareMap, telemetry);

        basket.closeBasket();

        // more defining and putting things in their starting positions

        waitForStart();

        boolean previousAState = false;

//        elevatorPControl.goTo(70);
//        basket.openBasket();
//        sleep(1000);
//        basket.closeBasket();
//        sleep(1000);
//        elevatorPControl.goTo(0);

//        intake.waitForGamePieceWIthDistance();
        while (opModeIsActive()) {
            drive.driveUsingGamepad(gamepad1);
            if (gamepad1.a) {
                if (elevatorPControl.FindLocation() < 25) {
                    elevatorPControl.goTo(70);
                } else {
                    elevatorPControl.goTo(0);
                }
            }
            // if you press on A and it's starting position is below 500 it will go up and if its higher it will go down
            if (gamepad1.y) {
                intake.waitForGamePieceWIthDistance();
                // if Y is pressed it will find a game piece and take it

            }
            if (gamepad1.x) {
                Claw.toggleClaw(true);
                sleep(500);
            }
            if (gamepad1.b) {
                open = !open;

                if (open) {
                    basket.openBasket();
                } else {
                    basket.closeBasket();
                }
                sleep(750);
            }

        }
    }
}