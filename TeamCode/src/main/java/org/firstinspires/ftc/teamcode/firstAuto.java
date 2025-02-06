package org.firstinspires.ftc.teamcode;

// import stuff

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
//
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.DriveTeleOp1;
import org.firstinspires.ftc.teamcode.Elevator.Elevator;

//import org.firstinspires.ftc.teamcode.intake.


@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private intake intake;


    private Elevator elevatorPControl;
    private Arm arm;
    private boolean open = false;
    private double openPos = 0.232;
    private double closePos = 0.055;
    private Distance distanceSensor;
    private DriveTeleOp1 drive;
    private FtcDashboard dashboard;
    private Telemetry dashTele;
    private boolean isPressedArm;
    private boolean isPressedArm2;
    private boolean isPressedElevator;
    private boolean isPressedBasket;
    private String robotState = "IDLE";
    private boolean isGamePieceDoingScoring;
    private boolean isGamePieceDoingScoringLow;

    private boolean armPose;
    private ElapsedTime timerScoring;
    private ElapsedTime timerScoringL;

    private ElapsedTime armDownTimer;
    private boolean hasReset;
    private ElapsedTime timerPassing;
    private int buttonPresses = 0;
    private int pressesButtonX = 0;
    private boolean wasBPressed = false;
    private int buttonsPresses = 0;
    private boolean wasXPressed = false;
    private TouchSensor touchTop;
    private TouchSensor touchBottom;
    private ElapsedTime armUpTimer;


    // defining the basic things for the system


    @Override
    public void runOpMode() {


        dashboard = FtcDashboard.getInstance();
        dashTele = dashboard.getTelemetry();

        elevatorPControl = new Elevator(hardwareMap, telemetry);

        intake = new intake(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        drive = new DriveTeleOp1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, dashTele);
        timerScoring = new ElapsedTime();
        timerScoringL = new ElapsedTime();
        timerPassing = new ElapsedTime();
        armDownTimer = new ElapsedTime();
        armUpTimer = new ElapsedTime();
        touchTop = hardwareMap.get(TouchSensor.class, "arm_top");
//        Touch1 = new TouchSensor() {
//            @Override
//            public double getValue() {
//                return Touch1.getValue();            }
//
//            @Override
//            public boolean isPressed() {
//                return false;
//            }
//
//            @Override
//            public Manufacturer getManufacturer() {
//                return null;
//            }
//
//            @Override
//            public String getDeviceName() {
//                return "";
//            }
//
//            @Override
//            public String getConnectionInfo() {
//                return "";
//            }
//
//            @Override
//            public int getVersion() {
//                return 0;
//            }
//
//            @Override
//            public void resetDeviceConfigurationForOpMode() {
//
//            }
//
//            @Override
//            public void close() {
//
//            }
//        };

        touchBottom = hardwareMap.get(TouchSensor.class, "arm_bottom");
//        Touch2 = new TouchSensor() {
//
//            public double getValue() {
//                return Touch2.getValue();
//            }
//
//
//            public boolean isPressed() {
//                return false;
//            }
//
//
//            public Manufacturer getManufacturer() {
//                return null;
//            }
//
//
//            public String getDeviceName() {
//                return "";
//            }
//
//
//            public String getConnectionInfo() {
//                return "";
//            }
//
//
//            public int getVersion() {
//                return 0;
//            }
//
//
//            public void resetDeviceConfigurationForOpMode() {
//
//            }
//
//
//            public void close() {
//
//            }
//        };


        //arm.setSetPoint(0);


        // more defining and putting things in their starting positions

       /* dashTele.addData("armPose", arm.getPosition());
        dashTele.addData("armSetPoint", arm.getSetPoint());

        dashTele.addData("elevatorPose", elevatorPControl.getPosition());
        dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
        dashTele.addData("getIntakeSpeed", intake.getSpeed());
        dashTele.update();*/

        waitForStart();

        boolean previousAState = false;

        armDownTimer.reset();
        armUpTimer.reset();
        timerScoring.reset();

        while (opModeIsActive()) {
            drive.fieldCentricUsingGamePad(gamepad1);




            if (gamepad1.left_bumper) {
                robotState = "HIGHSCORING";
            }

            if (gamepad1.b) {
                robotState = "CLAW";
            }

            if (gamepad2.x) {


            if (gamepad1.dpad_up){

            }
            if (gamepad1.dpad_down){

            }
            if (gamepad1.dpad_right) {
                elevatorPControl.setSetPoint(0);
            }





            if (gamepad2.y){
                elevatorPControl.setSetPoint(0);
            }
            if (gamepad2.a){
                intake.setPower(0.5);
            }
            if (gamepad2.left_bumper){

            }
            if (gamepad2.right_bumper){

            }
            if (gamepad2.dpad_right){

            }
            if (gamepad2.dpad_left){

            }

            if (gamepad2.b){

            }
            if (gamepad1.a){
                intake.setPower(0);
            }

            if (gamepad1.left_trigger > 0.1){
                robotState = "LOW";
            }
            if (gamepad2.right_trigger > 0.1){
                elevatorPControl.setSetPoint(50);
            }
            if (gamepad1.x){

            }
            if (gamepad1.touchpad){
                robotState = "IDLE";
            }

            if (gamepad2.touchpad){
                robotState = "IDLE";
            }

            if (gamepad2.dpad_up){

            }
            if (gamepad2.dpad_down){

            }









            elevatorPControl.updateBySetPoint();



            /*dashTele.addData("gamePieceInside", intake.isGamePiece());
            dashTele.addData("getDistanceSensor", intake.getDistanceSensor());
            dashTele.addData("getIntakeSpeed", intake.getSpeed());
            dashTele.addData("robotState", robotState);




            dashTele
                    .addData("armPose", arm.getPosition())
                    .addData("armSetPoint", arm.getSetPoint())
                    .addData("elevatorPose", elevatorPControl.getPosition())
                    .addData("elevatorSetPoint", elevatorPControl.getSetPoint())
                    .addData("gamePieceInside", intake.isGamePiece())
                    .addData("getDistanceSensor", intake.getDistanceSensor())
                    .addData("getIntakeSpeed", intake.getSpeed())

                    .addData("roboState", robotState);


            dashTele.update();
            telemetry.update();*/
        }
    }
//
     private void intakeAutomation() {


         if (robotState == "INTAKE" && !intake.isGamePiece()) {
             dashTele.addData("intakeAuto state", 1);
             intake.setPower(-0.5);

             if (!touchBottom.isPressed()) {
                 arm.setPower(-0.3);
             } else {
                 arm.setPower(0);
             }
         }

         if (robotState == "INTAKE" && intake.isGamePiece() && !touchTop.isPressed()) {
             intake.setPower(0);
             if (!touchTop.isPressed()) {
                 arm.setPower(0.3);
             } else {
                 arm.setPower(0);
             }




             dashTele.addData("intakeAuto state", 2);
         }

         if (robotState == "INTAKE" && intake.isGamePiece() && touchTop.isPressed()) {
             robotState = "PASSING";




             dashTele.addData("intakeAuto state", 3);
         }


         if (robotState == "PASSING" && intake.isGamePiece()) {

                 intake.setPower(-0.7);


             dashTele.addData("intakeAuto state", 4);
         }

         if (robotState == "PASSING" && !intake.isGamePiece()) {
             robotState = "IDLE";

             dashTele.addData("intakeAuto state", 5);
         }
         if (robotState == "IDLE" && !intake.isGamePiece()) {
             intake.setPower(0);

             dashTele.addData("intakeAuto state", 6);
         }
     }

    private void highScoringAutomation() {
        if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && !isGamePieceDoingScoring) {
            if (elevatorPControl.getSetPoint() == 0) {
                basket.setPosition(0.64);
                elevatorPControl.setSetPoint(105);
//                basket.openBasket();

                dashTele.addData("basketStages", 1);

            } else if (elevatorPControl.getSetPoint() == 105 && elevatorPControl.atPoint()) {
                basket.closeBasket();
                isGamePieceDoingScoring = true;

                dashTele.addData("basketStages", 2);

                robotState = "IDLE";
            }
        }

        if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && isGamePieceDoingScoring) {
            if (basket.getPosition() > 0.4 && basket.getPosition() < 0.8) {
                basket.setPosition(0.3);

                timerScoring.reset();
                timerScoring.startTime();

                dashTele.addData("basketStages", 3);
            } else if (timerScoring.seconds() > 1) {
                basket.setPosition(1);
//                basket.closeBasket();
                elevatorPControl.setSetPoint(0);

                dashTele.addData("basketStages", 4);

                if (elevatorPControl.atPoint() && elevatorPControl.getSetPoint() == 0 && elevatorPControl.atPoint()) {
                    basket.closeBasket();

                    isGamePieceDoingScoring = false;

                    robotState = "IDLE";
                }
            }
        }
    }


    private void lowScoringAutomation() {

        if (robotState == "LOW" && elevatorPControl.atPoint() && !isGamePieceDoingScoringLow) {
            if (elevatorPControl.getSetPoint() == 0) {
                basket.setPosition(0.95);
                elevatorPControl.setSetPoint(58);

                dashTele.addData("basketStages", 1);

            } else if (elevatorPControl.getSetPoint() == 58 && elevatorPControl.atPoint()) {
                basket.closeBasket();
                isGamePieceDoingScoringLow = true;

                dashTele.addData("basketStages", 2);

                robotState = "IDLE";
            }
        }
    }



        public void clawControl() {
            if (robotState.equals("CLAW")) {
                if (gamepad1.b && !wasBPressed) {
                    buttonPresses++;
                    switch (buttonPresses) {
                        case 1:
                            Claw.close();
                            break;

                        case 2:
                            basket.setPosition(69);
                            elevatorPControl.setSetPoint(70);
                            break;

                        case 3:
                            elevatorPControl.setSetPoint(5);

                            break;
                        case 4:
                            Claw.open();
                            basket.setPosition(1);
                            break;


                        case 5:
                            elevatorPControl.setSetPoint(0);

                            buttonPresses = 0;
                            break;
                    }
                }

                wasBPressed = gamepad1.b;
            } else {

                wasBPressed = false;


            }
        }
        public void emergencyElevator() {
            if (robotState == "EMERGENCY") {
                elevatorPControl.setSetPoint(0);
            }
        }
        public void emergencyArm() {
            if (robotState == "PROBLEM") {
                arm.setSetPoint(0);
                intake.setPower(0);
            }
        }

        public void manualControl() {

//            /*if (gamepad1.a && elevatorPControl.getSetPoint() == 0 && !isPressedElevator) {
//                elevatorPControl.setSetPoint(70);
//                isPressedElevator = true;
//            } else if (gamepad1.a && elevatorPControl.getSetPoint() == 70 && !isPressedElevator) {
//                elevatorPControl.setSetPoint(0);
//                isPressedElevator = true;
//            }
//            if (!gamepad1.a) {
//                isPressedElevator = false;
//            }
//
//            if (gamepad1.b && !isPressedBasket) {
//                open = !open;
//
//                if (open) {
//                    basket.openBasket();
//                } else {
//                    basket.closeBasket();
//                }
//                isPressedBasket = true;
//            }
//
//            if (!gamepad1.b) {
//                isPressedBasket = false;
//            }
//
//
//
//            if (gamepad1.x && arm.getSetPoint() == 0 && !isPressedArm) {
//                arm.setSetPoint(-250);
//                isPressedArm = true;
//            } else if (gamepad1.x && arm.getSetPoint() == -250 && !isPressedArm) {
//                arm.setSetPoint(0);
//                isPressedArm = true;
//            }
//
//            if (!gamepad1.x) {
//                isPressedArm = false;
//            }
//
//            if (gamepad1.dpad_up && arm.getSetPoint() == 0 && !isPressedArm2) {
//                arm.setSetPoint(-10);
//                isPressedArm2 = true;
//            } else if (gamepad1.dpad_up && arm.getSetPoint() == -10 && !isPressedArm2) {
//                arm.setSetPoint(0);
//                isPressedArm2 = true;
//            }
//
//            if (!gamepad1.dpad_up) {
//                isPressedArm2 = false;
//            }
//
//            if (gamepad1.dpad_down) {
//                intake.setSpeed(0.1);
//                intake.moveWithSpeed();
//            }*/
        }
    }

