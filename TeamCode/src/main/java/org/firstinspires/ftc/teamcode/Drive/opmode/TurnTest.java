package org.firstinspires.ftc.teamcode.Drive.opmode;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Drive.SampleMecanumDrive1;

///*
// * This is a simple routine to test turning capabilities.
// */
@Config
@Autonomous(group = "drive")
public class TurnTest extends LinearOpMode {
    public static double ANGLE = 90; // deg

@Override
   public void runOpMode() throws InterruptedException {
       SampleMecanumDrive1 drive = new SampleMecanumDrive1(hardwareMap);
//
      waitForStart();
//
        if (isStopRequested()) return;
//
      drive.turn(Math.toRadians(ANGLE));
    }
}
