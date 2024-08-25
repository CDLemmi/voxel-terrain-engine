package de.cdlemmi.vte.util;

public class Util {

    public static double limit(double value, double min, double max) {
        return Math.min(Math.max(value, min), max);
    }

    public static double wrap(double value, double min, double max) {
        double diff = max - min;
        if(value < min) {
            value += diff;
        }
        if(value > max) {
            value -= diff;
        }
        return value;
    }

}
