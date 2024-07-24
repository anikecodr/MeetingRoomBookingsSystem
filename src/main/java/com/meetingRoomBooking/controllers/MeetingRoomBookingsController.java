package com.meetingRoomBooking.controllers;

import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import com.meetingRoomBooking.entities.MeetingRoomBookingCreateDTO;
import com.meetingRoomBooking.entities.MeetingRoomBookingReferences;
import com.meetingRoomBooking.entities.SeatConfirmDTO;
import com.meetingRoomBooking.repostitories.MeetingRoomBookingsRepositories;
import com.meetingRoomBooking.services.MeetingRoomService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/room")
public class MeetingRoomBookingsController {

  @Autowired private MeetingRoomService meetingRoomService;

  @GetMapping("/bookings")
  public ResponseEntity<?> getBookedRooms() {
    return new ResponseEntity<>(meetingRoomService.getBookedRooms(), HttpStatus.OK);
  }

  public String isRoomAvailable() {
    return "roomAvailable";
  }

  @PostMapping("/create-booking")
  public ResponseEntity<?> createRoomBooking(
      @Valid @RequestBody MeetingRoomBookingCreateDTO bookingCreateDTO) {

    return new ResponseEntity<>(
        meetingRoomService.createRoomBooking(bookingCreateDTO), HttpStatus.CREATED);
  }

  @PutMapping("/confirm-seat")
  public ResponseEntity<MeetingRoomBookingReferences> confirmSeat(@Valid @RequestBody SeatConfirmDTO seatConfirmDTO) {
   return new ResponseEntity<>(meetingRoomService.reserveSeatForBookingId(seatConfirmDTO), HttpStatus.OK);
  }
}
