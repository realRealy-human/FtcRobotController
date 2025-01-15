package org.firstinspires.ftc.teamcode.Intake;

import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Arm;
//importing functions from other files


public class IntakeAndArm {
    private Intake1 intake;
    private Arm arm;
    private ColorSensor colorSensor ;
    private Telemetry telemetry;
    // defining every main thing

    public IntakeAndArm(HardwareMap hardwareMap, Telemetry telemetry) {
        intake = new Intake1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);


        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        //adding details to the main things we will be using

        this.telemetry = telemetry;
        //updating telemetry
    }

    public void searchAndCollectGamePiece(boolean redGroup) {
       while(!(((redGroup ? colorSensor.blue() : colorSensor.red()) > 200) || colorSensor.green() > 200 && colorSensor.red() > 200)) {}
        //arm.moveArmP(95);
        //moving the arm using P


//       intake.setSpeed(0.5);
//        intake.moveWithSpeed();
//        //when a game piece is found the intake will move
//
//        //arm.moveArmP(0);
//        intake.setSpeed(0);
//        intake.moveWithSpeed();
        // stopping the system
    }
}

