package org.firstinspires.ftc.teamcode;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Elevator.Elevator;
import org.firstinspires.ftc.teamcode.intake;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DistanceSensor;

@TeleOp(name = "firstTele", group = "Examples")
public class firstTele extends LinearOpMode {

    private Elevator elevator;
    private Arm arm;
    private intake intake;
    //private DistanceSensor distanceSensor;

    @Override
    public void runOpMode() {
        //elevator = new Elevator(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        intake = new intake(hardwareMap, telemetry);

        waitForStart();
        while (opModeIsActive()) {

            arm.armUpdateBySetPoint();

            if (gamepad1.a) {
                arm.armSetSetPoint(-2);
            }
            if (gamepad1.b) {
                arm.armSetSetPoint(0.3);
            }
            if (gamepad1.dpad_up){
                intake.setintake();
            }
            if (gamepad1.dpad_down){
                intake.intakespeed(0);
            }
            if (gamepad1.dpad_right){
                intake.intakespeed(-1);
            }

//            if (gamepad1.y) {
//                elevator.setSetPoint(70);
//            }
//            if (gamepad1.x){
//                elevator.setSetPoint(0);
//            }
//            if (gamepad1.left_bumper){
//                intake.collectgamepiece(1);
//            }
        }

    }




}








