package com.diplom.angai.diplom.utils;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

public class CustomTextUtils {

    public static String format(double location) {
        DecimalFormatSymbols separator = new DecimalFormatSymbols();
        separator.setDecimalSeparator('.');
        DecimalFormat numberFormat = new DecimalFormat("#.000000", separator);

        return numberFormat.format(location);
    }
}
