//package org.firstinspires.ftc.teamcode.Drive.opmode;
//
//import androidx.annotation.NonNull;
//
//import com.acmerobotics.dashboard.config.Config;
//import com.acmerobotics.roadrunner.geometry.Pose2d;
//import com.acmerobotics.roadrunner.localization.ThreeTrackingWheelLocalizer;
//import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
//import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
//import com.qualcomm.robotcore.hardware.DcMotor;
//import com.qualcomm.robotcore.hardware.DcMotorEx;
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.teamcode.Drive.SampleMecanumDrive;
//import org.firstinspires.ftc.teamcode.util.Encoder;
//
//import java.util.Arrays;
//import java.util.List;
//
///**
// * This is a simple teleop routine for testing localization. Drive the robot around like a normal
// * teleop routine and make sure the robot's estimated pose matches the robot's actual pose (slight
// * errors are not out of the ordinary, especially with sudden drive motions). The goal of this
// * exercise is to ascertain whether the localizer has been configured properly (note: the pure
// * encoder localizer heading may be significantly off if the track width has not been tuned).
// */
//@TeleOp(group = "drive")
//public class LocalizationTest extends LinearOpMode {
//    @Override
//    public void runOpMode() throws InterruptedException {
//        SampleMecanumDrive drive = new SampleMecanumDrive(hardwareMap);
//
//        drive.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
//
//        waitForStart();
//
//        while (!isStopRequested()) {
//            drive.setWeightedDrivePower(
//                    new Pose2d(
//                            -gamepad1.left_stick_y,
//                            -gamepad1.left_stick_x,
//                            -gamepad1.right_stick_x
//                    )
//            );
//
//            drive.update();
//
//            Pose2d poseEstimate = drive.getPoseEstimate();
//            telemetry.addData("x", poseEstimate.getX());
//            telemetry.addData("y", poseEstimate.getY());
//            telemetry.addData("heading", poseEstimate.getHeading());
//            telemetry.update();
//        }
//    }
//
//    /*
//     * Sample tracking wheel localizer implementation assuming the standard configuration:
//     *
//     *    /--------------\
//     *    |     ____     |
//     *    |     ----     |
//     *    | ||        || |
//     *    | ||        || |
//     *    |              |
//     *    |              |
//     *    \--------------/
//     *
//     */
//    @Config
//    public static class StandardTrackingWheelLocalizer extends ThreeTrackingWheelLocalizer {
//        public static double TICKS_PER_REV = 0;
//        public static double WHEEL_RADIUS = 2; // in
//        public static double GEAR_RATIO = 1; // output (wheel) speed / input (encoder) speed
//
//        public static double LATERAL_DISTANCE = 10; // in; distance between the left and right wheels
//        public static double FORWARD_OFFSET = 4; // in; offset of the lateral wheel
//
//        private Encoder leftEncoder, rightEncoder, frontEncoder;
//
//        private List<Integer> lastEncPositions, lastEncVels;
//
//        public StandardTrackingWheelLocalizer(HardwareMap hardwareMap, List<Integer> lastTrackingEncPositions, List<Integer> lastTrackingEncVels) {
//            super(Arrays.asList(
//                    new Pose2d(0, LATERAL_DISTANCE / 2, 0), // left
//                    new Pose2d(0, -LATERAL_DISTANCE / 2, 0), // right
//                    new Pose2d(FORWARD_OFFSET, 0, Math.toRadians(90)) // front
//            ));
//
//            lastEncPositions = lastTrackingEncPositions;
//            lastEncVels = lastTrackingEncVels;
//
//            leftEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "leftEncoder"));
//            rightEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "rightEncoder"));
//            frontEncoder = new Encoder(hardwareMap.get(DcMotorEx.class, "frontEncoder"));
//
//            // TODO: reverse any encoders using Encoder.setDirection(Encoder.Direction.REVERSE)
//        }
//
//        public static double encoderTicksToInches(double ticks) {
//            return WHEEL_RADIUS * 2 * Math.PI * GEAR_RATIO * ticks / TICKS_PER_REV;
//        }
//
//        @NonNull
//        @Override
//        public List<Double> getWheelPositions() {
//            int leftPos = leftEncoder.getCurrentPosition();
//            int rightPos = rightEncoder.getCurrentPosition();
//            int frontPos = frontEncoder.getCurrentPosition();
//
//            lastEncPositions.clear();
//            lastEncPositions.add(leftPos);
//            lastEncPositions.add(rightPos);
//            lastEncPositions.add(frontPos);
//
//            return Arrays.asList(
//                    encoderTicksToInches(leftPos),
//                    encoderTicksToInches(rightPos),
//                    encoderTicksToInches(frontPos)
//            );
//        }
//
//        @NonNull
//        @Override
//        public List<Double> getWheelVelocities() {
//            int leftVel = (int) leftEncoder.getCorrectedVelocity();
//            int rightVel = (int) rightEncoder.getCorrectedVelocity();
//            int frontVel = (int) frontEncoder.getCorrectedVelocity();
//
//            lastEncVels.clear();
//            lastEncVels.add(leftVel);
//            lastEncVels.add(rightVel);
//            lastEncVels.add(frontVel);
//
//            return Arrays.asList(
//                    encoderTicksToInches(leftVel),
//                    encoderTicksToInches(rightVel),
//                    encoderTicksToInches(frontVel)
//            );
//        }
//    }
//}
