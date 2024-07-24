package com.meetingRoomBooking.utilities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtility {

  private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");

  public static LocalTime convertStringToLocalTime(String timeString) {
    return LocalTime.parse(timeString, dateTimeFormatter);
  }

  public static String convertLocalTimeToString(LocalTime localTime) {
    return localTime.format(dateTimeFormatter);
  }

  public static LocalDate convertStringToLocalDate(String dateString) {
    return LocalDate.parse(dateString, formatter);
  }

  public static String convertLocalDateToString(LocalDate date) {
    return date.format(formatter);
  }
}
