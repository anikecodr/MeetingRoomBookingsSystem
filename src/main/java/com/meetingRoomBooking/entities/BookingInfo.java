package com.meetingRoomBooking.entities;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.meetingRoomBooking.constants.RoomInfo;
import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class BookingInfo {
    private static List<RoomInfo> roomInfo = new ArrayList<>();

    static {
        for(RoomInfo roomInfo1: RoomInfo.RoomConstants.FLOOR_5_ROOMS) {
            roomInfo.add(roomInfo1);
        }
        for(RoomInfo roomInfo1: RoomInfo.RoomConstants.FLOOR_6_ROOMS) {
            roomInfo.add(roomInfo1);
        }
        for(RoomInfo roomInfo1: RoomInfo.RoomConstants.FLOOR_10_ROOMS) {
            roomInfo.add(roomInfo1);
        }
    }

    private List<BookedRoomInfo> bookedRoomInfoList;
    private List<MeetingRoomBookings> roomBookingsList;

    @JsonGetter("roomInfo")
    public List<RoomInfo> getRoomInfo() {
        return roomInfo;
    }

}
