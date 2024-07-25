package com.meetingRoomBooking.services.impl;

import com.meetingRoomBooking.context.ContextDetails;
import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import com.meetingRoomBooking.entities.*;
import com.meetingRoomBooking.repostitories.MeetingRoomBookingsRepositories;
import com.meetingRoomBooking.services.MeetingRoomService;
import com.meetingRoomBooking.utilities.DateTimeUtility;
import java.util.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MeetingRoomServiceImpl implements MeetingRoomService {

  HashMap<RoomDetails, Integer> roomDetails = new HashMap<>();
  @Autowired private MeetingRoomBookingsRepositories bookingsRepositories;

  @Autowired private MeetingRoomBookingReferences roomBookingReferences;

  @Autowired private ContextDetails ctx;

  @Override
  public MeetingRoomBookingReferences createRoomBooking(MeetingRoomBookingCreateDTO createDTO) {

    MeetingRoomBookings roomBookingsD0 = new MeetingRoomBookings();
    roomBookingsD0.setEmpId(createDTO.getEmpId());
    roomBookingsD0.setEmpName(createDTO.getEmpName());
    roomBookingsD0.setRoomNumber(createDTO.getRoomNumber());
    roomBookingsD0.setFloorNumber(createDTO.getFloorNumber());
    roomBookingsD0.setDate(DateTimeUtility.convertStringToLocalDate(createDTO.getDate()));
    roomBookingsD0.setStartTime(DateTimeUtility.convertStringToLocalTime(createDTO.getStartTime()));
    roomBookingsD0.setEndTime(DateTimeUtility.convertStringToLocalTime(createDTO.getEndTime()));

    MeetingRoomBookings data = bookingsRepositories.save(roomBookingsD0);
    roomBookingReferences.setBookingId(data.getBookingId());
    roomBookingReferences.setStatus("reserved");
    return roomBookingReferences;
  }

  public List<MeetingRoomBookings> getBookingHistoryInfo() {
    return bookingsRepositories.findBookingsForEmployee(ctx.getEmpId());
  }

  @Override
  public BookingInfo getBookedRooms() {

    BookingInfo bookingInfo = new BookingInfo();
    List<MeetingRoomBookings> roomBookings;
    if (ctx.getFloorNumber() != null && ctx.getRoomNumber() != null && ctx.getDate() != null) {
      roomBookings =
          bookingsRepositories.findBookingsWithRoomDetailsAndDate(
              ctx.getFloorNumber(), ctx.getRoomNumber(), ctx.getDate());
    } else if (ctx.getEmpId() != null) {
      roomBookings = bookingsRepositories.findBookingsForEmployee(ctx.getEmpId());
    } else if (ctx.getDate() != null) {
      roomBookings = bookingsRepositories.findBookingsForDate(ctx.getDate());
    } else if (ctx.getDays() != null) {
      roomBookings = bookingsRepositories.findBookingsForNextdays(ctx.getDays());
    } else {
      roomBookings = bookingsRepositories.findAllBookingsFromCurrentDate();
    }

    List<MeetingRoomBookingDTO> meetingRoomBookingDTOList = addBookingDetails(roomBookings);

    Map<String, BookedRoomInfo> mergedMap = new HashMap<>();

    for (MeetingRoomBookings booking : roomBookings) {
      String key =
          booking.getRoomNumber()
              + "-"
              + booking.getFloorNumber()
              + "-"
              + DateTimeUtility.convertLocalDateToString(booking.getDate());

      ReservedTimeSlot reservedTimeSlot =
          new ReservedTimeSlot(booking.getStartTime(), booking.getEndTime());

      if (mergedMap.containsKey(key)) {
        BookedRoomInfo bookedRoomInfo = mergedMap.get(key);
        bookedRoomInfo.getReservedTimeSlots().add(reservedTimeSlot);
      } else {
        BookedRoomInfo bookedRoomInfo = new BookedRoomInfo();
        bookedRoomInfo.setRoomNumber(booking.getRoomNumber());
        bookedRoomInfo.setFloorNumber(booking.getFloorNumber());
        bookedRoomInfo.setDate(booking.getDate());
        List<ReservedTimeSlot> reservedTimeSlots = new ArrayList<>();
        reservedTimeSlots.add(reservedTimeSlot);
        bookedRoomInfo.setReservedTimeSlots(reservedTimeSlots);
        mergedMap.put(key, bookedRoomInfo);
      }
    }

   // bookingInfo.setRoomBookingsList(roomBookings);
    bookingInfo.setBookedRoomInfoList(mergedMap.values().stream().toList());
    return bookingInfo;
  }

  private List<MeetingRoomBookingDTO> addBookingDetails(
      List<MeetingRoomBookings> meetingRoomBookings) {
    List<MeetingRoomBookingDTO> meetingRoomBookingDTOList = new ArrayList<>();

    for (MeetingRoomBookings roomBookings : meetingRoomBookings) {
      MeetingRoomBookingDTO meetingRoomBookingDTO = new MeetingRoomBookingDTO();
      meetingRoomBookingDTO.setBookingId(roomBookings.getBookingId());
      meetingRoomBookingDTO.setEmpName(roomBookings.getEmpName());
      meetingRoomBookingDTO.setRoomNumber(roomBookings.getRoomNumber());
      meetingRoomBookingDTO.setFloorNumber(roomBookings.getFloorNumber());
      meetingRoomBookingDTO.setDate(roomBookings.getDate());
      meetingRoomBookingDTOList.add(meetingRoomBookingDTO);
    }
    return meetingRoomBookingDTOList;
  }

  public MeetingRoomBookingReferences reserveSeatForBookingId(SeatConfirmDTO seatConfirmDTO) {

    MeetingRoomBookings roomBooking;
    try {
      roomBooking =
          bookingsRepositories
              .findById(seatConfirmDTO.getBookingId())
              .orElseThrow(() -> new Exception("Booking not found"));
    } catch (Exception ex) {
      log.error("Booking Id not found");
      return new MeetingRoomBookingReferences();
    }
    bookingsRepositories.save(roomBooking);
    return new MeetingRoomBookingReferences(
        seatConfirmDTO.getBookingId(), seatConfirmDTO.getReservationStatus());
  }

  @AllArgsConstructor
  private class RoomDetails {
    private Integer roomNumber;
    private Integer floorNumber;

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (!(o instanceof RoomDetails that)) return false;
      return Objects.equals(roomNumber, that.roomNumber)
          && Objects.equals(floorNumber, that.floorNumber);
    }

    @Override
    public int hashCode() {
      return Objects.hash(roomNumber, floorNumber);
    }
  }
}
