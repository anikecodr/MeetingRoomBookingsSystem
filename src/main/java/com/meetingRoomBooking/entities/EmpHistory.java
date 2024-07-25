package com.meetingRoomBooking.entities;

import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import jdk.dynalink.linker.LinkerServices;
import lombok.Data;

import java.util.List;

@Data
public class EmpHistory {
    List<MeetingRoomBookings> roomBookingsList;
}