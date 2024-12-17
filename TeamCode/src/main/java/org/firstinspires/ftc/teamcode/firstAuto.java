package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import org.firstinspires.ftc.teamcode.Intake.IntakeAndArm;

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private IntakeAndArm intakeAndArm;
    private Basket basket;
    private ElevatorPControl elevatorPControl;
    // defining the basic things for the system



    @Override
      public void runOpMode() {
        basket = new Basket(hardwareMap, telemetry, "servo1");
        elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
        intakeAndArm = new IntakeAndArm(hardwareMap, telemetry);


        basket.closeBasket();

        // more defining and putting things in their starting positions

        waitForStart();

        boolean previousAState = false;

        while (opModeIsActive()) {
//            if (gamepad1.a) {
//                elevatorPControl.goTo(0);
//                if (elevatorPControl.FindLocation() < 500){
//                    elevatorPControl.goTo(1000);
//                    basket.openBasketLimited(1000);
//                }
//                else {
//                    elevatorPControl.goTo(0);
//                }
//            }
            // if you press on A and it's starting position is below 500 it will go up and if its higher it will go down
            if (gamepad1.y) {
                intakeAndArm.searchAndCollectGamePiece(true);
                // if Y is pressed it will find a game piece and take it
            }
        }
    }  }