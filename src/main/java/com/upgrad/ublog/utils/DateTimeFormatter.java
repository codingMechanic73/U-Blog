package com.upgrad.ublog.utils;

import com.upgrad.ublog.dto.PostDTO;

import java.time.LocalDateTime;

/**
 * TODO: 7.32. Implement a method with the following signature.
 *  public static String format(LocalDateTime localDateTime)
 *  This method should convert the default date time to the human readable format[dd-MM-yyyy HH:mm:ss].
 */

public class DateTimeFormatter {

    public static  String format(LocalDateTime localDateTime) {
        java.time.format.DateTimeFormatter format = java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return localDateTime.format(format);
    }

    public static void main(String[] args) {
        PostDTO postDTO = new PostDTO();
        DateTimeFormatter.format(postDTO.getTimestamp());
    }

}
