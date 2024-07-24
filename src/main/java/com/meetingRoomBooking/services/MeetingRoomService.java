package com.meetingRoomBooking.services;

import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import com.meetingRoomBooking.entities.BookingInfo;
import com.meetingRoomBooking.entities.MeetingRoomBookingCreateDTO;
import com.meetingRoomBooking.entities.MeetingRoomBookingReferences;
import com.meetingRoomBooking.entities.SeatConfirmDTO;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface MeetingRoomService {
    MeetingRoomBookingReferences createRoomBooking(MeetingRoomBookingCreateDTO createDTO);

    BookingInfo getBookedRooms();

    MeetingRoomBookingReferences reserveSeatForBookingId(SeatConfirmDTO seatConfirmDTO);
}
