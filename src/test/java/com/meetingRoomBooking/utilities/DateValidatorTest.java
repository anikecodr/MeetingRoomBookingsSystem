package com.meetingRoomBooking.utilities;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

class DateValidatorTest {
  /** Method under test: {@link DateValidator#isValidDate(String)} */
  @Test
  void testIsValidDate() {
    // Arrange, Act and Assert
    assertTrue(DateValidator.isValidDate("2020-03-01"));
    assertFalse(DateValidator.isValidDate("2020/03/01"));
  }
}
