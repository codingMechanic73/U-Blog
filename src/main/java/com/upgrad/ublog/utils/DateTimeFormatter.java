package com.upgrad.ublog.utils;

import java.time.LocalDateTime;

public class DateTimeFormatter {

    public static String format(LocalDateTime localDateTime) {
        java.time.format.DateTimeFormatter format = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(format);
    }
}
