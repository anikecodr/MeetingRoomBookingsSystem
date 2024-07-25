package com.meetingRoomBooking.utilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DateValidator {
    private static final String DATE_PATTERN = "^\\d{4}-\\d{2}-\\d{2}$";

    public static boolean isValidDate(String dateStr) {
        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(dateStr);
        return matcher.matches();
    }
    public static void main(String[] args){

        System.out.println(DateValidator.isValidDate("2024-07-25"));
    }
}