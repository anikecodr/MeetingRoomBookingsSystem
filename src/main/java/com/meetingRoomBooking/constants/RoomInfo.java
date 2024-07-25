package com.meetingRoomBooking.constants;

public class RoomInfo {
  private int roomNumber;
  private int floorNumber;
  private int capacity;

  public RoomInfo(int roomNumber, int floorNumber, int capacity) {
    this.roomNumber = roomNumber;
    this.floorNumber = floorNumber;
    this.capacity = capacity;
  }

  // Getters
  public int getRoomNumber() {
    return roomNumber;
  }

  public int getFloorNumber() {
    return floorNumber;
  }

  public int getCapacity() {
    return capacity;
  }

  public static class RoomConstants {
    public static final RoomInfo[] FLOOR_6_ROOMS = {
      new RoomInfo(1, 6, 4),
      new RoomInfo(2, 6, 8),
      new RoomInfo(3, 6, 10),
      new RoomInfo(4, 6, 12),
      new RoomInfo(5, 6, 15)
    };

    public static final RoomInfo[] FLOOR_5_ROOMS = {
      new RoomInfo(1, 5, 10),
      new RoomInfo(2, 5, 6),
      new RoomInfo(3, 5, 8),
      new RoomInfo(4, 5, 10),
      new RoomInfo(5, 5, 4)
    };

    public static final RoomInfo[] FLOOR_10_ROOMS = {
      new RoomInfo(1, 10, 4),
      new RoomInfo(2, 10, 8),
      new RoomInfo(3, 10, 12),
      new RoomInfo(4, 10, 10),
      new RoomInfo(5, 10, 15)
    };
  }
}