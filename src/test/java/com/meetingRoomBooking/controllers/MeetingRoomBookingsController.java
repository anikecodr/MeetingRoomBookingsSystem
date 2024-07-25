package com.meetingRoomBooking.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.meetingRoomBooking.context.ContextDetails;
import com.meetingRoomBooking.entities.BookingInfo;
import com.meetingRoomBooking.entities.MeetingRoomBookingCreateDTO;
import com.meetingRoomBooking.entities.MeetingRoomBookingReferences;
import com.meetingRoomBooking.entities.SeatConfirmDTO;
import com.meetingRoomBooking.services.MeetingRoomService;
import java.util.ArrayList;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {MeetingRoomBookingsController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class MeetingRoomBookingsControllerTest {
  @MockBean private ContextDetails contextDetails;

  @Autowired private MeetingRoomBookingsController meetingRoomBookingsController;

  @MockBean private MeetingRoomService meetingRoomService;

  @Test
  void testGetBookedRooms2() throws Exception {

    when(contextDetails.getEmpId()).thenReturn(null);

    BookingInfo bookingInfo = new BookingInfo();
    bookingInfo.setBookedRoomInfoList(new ArrayList<>());
    when(meetingRoomService.getBookedRooms()).thenReturn(bookingInfo);
    when(meetingRoomService.getBookingHistoryInfo()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/workspot/room/bookings");

    MockMvcBuilders.standaloneSetup(meetingRoomBookingsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(
            MockMvcResultMatchers.content()
                .string(
                    "{\"bookedRoomInfoList\":[],\"roomInfo\":[{\"roomNumber\":1,\"floorNumber\":5,\"capacity\":10},{\"roomNumber\":2,"
                        + "\"floorNumber\":5,\"capacity\":6},{\"roomNumber\":3,\"floorNumber\":5,\"capacity\":8},{\"roomNumber\":4,\"floorNumber"
                        + "\":5,\"capacity\":10},{\"roomNumber\":5,\"floorNumber\":5,\"capacity\":4},{\"roomNumber\":1,\"floorNumber\":6,"
                        + "\"capacity\":4},{\"roomNumber\":2,\"floorNumber\":6,\"capacity\":8},{\"roomNumber\":3,\"floorNumber\":6,\"capacity"
                        + "\":10},{\"roomNumber\":4,\"floorNumber\":6,\"capacity\":12},{\"roomNumber\":5,\"floorNumber\":6,\"capacity\":15},"
                        + "{\"roomNumber\":1,\"floorNumber\":10,\"capacity\":4},{\"roomNumber\":2,\"floorNumber\":10,\"capacity\":8},{"
                        + "\"roomNumber\":3,\"floorNumber\":10,\"capacity\":12},{\"roomNumber\":4,\"floorNumber\":10,\"capacity\":10},{"
                        + "\"roomNumber\":5,\"floorNumber\":10,\"capacity\":15}]}"));
  }

  @Test
  void testConfirmSeat() throws Exception {

    when(meetingRoomService.reserveSeatForBookingId(Mockito.<SeatConfirmDTO>any()))
        .thenReturn(new MeetingRoomBookingReferences());

    SeatConfirmDTO seatConfirmDTO = new SeatConfirmDTO();
    seatConfirmDTO.setBookingId(1);
    seatConfirmDTO.setReservationStatus("Reservation Status");
    String content = (new ObjectMapper()).writeValueAsString(seatConfirmDTO);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.put("/workspot/room/confirm-seat")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

    MockMvcBuilders.standaloneSetup(meetingRoomBookingsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("{\"bookingId\":null,\"status\":null}"));
  }

  @Test
  void testCreateRoomBooking() throws Exception {

    MeetingRoomBookingCreateDTO meetingRoomBookingCreateDTO = new MeetingRoomBookingCreateDTO();
    meetingRoomBookingCreateDTO.setDate("2020-03-01");
    meetingRoomBookingCreateDTO.setEmpId(1);
    meetingRoomBookingCreateDTO.setEmpName("Emp Name");
    meetingRoomBookingCreateDTO.setEndTime("End Time");
    meetingRoomBookingCreateDTO.setFloorNumber(10);
    meetingRoomBookingCreateDTO.setRoomNumber(10);
    meetingRoomBookingCreateDTO.setStartTime("Start Time");
    String content = (new ObjectMapper()).writeValueAsString(meetingRoomBookingCreateDTO);
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.post("/workspot/room/create-booking")
            .contentType(MediaType.APPLICATION_JSON)
            .content(content);

    ResultActions actualPerformResult =
        MockMvcBuilders.standaloneSetup(meetingRoomBookingsController)
            .build()
            .perform(requestBuilder);

    actualPerformResult.andExpect(MockMvcResultMatchers.status().is(400));
  }

  @Test
  void testGetBookedRooms() throws Exception {

    when(contextDetails.getEmpId()).thenReturn(1);
    when(meetingRoomService.getBookingHistoryInfo()).thenReturn(new ArrayList<>());
    MockHttpServletRequestBuilder requestBuilder =
        MockMvcRequestBuilders.get("/workspot/room/bookings");

    MockMvcBuilders.standaloneSetup(meetingRoomBookingsController)
        .build()
        .perform(requestBuilder)
        .andExpect(MockMvcResultMatchers.status().isOk())
        .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
        .andExpect(MockMvcResultMatchers.content().string("[]"));
  }

  @Test
  void testIsRoomAvailable() {

    assertEquals("roomAvailable", (new MeetingRoomBookingsController()).isRoomAvailable());
  }
}
