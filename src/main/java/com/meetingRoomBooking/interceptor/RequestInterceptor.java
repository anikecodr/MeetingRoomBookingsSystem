package com.meetingRoomBooking.interceptor;

import com.meetingRoomBooking.context.ContextDetails;
import com.meetingRoomBooking.utilities.DateTimeUtility;
import com.meetingRoomBooking.utilities.DateValidator;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.time.LocalDate;
import java.util.Objects;
import java.util.logging.Logger;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class RequestInterceptor implements HandlerInterceptor {

  @Autowired private ContextDetails ctx;

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    log.info("i'm here");
    if(this.isHealthEndPoint(request)) return true;

    String empyId = request.getParameter("empId");
    String floorNumber = request.getParameter("floorNumber");
    String roomNumber = request.getParameter("roomNumber");
    String date = request.getParameter("date");
    String days = request.getParameter("days");

    if (Objects.isNull(floorNumber) && Objects.nonNull(roomNumber)
        || Objects.isNull(roomNumber) && Objects.nonNull(floorNumber)) {
      log.error("raise exception");
      return false;
    }
    if(Objects.nonNull(date) && !DateValidator.isValidDate(date)) {
      log.error("date is improper format");
      return false;
    }

    this.ctx.setEmpId(Objects.isNull(empyId) ? null : Integer.parseInt(empyId));
    this.ctx.setFloorNumber(Objects.isNull(floorNumber) ? null : Integer.parseInt(floorNumber));
    this.ctx.setRoomNumber(Objects.isNull(roomNumber) ? null : Integer.parseInt(roomNumber));
    this.ctx.setDate(Objects.isNull(date) ? null : DateTimeUtility.convertStringToLocalDate(date));
    this.ctx.setDays(Objects.isNull(days) ? null : Integer.parseInt(days));
    return true;
  }

  protected boolean isHealthEndPoint(HttpServletRequest requestContext) {
    return requestContext.getRequestURI().contains("health");
  }
}
