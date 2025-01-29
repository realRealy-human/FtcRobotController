//package org.firstinspires.ftc.teamcode;
//
//// import stuff
//import com.qualcomm.robotcore.hardware.HardwareMap;
//
//import org.firstinspires.ftc.robotcore.external.Telemetry;
//
//import com.qualcomm.robotcore.hardware.ColorSensor;
//
//// define the class
//public class Color {
//    // define the telemetry and the colorSensor variable
//    private Telemetry telemetry;
//    private ColorSensor colorSensor;
//
//    // define the constructor
//    public Color(HardwareMap hardwareMap, Telemetry telemetry) {
//        //colorSensor = hardwareMap.get(ColorSensor.class, "color_sensor");

//        // save the telemetry
//        this.telemetry = telemetry;
//    }
//    public int colorSenseRed() {
//        return colorSensor.red();
//    }
//    public int colorSenseGreen() {
//        return colorSensor.green();
//    }
//    public int colorSenseBlue() {
//        return colorSensor.blue();
//    }
//    public int senseObject() {
//        return colorSensor.alpha();
//    }
//    // defining the types of colorSensors we have like what color they are
//
//    public boolean searchForObject() {
        // Read the color sensor data
//        int red = colorSensor.red();
//        int green = colorSensor.green();
//        int blue = colorSensor.blue();
//        int alpha = colorSensor.alpha(); // Overall light intensity
//
//        // Check for an object based on light intensity or color threshold
//        return alpha > 800 || red > 400 || blue > 100 || green > 800;
//    }
//}