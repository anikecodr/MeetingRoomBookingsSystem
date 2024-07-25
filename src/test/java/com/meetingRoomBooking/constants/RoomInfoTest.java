package com.meetingRoomBooking.constants;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

class RoomInfoTest {

  @Test
  void testGettersAndSetters() {

    RoomInfo actualRoomInfo = new RoomInfo(10, 10, 3);
    int actualCapacity = actualRoomInfo.getCapacity();
    int actualFloorNumber = actualRoomInfo.getFloorNumber();

    assertEquals(10, actualFloorNumber);
    assertEquals(10, actualRoomInfo.getRoomNumber());
    assertEquals(3, actualCapacity);
  }
}
