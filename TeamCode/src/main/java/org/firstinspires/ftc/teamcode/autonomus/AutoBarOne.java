//package org.firstinspires.ftc.teamcode.autonomus;
//
//
//import android.graphics.Color;
//
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.geometry.Vector2d;
//import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//
//import org.firstinspires.ftc.teamcode.Arm;
//import org.firstinspires.ftc.teamcode.Basket;
//import org.firstinspires.ftc.teamcode.Elevator.ElevatorPControl;
//import org.firstinspires.ftc.teamcode.Intake.Claw;
//import org.firstinspires.ftc.teamcode.Intake.Intake1;
//import org.firstinspires.ftc.teamcode.Drive1.SampleMecanumDrive1;
//import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;
//
//@Autonomous(name = "AutoBarOne", group = "Examples")
//public class AutoBarOne extends LinearOpMode {
//    private SampleMecanumDrive1 drive;
//    private Pose2d startPose = new Pose2d( 0, -72, 0); // 1
//    private Arm arm;
//    private ElevatorPControl Elevator;
//    private Claw Claw;
//    private Intake1 Intake;
//    private Basket Basket;
//    private Color Color;
//
//
//    @Override
//    public void runOpMode() {
//        arm = new Arm(hardwareMap, telemetry);
//        Elevator = new ElevatorPControl(hardwareMap,telemetry );
//        Claw = new Claw(hardwareMap, telemetry);
//        Intake = new Intake1(hardwareMap, telemetry);
//
//
//
//        drive = new SampleMecanumDrive1(hardwareMap);
//        drive.setPoseEstimate(startPose);
//
//        TrajectorySequence seq1 = drive.trajectorySequenceBuilder(startPose)
//                .lineToConstantHeading(new Vector2d(0, -18)) // 2
//                .addTemporalMarker(2,() -> Elevator.setSetPoint(50))
//
//                .lineToConstantHeading(new Vector2d(0, -16)) // 2
//                .addDisplacementMarker(() -> Elevator.setSetPoint(40))
//                .addDisplacementMarker(() -> Claw.openOrCloseClaw(true,false))
//
//                .lineToConstantHeading(new Vector2d(0, -50))
//                .addTemporalMarker(5,() -> Elevator.setSetPoint(0))
//
//                .splineToLinearHeading(new Pose2d(36, -50, Math.toRadians(180)), Math.toRadians(0)) // 3
//                .splineToConstantHeading(new Vector2d(36, -4), Math.toRadians(0))
//
//                .lineToConstantHeading(new Vector2d(48, -4)) // 4
//                .lineToConstantHeading(new Vector2d(48, -72))
//                .lineToConstantHeading(new Vector2d(48, -4))
//                .lineToConstantHeading(new Vector2d(60, -4))
//                .lineToConstantHeading(new Vector2d(60, -72))
//                .lineToConstantHeading(new Vector2d(48, -72))
//                .addTemporalMarker(12, () -> Claw.openOrCloseClaw(false, true))
//                .lineToLinearHeading(new Pose2d(0, -18, Math.toRadians(180)))
//                .addTemporalMarker(13,() -> Elevator.setSetPoint(50))
//                .lineToLinearHeading(new Pose2d(0, -16, Math.toRadians(0)))
//                .addTemporalMarker(15,() -> Elevator.setSetPoint(40))
//                .addTemporalMarker(15.5,() -> Claw.openOrCloseClaw(true, false))
//                .lineToLinearHeading(new Pose2d(0, -50, Math.toRadians(180)))
//                .addTemporalMarker(16,() -> Elevator.setSetPoint(0))
//
//                .lineToConstantHeading(new Vector2d(36, -50))
//                .lineToConstantHeading(new Vector2d(36, -4))
//                .lineToConstantHeading(new Vector2d(69, -4))
//                .lineToConstantHeading(new Vector2d(69, -72))
//                .lineToConstantHeading(new Vector2d(48, -72))
//                .addTemporalMarker(20, () -> Claw.openOrCloseClaw(false, true))
//                .lineToLinearHeading(new Pose2d(0, -18, Math.toRadians(180)))
//                .addTemporalMarker(23,() -> Elevator.setSetPoint(50))
//                .lineToLinearHeading(new Pose2d(0, -16, Math.toRadians(0)))
//                .addTemporalMarker(24,() -> Elevator.setSetPoint(40))
//                .addTemporalMarker(24.5,() -> Claw.openOrCloseClaw(true, false))
//                .lineToLinearHeading(new Pose2d(0, -50, Math.toRadians(180)))
//                .addTemporalMarker(25,() -> Elevator.setSetPoint(0))
//
//                .lineToConstantHeading(new Vector2d(18, 0))
//
//                .build();
//
//        waitForStart();
//
//        if (!isStopRequested()) {
//            drive.followTrajectorySequenceAsync(seq1);
//        }
//        while (opModeIsActive()) {
//            Elevator.updateBySetPoint();
//        }
//    }
//
//    private double MeterToInch(double Meter) {
//        return Meter * 39.3701;
//    }
//
//
//}
