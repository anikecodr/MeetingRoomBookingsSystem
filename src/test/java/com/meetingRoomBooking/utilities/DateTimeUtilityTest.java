package com.meetingRoomBooking.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import java.time.LocalTime;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DateTimeUtilityTest {

  @Test
  @Disabled("TODO: Complete this test")
  void testConvertStringToLocalTime() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.time.format.DateTimeParseException: Text 'Time String' could not be parsed at index 0
    //       at
    // java.base/java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:2052)
    //       at java.base/java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1954)
    //       at java.base/java.time.LocalTime.parse(LocalTime.java:465)
    //       at
    // com.meetingRoomBooking.utilities.DateTimeUtility.convertStringToLocalTime(DateTimeUtility.java:14)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange and Act
    DateTimeUtility.convertStringToLocalTime("Time String");
  }

  /** Method under test: {@link DateTimeUtility#convertLocalTimeToString(LocalTime)} */
  @Test
  void testConvertLocalTimeToString() {
    // Arrange, Act and Assert
    assertEquals("00:00", DateTimeUtility.convertLocalTimeToString(LocalTime.MIDNIGHT));
  }

  /** Method under test: {@link DateTimeUtility#convertStringToLocalDate(String)} */
  @Test
  void testConvertStringToLocalDate() {
    // Arrange, Act and Assert
    assertEquals("2020-03-01", DateTimeUtility.convertStringToLocalDate("2020-03-01").toString());
  }

  /** Method under test: {@link DateTimeUtility#convertLocalDateToString(LocalDate)} */
  @Test
  void testConvertLocalDateToString() {
    // Arrange, Act and Assert
    assertEquals("1970-01-01", DateTimeUtility.convertLocalDateToString(LocalDate.of(1970, 1, 1)));
  }
}
