package org.firstinspires.ftc.teamcode;

 import org.firstinspires.ftc.teamcode.Drive.DriveTeleOp1;
 import org.firstinspires.ftc.teamcode.Elevator.Elevator;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "firstTele", group = "Examples")
public class firstTele extends LinearOpMode {

    private Elevator elevator;
    private Arm arm;
    private intake intake;
    private double intakePos = 0;
    private double zeroPos = 0;
    private double highscoringPos = 0;
    private double highspecimenPos = 0;
    private double lowspecimenPos = 0;
    private double HPpos = 0.015;
    private double armBottomPos = 0;
    private double armTopPos = 0.45;
    private double armEnterPos = 0.83;
    private double armCollectPos = 0.91;
    private String robotState = "IDLE";

    private DriveTeleOp1 drive;
    private boolean senseGamePiece;

    //private DistanceSensor distanceSensor;

    @Override
    public void runOpMode() {
        //elevator = new Elevator(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        intake = new intake(hardwareMap, telemetry);
        drive = new DriveTeleOp1(hardwareMap, telemetry);

        robotState = "IDLE";
        waitForStart();
        while (opModeIsActive()) {

            drive.fieldCentricUsingGamePad(gamepad1);

//            if (gamepad1.y){
//                arm.armSetSetPoint(0);
//            }
//            if (gamepad1.b){
//                intake.intakespeed(0);
//            }

//
//            if (gamepad1.a) {
//                arm.armSetSetPoint(armBottomPos);
//            }
//            if (gamepad1.b) {
//                arm.armSetSetPoint(armTopPos);
//            }
//            if (gamepad1.y) {
//                arm.armSetSetPoint(armEnterPos);
//            }
//            if (gamepad1.x) {
//                arm.armSetSetPoint(armCollectPos);
//            }
//            if (gamepad1.dpad_left) {
//                intake.collectgamepiece();
//            }
//            if (gamepad1.dpad_up) {
//                intake.setintake();
//            }
//            if (gamepad1.dpad_down) {
//                intake.intakespeed(0);
//            }

            if (gamepad1.left_bumper) {
                robotState = "INTAKE";
            }

            if (gamepad1.right_bumper) {
                robotState = "SPECIMENHIGH";
            }


            if (gamepad1.touchpad){
                robotState = "IDLE";
            }


            if (robotState == "INTAKE" && !intake.getDistanceSensor().senseGamePiece()) {
                arm.armSetSetPoint(0.039);
                intake.setintake();
            }

            if (robotState == "INTAKE" && intake.getDistanceSensor().senseGamePiece()) {
                intake.intakespeed(0);
                arm.armSetSetPoint(0.62);
                robotState = "IDLE";
            }

            if (robotState == "SPECIMENHIGH") {
                arm.armSetSetPoint(0.43);
            }
            if (gamepad1.left_trigger > 1){
                robotState = "SPIT";
            }
            if (gamepad1.y){
                robotState = "ENTER";
            }



                if (robotState == "ENTER" && !intake.getDistanceSensor().senseGamePiece()) {
                    arm.armSetSetPoint(armEnterPos);
                }
                if (robotState == "SPIT" && !intake.getDistanceSensor().senseGamePiece()) {
                    arm.armSetSetPoint(armCollectPos);
                    intake.setintake();
                }
                if (robotState == "SPIT" && intake.getDistanceSensor().senseGamePiece()) {
                    intake.intakespeed(0);
                    arm.armSetSetPoint(armEnterPos);

                }

//            if (gamepad1.left_trigger > 1) {
//                if (robotState == "IDLE" && !distance.senseGamePiece()) {
//                    robotState = "HIGHSCORING";
//                    arm.armSetSetPoint(armCollectPos);
//                    intake.collectgamepiece();
//                }
//                if (robotState == "HIGHSCORING" && distance.senseGamePiece()) {
//                    robotState = "continuation";
//                    arm.armSetSetPoint(0.5);
//                    elevator.setSetPoint(20);
//                }
//                if (robotState == "continuation" && distance.senseGamePiece()) {
//                    robotState = "FINISH";
//                    intake.spitOutGamePiece();
//                }
//                if (robotState == "FINISH" && !distance.senseGamePiece()) {
//                    robotState = "IDLE";
//                }
//            }
            if (gamepad1.a){
                intake.spitOutGamePiece();
                arm.armSetSetPoint(0.6);
            }
            arm.armUpdateBySetPoint();
            telemetry.addData("Distance", intake.getDistanceSensor().getDistance());
            telemetry.update();


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
















