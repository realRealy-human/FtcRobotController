package org.firstinspires.ftc.teamcode;

// import stuff
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.Drive1;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;

import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;

import java.util.Timer;


@TeleOp(name = "firstAuto", group = "Examples")
public class firstAuto extends LinearOpMode {
    private Intake1 intake;
    private Basket basket;
    private Claw Claw;
    private ElevatorPControl elevatorPControl;
    private  Arm arm;
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
    private ElapsedTime timer;
    // defining the basic things for the system


    @Override
      public void runOpMode() {
        basket = new Basket(hardwareMap, telemetry);
        elevatorPControl = new ElevatorPControl(hardwareMap, telemetry);
        Claw = new Claw(hardwareMap, telemetry);
        intake = new Intake1(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        drive = new Drive1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, telemetry);
        dashboard = FtcDashboard.getInstance();
        dashTele = dashboard.getTelemetry();
        timer = new ElapsedTime();

        arm.setSetPoint(0);
        basket.closeBasket();

        // more defining and putting things in their starting positions

        dashTele.addData("armPose", arm.getPosition());
        dashTele.addData("armSetPoint", arm.getSetPoint());

        dashTele.addData("elevatorPose", elevatorPControl.getPosition());
        dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
        dashTele.update();

        waitForStart();

        boolean previousAState = false;

        while (opModeIsActive()) {
            drive.driveUsingGamepad(gamepad1);

            if (gamepad1.x && !intake.isGamePiece()){
                robotState = "INTAKE";
            }

            if (gamepad1.a) {
                robotState = "HIGHSCORING";
            }







            intakeAutomation();

            highScoringAutomation();







            elevatorPControl.updateBySetPoint();
            arm.updateBySetPoint();

            dashTele.addData("armPose", arm.getPosition());
            dashTele.addData("armSetPoint", arm.getSetPoint());
            dashTele.addData("elevatorPose", elevatorPControl.getPosition());
            dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
            dashTele.addData("gamePieceInside", intake.isGamePiece());
            dashTele.addData("getDistanceSensor", intake.getDistanceSensor());
            dashTele.update();
        }
    }
    private void intakeAutomation() {
        if (robotState == "INTAKE" && arm.atPoint() && !intake.isGamePiece()) {
            intake.move(0.5);
        }

        if (robotState == "INTAKE" && intake.isGamePiece()){
            intake.move(0);
            arm.setSetPoint(0);
        }

        if (robotState == "INTAKE" && intake.isGamePiece() && arm.getSetPoint() == 0 && arm.atPoint()) {
            robotState = "IDLE";
        }

        if (robotState == "INTAKE" && !intake.isGamePiece()) {
            arm.setSetPoint(-250);
        }
    }

    private void highScoringAutomation() {
        if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && !isGamePieceDoingScoring) {
            if (elevatorPControl.getSetPoint() == 0) {
                basket.setPosition(0.95);
                elevatorPControl.setSetPoint(70);
            } else if (elevatorPControl.getSetPoint() == 70) {
                basket.closeBasket();
                isGamePieceDoingScoring = true;

                robotState = "IDLE";
            }
        }

        if (robotState == "HIGHSCORING" && elevatorPControl.atPoint() && elevatorPControl.getSetPoint() == 70  && isGamePieceDoingScoring) {
            if (basket.getPosition() > 0.65) {
                basket.openBasket();
                timer.reset();
                timer.startTime();
            } else if (timer.seconds() > 1){
                basket.setPosition(0.95);
                elevatorPControl.setSetPoint(0);

                if (elevatorPControl.atPoint() && elevatorPControl.getSetPoint() == 0) {
                    basket.closeBasket();

                    isGamePieceDoingScoring = false;

                    robotState = "IDLE";
                }
            }
        }
    }

    public void manualControl(){

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

        if (gamepad1.dpad_down){
            intake.move(0.1);
        }
    }
}