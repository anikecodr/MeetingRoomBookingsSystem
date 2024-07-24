package com.meetingRoomBooking.entities;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class MeetingRoomBookingCreateDTO {

  @NotNull(message = "Missing empyId")
  private Integer empId;

  @NotNull(message = "missing empName")
  private String empName;

  @NotNull(message = "missing roomNumber")
  private Integer roomNumber;

  @NotNull(message = "missing floorNumber")
  private Integer floorNumber;

  @NotNull(message = "missing date")
  @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$", message = "date should be 2024-07-25 format")
  private String date;

  @NotNull(message = "missing startDate")
  @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "start time should be in 12:45 format")
  private String startTime;

  @NotNull(message = "missing endTime")
  @Pattern(regexp = "^([01]\\d|2[0-3]):([0-5]\\d)$", message = "start time should be in 12:45 format")
  private String endTime;

  @NotNull(message = "missing Reservation Status")
  private String reservationStatus;
}
