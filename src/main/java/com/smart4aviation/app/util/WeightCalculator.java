package com.smart4aviation.app.util;

public class WeightCalculator {

    public static Integer convertToKg(Integer value, String unit) {
        switch (unit) {
            case "lb":
                return Double.valueOf(value * 0.453).intValue();
            case "kg":
                return value;
            default:
                throw new RuntimeException(String.format("Unsupported unit type for conversion: %s", unit));
        }
    }
}
