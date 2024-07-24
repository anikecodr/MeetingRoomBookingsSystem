package com.meetingRoomBooking.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class MeetingRoomBookingDTO {

    private Integer bookingId;

    private Integer empId;

    private String empName;

    private Integer roomNumber;

    private Integer floorNumber;

    private LocalDate date;

    private List<ReservedTimeSlot> reservedTimeSlots;
}
