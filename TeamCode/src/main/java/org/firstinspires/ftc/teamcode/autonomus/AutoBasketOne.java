package org.firstinspires.ftc.teamcode.autonomus;


import android.graphics.Color;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Arm;
import org.firstinspires.ftc.teamcode.Basket;
import org.firstinspires.ftc.teamcode.Drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;

import org.firstinspires.ftc.teamcode.Intake.Intake1;
import org.firstinspires.ftc.teamcode.Intake.Claw;


//import org.firstinspires.ftc.teamcode.Drive.Drive1;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "AutoBasketOne", group = "Examples")
public class AutoBasketOne extends LinearOpMode {
    private SampleMecanumDrive drive;
    private Pose2d startPose = new Pose2d( 0, -72, 0); // 1
    private Arm arm;
    private ElevatorPControl Elevator;
    private Claw Claw;
    private Intake1 Intake;
    private Basket Basket;
    private Color Color;
    private HardwareMap hardwareMap;

    @Override
    public void runOpMode() {
        arm = new Arm(hardwareMap, telemetry);
        Elevator = new ElevatorPControl(hardwareMap,telemetry );
        Claw = new Claw(hardwareMap, telemetry);
        Intake = new Intake1(hardwareMap, telemetry);
//
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);
        TrajectorySequence seq1 = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(0, -18)) // 2
                .addTemporalMarker(2,() -> Elevator.setSetPoint(100))

                .lineToConstantHeading(new Vector2d(0, -16)) // 2
                .addDisplacementMarker(() -> Elevator.setSetPoint(50))
                .addDisplacementMarker(() -> Claw.openOrCloseClaw(true,false))

                .lineToConstantHeading(new Vector2d(0, -18))
                .addTemporalMarker(7,() -> Elevator.setSetPoint(0))
                .addTemporalMarker(8,() -> Claw.openOrCloseClaw(false,true))

                .splineToLinearHeading(new Pose2d(52, -36, Math.toRadians(180)), Math.toRadians(0)) // 3
                .addDisplacementMarker(() -> Intake.startAndStop())

                .lineToLinearHeading(new Pose2d(60,-60,Math.toRadians(225))) // 4
                .addTemporalMarker(11,() -> arm.setSetPoint(0))
                .addDisplacementMarker(() -> Intake.takeOut())
                .addTemporalMarker(() -> {
                    sleep(200);
                })

                .addDisplacementMarker(() -> Elevator.setSetPoint(100))
                .addDisplacementMarker(() -> Basket.openBasket())
                .addDisplacementMarker(() -> Elevator.setSetPoint(0))
                .addTemporalMarker(() -> Basket.closeBasket())


                .lineToLinearHeading(new Pose2d(60,-36, Math.toRadians(180)))
                // 5
                .addTemporalMarker(() -> arm.setSetPoint(-250))
                .addDisplacementMarker(() -> Intake.startAndStop())

                .lineToLinearHeading(new Pose2d(60,-60,Math.toRadians(225))) // 6
                .addTemporalMarker(17,() -> arm.setSetPoint(0))
                .addDisplacementMarker(() -> Intake.takeOut())
                .addTemporalMarker(() -> {
                    sleep(200);
                })

                .addDisplacementMarker(() ->Elevator.setSetPoint(100))
                .addDisplacementMarker(() -> Basket.openBasket())
                .addDisplacementMarker(() -> Elevator.setSetPoint(0))
                .addTemporalMarker(20,() -> Basket.closeBasket())


                .lineToLinearHeading(new Pose2d(60,-36,Math.toRadians(45)))
                .addTemporalMarker(20,() -> arm.setSetPoint(-250))
                .addDisplacementMarker(() -> Intake.startAndStop())

                .lineToLinearHeading(new Pose2d(60,-60,Math.toRadians(45))) // 8
               .addTemporalMarker(22,() -> arm.setSetPoint(0))
                .addTemporalMarker(22.5,() -> Intake.takeOut())
                .addTemporalMarker(() -> {
                    sleep(200);
                })

                .addDisplacementMarker(() -> Elevator.setSetPoint(100))
                .addDisplacementMarker(() -> Basket.openBasket())
                .addDisplacementMarker(() -> Elevator.setSetPoint(0))
                .addTemporalMarker(24.5,() -> Basket.closeBasket())

                .splineToLinearHeading(new Pose2d(-16, 0, Math.toRadians(180)), Math.toRadians(0)) //9

                .build();

        waitForStart();

        if (!isStopRequested()) {
            drive.followTrajectorySequence(seq1);
        }
    }
    private double MeterToInch(double Meter) {
        return Meter * 39.3701;
    }
}
