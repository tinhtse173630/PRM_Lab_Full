package com.exercise.mssql_project.utils;

import java.text.DecimalFormat;

public class PriceUtils {

    public static String formatPrice(double price) {
        DecimalFormat df = new DecimalFormat("#,###");

        return df.format(price);
    }
}
