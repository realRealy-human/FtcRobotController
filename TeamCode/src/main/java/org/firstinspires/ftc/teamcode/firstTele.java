package org.firstinspires.ftc.teamcode;

// import stuff

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.TouchSensor;
//

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Intake.Intake1;

@TeleOp(name = "firstTele", group = "Examples")
public class firstTele extends LinearOpMode {
    //    private Intake1 intake;
    // defining the basic things for the system
    private CRServo servoOne;
    private CRServo servoTwo;
    private String robotState = "IDLE";
    private int buttonPresses = 0;
    private boolean wasRightPressed = false;
    private TouchSensor touchRoller;


    @Override
    public void runOpMode() {
        servoOne = hardwareMap.crservo.get("intake");
        servoTwo = hardwareMap.crservo.get("intake1");
//        intake = new Intake1(hardwareMap, telemetry);
        touchRoller= hardwareMap.get(TouchSensor.class, "touchRoller");


        waitForStart();
        while (opModeIsActive()) {
            if (gamepad1.right_bumper) {
                robotState = "ROLL";
            }

        }

    }

    public void takeInGamePiece() {
        servoTwo.setPower(1);
        servoOne.setPower(-1);
    }

    public void takeOutGamePiece() {
        servoTwo.setPower(-1);
        servoOne.setPower(1);
    }

    public void stopRoller(){
        servoTwo.setPower(0);
        servoOne.setPower(0);
    }

    public void ROLL() {
        if (robotState.equals("ROLL")){
            if (gamepad1.right_bumper && !wasRightPressed) {
                buttonPresses++;
                switch (buttonPresses) {
                    case 1:
                        if (!touchRoller.isPressed()){
                            takeInGamePiece();
                        }
                        if (touchRoller.isPressed()){
                          stopRoller();
                        }

                        break;

                    case 2:

                        if (touchRoller.isPressed()){
                            takeOutGamePiece();
                        }
                        if (!touchRoller.isPressed()){
                            stopRoller();
                            buttonPresses = 0;
                            robotState = "IDLE";
                        }




                        break;


                }
            }
        }

    }
}





