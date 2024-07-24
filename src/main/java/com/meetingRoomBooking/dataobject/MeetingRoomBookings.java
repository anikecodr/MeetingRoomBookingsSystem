package com.meetingRoomBooking.dataobject;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Collection;

@Entity
@Data
@Table(name = "meeting_room_booking_info")
public class MeetingRoomBookings {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "booking_id")
    private Integer bookingId;

    @Column(name = "emp_id", nullable = false)
    private Integer empId;

    @Column(name = "emp_name", nullable = false)
    private String empName;

    @Column(name = "room_num", nullable = false)
    private Integer roomNumber;

    @Column(name = "floor_num", nullable = false)
    private Integer floorNumber;

    @Column(name = "date", nullable = false)
    @Temporal(TemporalType.DATE)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @Column(name = "reservation_status")
    private String reservationStatus;
}
