package org.firstinspires.ftc.teamcode;

// import stuff
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

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

//        elevatorPControl.goTo(70);
//        basket.openBasket();
//        sleep(1000);
//        basket.closeBasket();
//        sleep(1000);
//        elevatorPControl.goTo(0);

//        intake.waitForGamePieceWIthDistance();
        while (opModeIsActive()) {
            drive.driveUsingGamepad(gamepad1);

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
            if (gamepad1.left_bumper){
                elevatorPControl.setSetPoint(70);
                while (elevatorPControl.getPosition() != 70) {
                    elevatorPControl.updateBySetPoint();
                }
                basket.openBasket();
                sleep(100);
                basket.closeBasket();
                elevatorPControl.setSetPoint(0);
            }

            elevatorPControl.updateBySetPoint();
            arm.updateBySetPoint();

            dashTele.addData("armPose", arm.getPosition());
            dashTele.addData("armSetPoint", arm.getSetPoint());
            dashTele.addData("leftBumper" , gamepad1.left_bumper );

            dashTele.addData("elevatorPose", elevatorPControl.getPosition());
            dashTele.addData("elevatorSetPoint", elevatorPControl.getSetPoint());
            dashTele.update();
        }
    }
}