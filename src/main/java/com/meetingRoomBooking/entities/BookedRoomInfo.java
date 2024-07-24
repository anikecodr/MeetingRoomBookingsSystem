package com.meetingRoomBooking.entities;

import lombok.Data;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.List;

@Data
public class BookedRoomInfo {

    private Integer floorNumber;
    private Integer roomNumber;
    private LocalDate date;

    List<ReservedTimeSlot> reservedTimeSlots;
}
