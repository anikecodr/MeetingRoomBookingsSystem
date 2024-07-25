package com.meetingRoomBooking.entities;

import lombok.Data;

@Data
public class SeatConfirmDTO {

    private Integer bookingId;
    private String reservationStatus;
}
