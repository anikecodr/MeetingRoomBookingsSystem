package com.meetingRoomBooking.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class MeetingRoomBookingException extends Exception {
    private String message;
    public MeetingRoomBookingException(String message) {
        this.message = message;
    }
}
