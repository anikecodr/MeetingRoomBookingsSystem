package com.meetingRoomBooking.repostitories;

import com.meetingRoomBooking.dataobject.MeetingRoomBookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface MeetingRoomBookingsRepositories
    extends JpaRepository<MeetingRoomBookings, Integer> {

  @Query(
      value =
          "SELECT * FROM meeting_room_booking WHERE date BETWEEN CURDATE() and DATE_ADD(CURDATE(), INTERVAL :days DAY)",
      nativeQuery = true)
  public List<MeetingRoomBookings> findBookingsForNextdays(@Param("days") int days);

  @Query(
      value = "SELECT * FROM meeting_room_booking WHERE date >=CURDATE()",
      nativeQuery = true)
  public List<MeetingRoomBookings> findAllBookingsFromCurrentDate();

  @Query(
          value = "SELECT * FROM meeting_room_booking where emp_id = :empId",
          nativeQuery = true)
  public List<MeetingRoomBookings> findBookingsForEmployee(int empId);

  @Query(
          value = "SELECT * FROM meeting_room_booking WHERE date = :date",
          nativeQuery = true)
  public List<MeetingRoomBookings> findBookingsForDate(LocalDate date);

  @Query(
          value = "SELECT * FROM meeting_room_booking WHERE floor_num = :floorNumber and room_num = :roomNumber and date = :date",
          nativeQuery = true)
  public List<MeetingRoomBookings> findBookingsWithRoomDetailsAndDate(int floorNumber, int roomNumber, LocalDate date);

}