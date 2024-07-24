package com.meetingRoomBooking.context;


import lombok.Data;
import org.springframework.cglib.core.Local;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Scope(value = "request", proxyMode = ScopedProxyMode.TARGET_CLASS)
@Data
public class ContextDetails {

    private Integer empId;
    private Integer floorNumber;
    private Integer roomNumber;
    private LocalDate date;
    private Integer days;
}
