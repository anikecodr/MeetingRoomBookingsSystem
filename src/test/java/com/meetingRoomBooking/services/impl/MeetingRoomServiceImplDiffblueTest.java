package com.meetingRoomBooking.services.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.meetingRoomBooking.constants.RoomInfo;
import com.meetingRoomBooking.context.ContextDetails;
import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import com.meetingRoomBooking.entities.BookedRoomInfo;
import com.meetingRoomBooking.entities.BookingInfo;
import com.meetingRoomBooking.entities.MeetingRoomBookingCreateDTO;
import com.meetingRoomBooking.entities.MeetingRoomBookingReferences;
import com.meetingRoomBooking.entities.ReservedTimeSlot;
import com.meetingRoomBooking.entities.SeatConfirmDTO;
import com.meetingRoomBooking.repostitories.MeetingRoomBookingsRepositories;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {MeetingRoomServiceImpl.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@DisabledInAotMode
class MeetingRoomServiceImplDiffblueTest {
  @MockBean private ContextDetails contextDetails;

  @MockBean private MeetingRoomBookingReferences meetingRoomBookingReferences;

  @MockBean private MeetingRoomBookingsRepositories meetingRoomBookingsRepositories;

  @Autowired private MeetingRoomServiceImpl meetingRoomServiceImpl;

  /**
   * Method under test: {@link
   * MeetingRoomServiceImpl#createRoomBooking(MeetingRoomBookingCreateDTO)}
   */
  @Test
  @Disabled("TODO: Complete this test")
  void testCreateRoomBooking() {
    // TODO: Diffblue Cover was only able to create a partial test for this method:
    //   Reason: No inputs found that don't throw a trivial exception.
    //   Diffblue Cover tried to run the arrange/act section, but the method under
    //   test threw
    //   java.time.format.DateTimeParseException: Text 'Start Time' could not be parsed at index 0
    //       at
    // java.base/java.time.format.DateTimeFormatter.parseResolved0(DateTimeFormatter.java:2052)
    //       at java.base/java.time.format.DateTimeFormatter.parse(DateTimeFormatter.java:1954)
    //       at java.base/java.time.LocalTime.parse(LocalTime.java:465)
    //       at
    // com.meetingRoomBooking.utilities.DateTimeUtility.convertStringToLocalTime(DateTimeUtility.java:14)
    //       at
    // com.meetingRoomBooking.services.impl.MeetingRoomServiceImpl.createRoomBooking(MeetingRoomServiceImpl.java:35)
    //   See https://diff.blue/R013 to resolve this issue.

    // Arrange
    MeetingRoomBookingCreateDTO createDTO = new MeetingRoomBookingCreateDTO();
    createDTO.setDate("2020-03-01");
    createDTO.setEmpId(1);
    createDTO.setEmpName("Emp Name");
    createDTO.setEndTime("End Time");
    createDTO.setFloorNumber(10);
    createDTO.setRoomNumber(10);
    createDTO.setStartTime("Start Time");

    // Act
    meetingRoomServiceImpl.createRoomBooking(createDTO);
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookingHistoryInfo()} */
  @Test
  void testGetBookingHistoryInfo() {
    // Arrange
    ArrayList<MeetingRoomBookings> meetingRoomBookingsList = new ArrayList<>();
    when(meetingRoomBookingsRepositories.findBookingsForEmployee(anyInt()))
        .thenReturn(meetingRoomBookingsList);
    when(contextDetails.getEmpId()).thenReturn(1);

    // Act
    List<MeetingRoomBookings> actualBookingHistoryInfo =
        meetingRoomServiceImpl.getBookingHistoryInfo();

    // Assert
    verify(contextDetails).getEmpId();
    verify(meetingRoomBookingsRepositories).findBookingsForEmployee(eq(1));
    assertTrue(actualBookingHistoryInfo.isEmpty());
    assertSame(meetingRoomBookingsList, actualBookingHistoryInfo);
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookedRooms()} */
  @Test
  void testGetBookedRooms() {
    // Arrange
    when(meetingRoomBookingsRepositories.findBookingsWithRoomDetailsAndDate(
            anyInt(), anyInt(), Mockito.<LocalDate>any()))
        .thenReturn(new ArrayList<>());
    when(contextDetails.getFloorNumber()).thenReturn(10);
    when(contextDetails.getRoomNumber()).thenReturn(10);
    when(contextDetails.getDate()).thenReturn(LocalDate.of(1970, 1, 1));

    // Act
    BookingInfo actualBookedRooms = meetingRoomServiceImpl.getBookedRooms();

    // Assert
    verify(contextDetails, atLeast(1)).getDate();
    verify(contextDetails, atLeast(1)).getFloorNumber();
    verify(contextDetails, atLeast(1)).getRoomNumber();
    verify(meetingRoomBookingsRepositories)
        .findBookingsWithRoomDetailsAndDate(eq(10), eq(10), isA(LocalDate.class));
    List<RoomInfo> roomInfo = actualBookedRooms.getRoomInfo();
    assertEquals(15, roomInfo.size());
    RoomInfo getResult = roomInfo.get(0);
    assertEquals(1, getResult.getRoomNumber());
    assertEquals(10, getResult.getCapacity());
    RoomInfo getResult2 = roomInfo.get(13);
    assertEquals(10, getResult2.getCapacity());
    RoomInfo getResult3 = roomInfo.get(12);
    assertEquals(10, getResult3.getFloorNumber());
    assertEquals(10, getResult2.getFloorNumber());
    RoomInfo getResult4 = roomInfo.get(14);
    assertEquals(10, getResult4.getFloorNumber());
    assertEquals(12, getResult3.getCapacity());
    assertEquals(15, getResult4.getCapacity());
    RoomInfo getResult5 = roomInfo.get(1);
    assertEquals(2, getResult5.getRoomNumber());
    assertEquals(3, getResult3.getRoomNumber());
    RoomInfo getResult6 = roomInfo.get(2);
    assertEquals(3, getResult6.getRoomNumber());
    assertEquals(4, getResult2.getRoomNumber());
    assertEquals(5, getResult.getFloorNumber());
    assertEquals(5, getResult5.getFloorNumber());
    assertEquals(5, getResult6.getFloorNumber());
    assertEquals(5, getResult4.getRoomNumber());
    assertEquals(6, getResult5.getCapacity());
    assertEquals(8, getResult6.getCapacity());
    assertTrue(actualBookedRooms.getBookedRoomInfoList().isEmpty());
    assertTrue(meetingRoomServiceImpl.getBookingHistoryInfo().isEmpty());
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookedRooms()} */
  @Test
  void testGetBookedRooms2() {
    // Arrange
    MeetingRoomBookings meetingRoomBookings = new MeetingRoomBookings();
    meetingRoomBookings.setBookingId(1);
    LocalDate date = LocalDate.of(1970, 1, 1);
    meetingRoomBookings.setDate(date);
    meetingRoomBookings.setEmpId(1);
    meetingRoomBookings.setEmpName("Emp Name");
    meetingRoomBookings.setEndTime(LocalTime.MIDNIGHT);
    meetingRoomBookings.setFloorNumber(10);
    meetingRoomBookings.setRoomNumber(10);
    meetingRoomBookings.setStartTime(LocalTime.MIDNIGHT);

    ArrayList<MeetingRoomBookings> meetingRoomBookingsList = new ArrayList<>();
    meetingRoomBookingsList.add(meetingRoomBookings);
    when(meetingRoomBookingsRepositories.findBookingsWithRoomDetailsAndDate(
            anyInt(), anyInt(), Mockito.<LocalDate>any()))
        .thenReturn(meetingRoomBookingsList);
    when(contextDetails.getFloorNumber()).thenReturn(10);
    when(contextDetails.getRoomNumber()).thenReturn(10);
    when(contextDetails.getDate()).thenReturn(LocalDate.of(1970, 1, 1));

    // Act
    BookingInfo actualBookedRooms = meetingRoomServiceImpl.getBookedRooms();

    // Assert
    verify(contextDetails, atLeast(1)).getDate();
    verify(contextDetails, atLeast(1)).getFloorNumber();
    verify(contextDetails, atLeast(1)).getRoomNumber();
    verify(meetingRoomBookingsRepositories)
        .findBookingsWithRoomDetailsAndDate(eq(10), eq(10), isA(LocalDate.class));
    List<BookedRoomInfo> bookedRoomInfoList = actualBookedRooms.getBookedRoomInfoList();
    assertEquals(1, bookedRoomInfoList.size());
    BookedRoomInfo getResult = bookedRoomInfoList.get(0);
    List<ReservedTimeSlot> reservedTimeSlots = getResult.getReservedTimeSlots();
    assertEquals(1, reservedTimeSlots.size());
    ReservedTimeSlot getResult2 = reservedTimeSlots.get(0);
    LocalTime endTime = getResult2.getEndTime();
    assertEquals("00:00", endTime.toString());
    LocalDate date2 = getResult.getDate();
    assertEquals("1970-01-01", date2.toString());
    List<RoomInfo> roomInfo = actualBookedRooms.getRoomInfo();
    assertEquals(15, roomInfo.size());
    RoomInfo getResult3 = roomInfo.get(0);
    assertEquals(1, getResult3.getRoomNumber());
    assertEquals(10, getResult3.getCapacity());
    RoomInfo getResult4 = roomInfo.get(13);
    assertEquals(10, getResult4.getCapacity());
    RoomInfo getResult5 = roomInfo.get(12);
    assertEquals(10, getResult5.getFloorNumber());
    assertEquals(10, getResult4.getFloorNumber());
    RoomInfo getResult6 = roomInfo.get(14);
    assertEquals(10, getResult6.getFloorNumber());
    assertEquals(10, getResult.getFloorNumber().intValue());
    assertEquals(10, getResult.getRoomNumber().intValue());
    assertEquals(12, getResult5.getCapacity());
    assertEquals(15, getResult6.getCapacity());
    RoomInfo getResult7 = roomInfo.get(1);
    assertEquals(2, getResult7.getRoomNumber());
    assertEquals(3, getResult5.getRoomNumber());
    RoomInfo getResult8 = roomInfo.get(2);
    assertEquals(3, getResult8.getRoomNumber());
    assertEquals(4, getResult4.getRoomNumber());
    assertEquals(5, getResult3.getFloorNumber());
    assertEquals(5, getResult7.getFloorNumber());
    assertEquals(5, getResult8.getFloorNumber());
    assertEquals(5, getResult6.getRoomNumber());
    assertEquals(6, getResult7.getCapacity());
    assertEquals(8, getResult8.getCapacity());
    assertTrue(meetingRoomServiceImpl.getBookingHistoryInfo().isEmpty());
    assertSame(endTime, getResult2.getStartTime());
    assertSame(date, date2);
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookedRooms()} */
  @Test
  void testGetBookedRooms3() {
    // Arrange
    when(meetingRoomBookingsRepositories.findBookingsForEmployee(anyInt()))
        .thenReturn(new ArrayList<>());
    when(contextDetails.getEmpId()).thenReturn(1);
    when(contextDetails.getFloorNumber()).thenReturn(null);

    // Act
    BookingInfo actualBookedRooms = meetingRoomServiceImpl.getBookedRooms();

    // Assert
    verify(contextDetails, atLeast(1)).getEmpId();
    verify(contextDetails).getFloorNumber();
    verify(meetingRoomBookingsRepositories).findBookingsForEmployee(eq(1));
    List<RoomInfo> roomInfo = actualBookedRooms.getRoomInfo();
    assertEquals(15, roomInfo.size());
    RoomInfo getResult = roomInfo.get(0);
    assertEquals(1, getResult.getRoomNumber());
    assertEquals(10, getResult.getCapacity());
    RoomInfo getResult2 = roomInfo.get(13);
    assertEquals(10, getResult2.getCapacity());
    RoomInfo getResult3 = roomInfo.get(12);
    assertEquals(10, getResult3.getFloorNumber());
    assertEquals(10, getResult2.getFloorNumber());
    RoomInfo getResult4 = roomInfo.get(14);
    assertEquals(10, getResult4.getFloorNumber());
    assertEquals(12, getResult3.getCapacity());
    assertEquals(15, getResult4.getCapacity());
    RoomInfo getResult5 = roomInfo.get(1);
    assertEquals(2, getResult5.getRoomNumber());
    assertEquals(3, getResult3.getRoomNumber());
    RoomInfo getResult6 = roomInfo.get(2);
    assertEquals(3, getResult6.getRoomNumber());
    assertEquals(4, getResult2.getRoomNumber());
    assertEquals(5, getResult.getFloorNumber());
    assertEquals(5, getResult5.getFloorNumber());
    assertEquals(5, getResult6.getFloorNumber());
    assertEquals(5, getResult4.getRoomNumber());
    assertEquals(6, getResult5.getCapacity());
    assertEquals(8, getResult6.getCapacity());
    assertTrue(actualBookedRooms.getBookedRoomInfoList().isEmpty());
    assertTrue(meetingRoomServiceImpl.getBookingHistoryInfo().isEmpty());
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookedRooms()} */
  @Test
  void testGetBookedRooms4() {
    // Arrange
    when(meetingRoomBookingsRepositories.findBookingsForEmployee(anyInt()))
        .thenReturn(new ArrayList<>());
    when(contextDetails.getEmpId()).thenReturn(1);
    when(contextDetails.getFloorNumber()).thenReturn(10);
    when(contextDetails.getRoomNumber()).thenReturn(null);

    // Act
    BookingInfo actualBookedRooms = meetingRoomServiceImpl.getBookedRooms();

    // Assert
    verify(contextDetails, atLeast(1)).getEmpId();
    verify(contextDetails).getFloorNumber();
    verify(contextDetails).getRoomNumber();
    verify(meetingRoomBookingsRepositories).findBookingsForEmployee(eq(1));
    List<RoomInfo> roomInfo = actualBookedRooms.getRoomInfo();
    assertEquals(15, roomInfo.size());
    RoomInfo getResult = roomInfo.get(0);
    assertEquals(1, getResult.getRoomNumber());
    assertEquals(10, getResult.getCapacity());
    RoomInfo getResult2 = roomInfo.get(13);
    assertEquals(10, getResult2.getCapacity());
    RoomInfo getResult3 = roomInfo.get(12);
    assertEquals(10, getResult3.getFloorNumber());
    assertEquals(10, getResult2.getFloorNumber());
    RoomInfo getResult4 = roomInfo.get(14);
    assertEquals(10, getResult4.getFloorNumber());
    assertEquals(12, getResult3.getCapacity());
    assertEquals(15, getResult4.getCapacity());
    RoomInfo getResult5 = roomInfo.get(1);
    assertEquals(2, getResult5.getRoomNumber());
    assertEquals(3, getResult3.getRoomNumber());
    RoomInfo getResult6 = roomInfo.get(2);
    assertEquals(3, getResult6.getRoomNumber());
    assertEquals(4, getResult2.getRoomNumber());
    assertEquals(5, getResult.getFloorNumber());
    assertEquals(5, getResult5.getFloorNumber());
    assertEquals(5, getResult6.getFloorNumber());
    assertEquals(5, getResult4.getRoomNumber());
    assertEquals(6, getResult5.getCapacity());
    assertEquals(8, getResult6.getCapacity());
    assertTrue(actualBookedRooms.getBookedRoomInfoList().isEmpty());
    assertTrue(meetingRoomServiceImpl.getBookingHistoryInfo().isEmpty());
  }

  /** Method under test: {@link MeetingRoomServiceImpl#getBookedRooms()} */
  @Test
  void testGetBookedRooms5() {
    // Arrange
    when(meetingRoomBookingsRepositories.findBookingsForEmployee(anyInt()))
        .thenReturn(new ArrayList<>());
    when(contextDetails.getEmpId()).thenReturn(1);
    when(contextDetails.getFloorNumber()).thenReturn(10);
    when(contextDetails.getRoomNumber()).thenReturn(10);
    when(contextDetails.getDate()).thenReturn(null);

    // Act
    BookingInfo actualBookedRooms = meetingRoomServiceImpl.getBookedRooms();

    // Assert
    verify(contextDetails).getDate();
    verify(contextDetails, atLeast(1)).getEmpId();
    verify(contextDetails).getFloorNumber();
    verify(contextDetails).getRoomNumber();
    verify(meetingRoomBookingsRepositories).findBookingsForEmployee(eq(1));
    List<RoomInfo> roomInfo = actualBookedRooms.getRoomInfo();
    assertEquals(15, roomInfo.size());
    RoomInfo getResult = roomInfo.get(0);
    assertEquals(1, getResult.getRoomNumber());
    assertEquals(10, getResult.getCapacity());
    RoomInfo getResult2 = roomInfo.get(13);
    assertEquals(10, getResult2.getCapacity());
    RoomInfo getResult3 = roomInfo.get(12);
    assertEquals(10, getResult3.getFloorNumber());
    assertEquals(10, getResult2.getFloorNumber());
    RoomInfo getResult4 = roomInfo.get(14);
    assertEquals(10, getResult4.getFloorNumber());
    assertEquals(12, getResult3.getCapacity());
    assertEquals(15, getResult4.getCapacity());
    RoomInfo getResult5 = roomInfo.get(1);
    assertEquals(2, getResult5.getRoomNumber());
    assertEquals(3, getResult3.getRoomNumber());
    RoomInfo getResult6 = roomInfo.get(2);
    assertEquals(3, getResult6.getRoomNumber());
    assertEquals(4, getResult2.getRoomNumber());
    assertEquals(5, getResult.getFloorNumber());
    assertEquals(5, getResult5.getFloorNumber());
    assertEquals(5, getResult6.getFloorNumber());
    assertEquals(5, getResult4.getRoomNumber());
    assertEquals(6, getResult5.getCapacity());
    assertEquals(8, getResult6.getCapacity());
    assertTrue(actualBookedRooms.getBookedRoomInfoList().isEmpty());
    assertTrue(meetingRoomServiceImpl.getBookingHistoryInfo().isEmpty());
  }

  /** Method under test: {@link MeetingRoomServiceImpl#reserveSeatForBookingId(SeatConfirmDTO)} */
  @Test
  void testReserveSeatForBookingId() {
    // Arrange
    MeetingRoomBookings meetingRoomBookings = new MeetingRoomBookings();
    meetingRoomBookings.setBookingId(1);
    meetingRoomBookings.setDate(LocalDate.of(1970, 1, 1));
    meetingRoomBookings.setEmpId(1);
    meetingRoomBookings.setEmpName("Emp Name");
    meetingRoomBookings.setEndTime(LocalTime.MIDNIGHT);
    meetingRoomBookings.setFloorNumber(10);
    meetingRoomBookings.setRoomNumber(10);
    meetingRoomBookings.setStartTime(LocalTime.MIDNIGHT);
    Optional<MeetingRoomBookings> ofResult = Optional.of(meetingRoomBookings);

    MeetingRoomBookings meetingRoomBookings2 = new MeetingRoomBookings();
    meetingRoomBookings2.setBookingId(1);
    meetingRoomBookings2.setDate(LocalDate.of(1970, 1, 1));
    meetingRoomBookings2.setEmpId(1);
    meetingRoomBookings2.setEmpName("Emp Name");
    meetingRoomBookings2.setEndTime(LocalTime.MIDNIGHT);
    meetingRoomBookings2.setFloorNumber(10);
    meetingRoomBookings2.setRoomNumber(10);
    meetingRoomBookings2.setStartTime(LocalTime.MIDNIGHT);
    when(meetingRoomBookingsRepositories.save(Mockito.<MeetingRoomBookings>any()))
        .thenReturn(meetingRoomBookings2);
    when(meetingRoomBookingsRepositories.findById(Mockito.<Integer>any())).thenReturn(ofResult);

    SeatConfirmDTO seatConfirmDTO = new SeatConfirmDTO();
    seatConfirmDTO.setBookingId(1);
    seatConfirmDTO.setReservationStatus("Reservation Status");

    // Act
    MeetingRoomBookingReferences actualReserveSeatForBookingIdResult =
        meetingRoomServiceImpl.reserveSeatForBookingId(seatConfirmDTO);

    // Assert
    verify(meetingRoomBookingsRepositories).findById(eq(1));
    verify(meetingRoomBookingsRepositories).save(isA(MeetingRoomBookings.class));
    assertEquals("Reservation Status", actualReserveSeatForBookingIdResult.getStatus());
    assertEquals(1, actualReserveSeatForBookingIdResult.getBookingId().intValue());
  }

  /** Method under test: {@link MeetingRoomServiceImpl#reserveSeatForBookingId(SeatConfirmDTO)} */
  @Test
  void testReserveSeatForBookingId2() {
    // Arrange
    Optional<MeetingRoomBookings> emptyResult = Optional.empty();
    when(meetingRoomBookingsRepositories.findById(Mockito.<Integer>any())).thenReturn(emptyResult);

    SeatConfirmDTO seatConfirmDTO = new SeatConfirmDTO();
    seatConfirmDTO.setBookingId(1);
    seatConfirmDTO.setReservationStatus("Reservation Status");

    // Act
    MeetingRoomBookingReferences actualReserveSeatForBookingIdResult =
        meetingRoomServiceImpl.reserveSeatForBookingId(seatConfirmDTO);

    // Assert
    verify(meetingRoomBookingsRepositories).findById(eq(1));
    assertNull(actualReserveSeatForBookingIdResult.getBookingId());
    assertNull(actualReserveSeatForBookingIdResult.getStatus());
  }
}
