package org.firstinspires.ftc.teamcode.autonomus;


import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.navigation.Pose2D;
import org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.trajectorysequence.TrajectorySequence;

@Autonomous(name = "AutoBasketOne", group = "Examples")
public class AutoBasketOne extends LinearOpMode {
    private org.firstinspires.ftc.teamcode.drive.SampleMecanumDrive drive;
    private Pose2d startPose = new Pose2d( 0, -72, 0);

    @Override
    public void runOpMode() {
        drive = new SampleMecanumDrive(hardwareMap);
        drive.setPoseEstimate(startPose);

        TrajectorySequence seq1 = drive.trajectorySequenceBuilder(startPose)
                .lineToConstantHeading(new Vector2d(0, -16))
                .lineToConstantHeading(new Vector2d(0, MeterToInch(1.287)))
                .splineToLinearHeading(new Pose2d(-52, -36, Math.toRadians(180)), Math.toRadians(0))
                .lineToLinearHeading(new Pose2d(-60,-60,Math.toRadians(225)))
                .turn(Math.toDegrees(Math.atan(0.37161216 * 1.5) / (0.37161216 * 0.5)))
                .forward(MeterToInch(Math.sqrt(0.37161216 * 0.5) / (0.37161216 * 1.5)))
                .turn(Math.toDegrees(180 - Math.atan((0.37161216 * 0.5) / (0.37161216 * 1.5))))
                .back(0.37161216 * 1.5)
                .forward(0.37161216 * 2)
//                .turn(Math.toDegrees(-22))
//                .back(1)
                .forward(1)





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
