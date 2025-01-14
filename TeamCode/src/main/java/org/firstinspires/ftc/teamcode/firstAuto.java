package org.firstinspires.ftc.teamcode;

// import stuff

import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
    private Arm arm;
    private boolean open = false;
    private double openPos = 0.232;
    private double closePos = 0.055;
    private Distance distanceSensor;
    private Drive1 drive;
    private FtcDashboard dashboard;
    private Telemetry dashTele;
    private boolean isPressedArm;
    private boolean isPressedArm2;
    private boolean isPressedElevator;
    private boolean isPressedBasket;
    private String robotState = "IDLE";
    private boolean isGamePieceDoingScoring;
    private boolean armPose;
    private ElapsedTime timerScoring;
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

    // defining the basic things for the system


    @Override
    public void runOpMode() {
        dashboard = FtcDashboard.getInstance();
        dashTele = dashboard.getTelemetry();
        basket = new Basket(hardwareMap, telemetry);
        elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
        Claw = new Claw(hardwareMap, telemetry);
        intake = new Intake1(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        drive = new Drive1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, dashTele);
        timerScoring = new ElapsedTime();
        timerPassing = new ElapsedTime();
        armDownTimer = new ElapsedTime();
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




        arm.setSetPoint(0);
        basket.closeBasket();

        // more defining and putting things in their starting positions

        dashTele.addData("armPose", arm.getPosition());
        dashTele.addData("armSetPoint", arm.getSetPoint());

        dashTele.addData("elevatorPose", elevatorPControl.getPosition());
        dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
        dashTele.addData("getIntakeSpeed", intake.getSpeed());
        dashTele.update();

        waitForStart();

        boolean previousAState = false;

        armDownTimer.reset();

        while (opModeIsActive()) {
            drive.driveUsingGamepad(gamepad1);

            if (gamepad1.x && !intake.isGamePiece()) {
                robotState = "INTAKE";
            }

            if (gamepad1.a) {
                robotState = "HIGHSCORING";
            }

            if (gamepad1.b) {
                robotState = "CLAW";
            }
            if (gamepad1.left_bumper) {
                robotState = "PROBLEM";
            }
            if (gamepad1.right_bumper) {
                robotState = "EMERGENCY";
            }
            if (gamepad2.x) {
                robotState = "IDLE";
            }
            if (gamepad2.b){
                arm.setSetPoint(0);
            }
            if (gamepad2.y){
                elevatorPControl.setSetPoint(0);
            }
            if (gamepad2.a){
                intake.setPower(0);
            }
            if (gamepad2.left_bumper){
                basket.closeBasket();
            }
            if (gamepad2.right_bumper){
                intake.setPower(1);
            }
            if (gamepad2.dpad_right){
                Claw.openOrCloseClaw(false, true);
            }
            if (gamepad2.dpad_left){
                Claw.openOrCloseClaw(true, false);
            }

            intakeAutomation();

            highScoringAutomation();
            clawControl();
            emergencyElevator();
            emergencyArm();


            //intake.moveWithSpeed();
            elevatorPControl.updateBySetPoint();
//            arm.updateBySetPoint();

            dashTele.addData("armPose", arm.getPosition());
            dashTele.addData("armSetPoint", arm.getSetPoint());
            dashTele.addData("elevatorPose", elevatorPControl.getPosition());
            dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
            dashTele.addData("gamePieceInside", intake.isGamePiece());
            dashTele.addData("getDistanceSensor", intake.getDistanceSensor());
            dashTele.addData("getIntakeSpeed", intake.getSpeed());
            dashTele.addData("buttonPresses", buttonPresses);
            dashTele.addData("robotState", robotState);
            dashTele.addData("pressesA", gamepad1.a);
            dashTele.addData("pressesB", gamepad1.b);
            dashTele.addData("arm at point", arm.atPoint());
            dashTele.addData( "touch top is pressed", touchTop.isPressed());
            dashTele.addData( "touch bottom is pressed", touchBottom.isPressed());

//
//            dashTele
//                    .addData("armPose", arm.getPosition())
//                    .addData("armSetPoint", arm.getSetPoint())
//                    .addData("elevatorPose", elevatorPControl.getPosition())
//                    .addData("elevatorSetPoint", elevatorPControl.getSetPoint())
//                    .addData("gamePieceInside", intake.isGamePiece())
//                    .addData("getDistanceSensor", intake.getDistanceSensor())
//                    .addData("getIntakeSpeed", intake.getSpeed())
//                    .addData("buttonPresses", buttonPresses)
//                    .addData("roboState", robotState)
//                    .addData("pressesA", gamepad1.a)
//                    .addData("pressesB", gamepad1.b)
//                    .addData("arm at point", arm.atPoint());
            dashTele.update();
            telemetry.update();
        }
    }

    private void intakeAutomation() {
        intake.setPower(1);
       /* if (robotState.equals("INTAKE")) {
            if (gamepad1.x && !wasXPressed) {
                pressesButtonX++;
                switch (pressesButtonX) {
                    case 1:
                        arm.setSetPoint(-250);
                        intake.setSpeed(0.6);
                        if (intake.isGamePiece() == true) {
                            intake.setSpeed(0);

                        }
                        break;
                    case 2:
                        arm.setSetPoint(0);
                        intake.setSpeed(0.6);
                        if (intake.isGamePiece() == false) {
                            intake.setSpeed(0);
                            pressesButtonX = 0;
                            break;

                        }
                        wasXPressed = gamepad1.b;


                }

            }*/




        if (touchBottom.isPressed()){
            dashTele.addData("touch 2 is pressed", true);
        }
        if (touchTop.isPressed()){
            dashTele.addData("touch 1 is pressed", true);
        }
        if (!touchBottom.isPressed()){
            dashTele.addData("touch 2 isn pressed", false);
        }
        if (!touchTop.isPressed()){
            dashTele.addData("touch 1 is pressed", false);
        }
        if (robotState == "INTAKE" && !intake.isGamePiece() && !touchBottom.isPressed()) {
            dashTele.addData("intakeAuto state", 1);

            arm.setPower(-0.1);
        }

        if (robotState == "INTAKE" && !intake.isGamePiece() && touchBottom.isPressed()) {
            arm.setPower(0);
            intake.setSpeed(1);

            dashTele.addData("intakeAuto state", 2);
        }

        if (robotState == "INTAKE" && intake.isGamePiece() ) {
           if (!touchTop.isPressed()){
               arm.setPower(0.7);
           } else {
               arm.setPower(0);
           }


            dashTele.addData("intakeAuto state", 3);
        }
        if (robotState == "INTAKE" && intake.isGamePiece() && touchTop.isPressed()) {
           robotState = "PASSING";


            dashTele.addData("intakeAuto state", 3);
        }

        if (robotState == "PASSING" && intake.isGamePiece() ) {
            intake.setSpeed(0.6);


            dashTele.addData("intakeAuto state", 4);
        }
        if (robotState == "PASSING" && !intake.isGamePiece() ) {
            intake.setSpeed(0);
            robotState = "IDLE";


            dashTele.addData("intakeAuto state", 5);
        }



//        if (robotState == "PASSING" && intake.isGamePiece() && armDownTimer.seconds() < 6.8) {
//            intake.setSpeed(0.6);
//            dashTele.addData("intakeAuto state", 4);
//        }
//
//        if (robotState.equals("PASSING") && !intake.isGamePiece() &&  armDownTimer.seconds() < 6.9){
//            intake.setSpeed(0);
//            dashTele.addData("intakeAuto state", 5);
//        }
//
//        if (robotState.equals("PASSING") && !intake.isGamePiece() &&  armDownTimer.seconds() < 7 ){
//            robotState = "IDLE";
//        }
//
//        if (robotState == "IDLE"){
//            intake.setSpeed(0);
//            arm.setSetPoint(0);
//            armDownTimer.reset();


//
    }




        private void highScoringAutomation() {
            if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && !isGamePieceDoingScoring) {
                if (elevatorPControl.getSetPoint() == 0) {
                    basket.setPosition(0.95);
                    elevatorPControl.setSetPoint(105);

                    dashTele.addData("basketStages", 1);

                } else if (elevatorPControl.getSetPoint() == 105 && elevatorPControl.atPoint()) {
                    basket.closeBasket();
                    isGamePieceDoingScoring = true;

                    dashTele.addData("basketStages", 2);

                    robotState = "IDLE";
                }
            }

            if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && isGamePieceDoingScoring) {
                if (basket.getPosition() > 0.65 && basket.getPosition() < 1) {
                    basket.openBasket();
                    timerScoring.reset();
                    timerScoring.startTime();

                    dashTele.addData("basketStages", 3);

                } else if (timerScoring.seconds() > 1) {
                    basket.setPosition(1);
                    elevatorPControl.setSetPoint(0);

                    dashTele.addData("basketStages", 4);

                    if (elevatorPControl.atPoint() && elevatorPControl.getSetPoint() == 0 && elevatorPControl.atPoint()) {
                        basket.closeBasket();

                        isGamePieceDoingScoring = false;

                        robotState = "IDLE";

                        dashTele.addData("basketStages", 5);
                    }
                }
            }
        }

        public void clawControl() {
            if (robotState.equals("CLAW")) {
                if (gamepad1.b && !wasBPressed) {
                    buttonPresses++;
                    switch (buttonPresses) {
                        case 1:
                            Claw.openOrCloseClaw(true, false);
                            elevatorPControl.setSetPoint(20);

                            break;

                        case 2:
                            Claw.openOrCloseClaw(false, true);
                            elevatorPControl.setSetPoint(50);
                            break;

                        case 3:
                            elevatorPControl.setSetPoint(40);

                            Claw.openOrCloseClaw(true, false);
                            break;
                        case 4:
                            elevatorPControl.setSetPoint(0);
                            Claw.openOrCloseClaw(false, true);
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
                intake.setSpeed(0);
            }
        }

        public void manualControl() {

            if (gamepad1.a && elevatorPControl.getSetPoint() == 0 && !isPressedElevator) {
                elevatorPControl.setSetPoint(70);
                isPressedElevator = true;
            } else if (gamepad1.a && elevatorPControl.getSetPoint() == 70 && !isPressedElevator) {
                elevatorPControl.setSetPoint(0);
                isPressedElevator = true;
            }
            if (!gamepad1.a) {
                isPressedElevator = false;
            }

            if (gamepad1.b && !isPressedBasket) {
                open = !open;

                if (open) {
                    basket.openBasket();
                } else {
                    basket.closeBasket();
                }
                isPressedBasket = true;
            }

            if (!gamepad1.b) {
                isPressedBasket = false;
            }

            intake.waitForGamePieceWIthDistance(gamepad1.y);

            if (gamepad1.x && arm.getSetPoint() == 0 && !isPressedArm) {
                arm.setSetPoint(-250);
                isPressedArm = true;
            } else if (gamepad1.x && arm.getSetPoint() == -250 && !isPressedArm) {
                arm.setSetPoint(0);
                isPressedArm = true;
            }

            if (!gamepad1.x) {
                isPressedArm = false;
            }

            if (gamepad1.dpad_up && arm.getSetPoint() == 0 && !isPressedArm2) {
                arm.setSetPoint(-10);
                isPressedArm2 = true;
            } else if (gamepad1.dpad_up && arm.getSetPoint() == -10 && !isPressedArm2) {
                arm.setSetPoint(0);
                isPressedArm2 = true;
            }

            if (!gamepad1.dpad_up) {
                isPressedArm2 = false;
            }

            if (gamepad1.dpad_down) {
                intake.setSpeed(0.1);
                intake.moveWithSpeed();
            }
        }
    }

