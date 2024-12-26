package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DistanceSensor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class Distance {
    private DistanceSensor distanceSensor;
    private Telemetry telemetry;

    public Distance(HardwareMap hardwareMap, Telemetry telemetry) {
        distanceSensor = hardwareMap.get(DistanceSensor.class, "distance_sensor");

        this.telemetry = telemetry;
    }

    public double whatDistance(DistanceUnit unit) {
        return distanceSensor.getDistance(unit);
    }

    public boolean senseGamePiece() {
        return distanceSensor.getDistance(DistanceUnit.CM) < 11;
    }
}