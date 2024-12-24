package org.firstinspires.ftc.teamcode;

// import stuff
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
import org.firstinspires.ftc.teamcode.Intake.Claw;
import org.firstinspires.ftc.teamcode.Intake.IntakeAndArm;
<<<<<<< Updated upstream
import org.firstinspires.ftc.teamcode.Basket;


=======
import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;
>>>>>>> Stashed changes

@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private IntakeAndArm intakeAndArm;
    private Basket basket;
    private ElevatorPControl elevatorPControl;
<<<<<<< Updated upstream
    private boolean open = false;
    private Servo servo;
=======
    private Claw Claw;
    private double openPos = 0.232;
    private double closePos = 0.055;
>>>>>>> Stashed changes
    // defining the basic things for the system


    @Override
      public void runOpMode() {
        basket = new Basket(hardwareMap, telemetry, "servo1");
        elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
        intakeAndArm = new IntakeAndArm(hardwareMap, telemetry);
<<<<<<< Updated upstream
        servo = hardwareMap.get(Servo.class, "servo1");
=======
        Claw = new Claw(hardwareMap, telemetry);

>>>>>>> Stashed changes

        basket.closeBasket();

        // more defining and putting things in their starting positions

        waitForStart();

        boolean previousAState = false;

        while (opModeIsActive()) {
            if (gamepad1.x) {
                Claw.toggleClaw(true);
            }
           if (gamepad1.a) {
               elevatorPControl.goTo(0);
               if (elevatorPControl.FindLocation() < 500){
                   elevatorPControl.goTo(1000);
                   basket.openBasketLimited(1000);
               }
                else {
                   elevatorPControl.goTo(0);
                }
            }
            // if you press on A and it's starting position is below 500 it will go up and if its higher it will go down
<<<<<<< Updated upstream
            if (gamepad1.y) {
                intakeAndArm.searchAndCollectGamePiece(true);
                // if Y is pressed it will find a game piece and take it




=======
           if (gamepad1.y) {
             intakeAndArm.searchAndCollectGamePiece(true);
//                // if Y is pressed it will find a game piece and take it
>>>>>>> Stashed changes
            }
            if (gamepad1.b) {
                open = !open;

                if (open) {
                    basket.openBasket();
                }
                else {
                 basket.closeBasket();
                }
            }
//            servo.setPosition(0.5);
        }
<<<<<<< Updated upstream
    }  }

=======




    }
>>>>>>> Stashed changes
