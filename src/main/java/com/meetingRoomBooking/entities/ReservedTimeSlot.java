package com.meetingRoomBooking.entities;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalTime;

@Data
@AllArgsConstructor
public class ReservedTimeSlot {

    private LocalTime startTime;
    private LocalTime endTime;
}
