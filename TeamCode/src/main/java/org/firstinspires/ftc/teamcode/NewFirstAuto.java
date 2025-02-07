package org.firstinspires.ftc.teamcode;
import com.acmerobotics.dashboard.FtcDashboard;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.util.ElapsedTime;
//
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Drive.DriveTeleOp1;
import org.firstinspires.ftc.teamcode.Elevator.Elevator;

//import org.firstinspires.ftc.teamcode.intake.
@TeleOp(name = "NewFirstAuto", group = "Examples")
public class NewFirstAuto {
    private HardwareMap hardwareMap;
    private Telemetry telemetry;
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

    public void runOpMode() {
        dashboard = FtcDashboard.getInstance();
        dashTele = dashboard.getTelemetry();

        elevatorPControl = new Elevator(hardwareMap, telemetry);

        //intake = new intake(hardwareMap, telemetry);
        distanceSensor = new Distance(hardwareMap, telemetry);
        drive = new DriveTeleOp1(hardwareMap, telemetry);
        arm = new Arm(hardwareMap, dashTele);
        timerScoring = new ElapsedTime();
        timerScoringL = new ElapsedTime();
        timerPassing = new ElapsedTime();
        armDownTimer = new ElapsedTime();
        armUpTimer = new ElapsedTime();
        touchTop = hardwareMap.get(TouchSensor.class, "arm_top");



    }
}

