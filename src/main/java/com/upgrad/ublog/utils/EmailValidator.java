package com.upgrad.ublog.utils;

import com.upgrad.ublog.exceptions.EmailNotValidException;

import java.util.regex.Pattern;

public class EmailValidator {
    public static boolean isValidEmail(String email) throws EmailNotValidException {
        String regex = "^[a-z0-9]+@[a-z0-9]+\\.[a-z0-9]{2,6}$";
        if (!Pattern.matches(regex, email)) {
            throw new EmailNotValidException("Please provide valid email address");
        } else {
            return true;
        }
    }
}
