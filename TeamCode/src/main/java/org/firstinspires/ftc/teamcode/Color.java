package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import com.qualcomm.robotcore.hardware.ColorSensor;

public class Color {
    private ColorSensor colorSensor;
    private Telemetry telemetry;
    public Color(HardwareMap hardwareMap, Telemetry telemetry) {
        colorSensor = hardwareMap.get(ColorSensor.class, "colorSensor");
        // defining the things we need in this system

        this.telemetry = telemetry;
        // updating telemetry
    }
    public int colorSenseRed() {
        return colorSensor.red();
    }
    public int colorSenseGreen() {
        return colorSensor.green();
    }
    public int colorSenseBlue() {
        return colorSensor.blue();
    }
    public int senseObject() {
        return colorSensor.alpha();
    }
    // defining the types of colorSensors we have like what color they are

    private boolean searchForObject() {
        // Read the color sensor data
        int red = colorSensor.red();
        int green = colorSensor.green();
        int blue = colorSensor.blue();
        int alpha = colorSensor.alpha(); // Overall light intensity

        // Check for an object based on light intensity or color threshold
        return alpha > 100 || red > 50 || blue > 50 || green > 50;
    }
}