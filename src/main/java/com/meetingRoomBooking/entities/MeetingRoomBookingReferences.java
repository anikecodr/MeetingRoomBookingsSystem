package com.meetingRoomBooking.entities;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class MeetingRoomBookingReferences {
    private Integer bookingId;
    private String status;
}