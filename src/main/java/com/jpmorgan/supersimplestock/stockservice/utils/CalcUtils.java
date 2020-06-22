package com.jpmorgan.supersimplestock.stockservice.utils;

public class CalcUtils {
    public static void checkValidCalculation(float divider) throws Exception {
        if (divider <= 0) throw new Exception("Unable to calculate");
    }
}
