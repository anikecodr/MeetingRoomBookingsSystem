package com.meetingRoomBooking.interceptor;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import com.meetingRoomBooking.context.ContextDetails;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import org.apache.catalina.connector.Response;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@ContextConfiguration(classes = {RequestInterceptor.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class RequestInterceptorDiffblueTest {
  @MockBean private ContextDetails contextDetails;

  @Autowired private RequestInterceptor requestInterceptor;

  /**
   * Method under test: {@link RequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse,
   * Object)}
   */
  @Test
  void testPreHandle() throws Exception {
    // Arrange
    doNothing().when(contextDetails).setDate(Mockito.<LocalDate>any());
    doNothing().when(contextDetails).setDays(Mockito.<Integer>any());
    doNothing().when(contextDetails).setEmpId(Mockito.<Integer>any());
    doNothing().when(contextDetails).setFloorNumber(Mockito.<Integer>any());
    doNothing().when(contextDetails).setRoomNumber(Mockito.<Integer>any());
    MockHttpServletRequest request = new MockHttpServletRequest();

    // Act
    boolean actualPreHandleResult =
        requestInterceptor.preHandle(request, new Response(), "Handler");

    // Assert
    verify(contextDetails).setDate(isNull());
    verify(contextDetails).setDays(isNull());
    verify(contextDetails).setEmpId(isNull());
    verify(contextDetails).setFloorNumber(isNull());
    verify(contextDetails).setRoomNumber(isNull());
    assertTrue(actualPreHandleResult);
  }

  /**
   * Method under test: {@link RequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse,
   * Object)}
   */
  @Test
  void testPreHandle2() throws Exception {
    // Arrange
    HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
    when(request.getParameter(Mockito.<String>any())).thenReturn("Parameter");
    when(request.getRequestURI()).thenReturn("https://example.org/example");

    // Act
    boolean actualPreHandleResult =
        requestInterceptor.preHandle(request, new Response(), "Handler");

    // Assert
    verify(request, atLeast(1)).getParameter(Mockito.<String>any());
    verify(request).getRequestURI();
    assertFalse(actualPreHandleResult);
  }

  /**
   * Method under test: {@link RequestInterceptor#preHandle(HttpServletRequest, HttpServletResponse,
   * Object)}
   */
  @Test
  void testPreHandle3() throws Exception {
    // Arrange
    HttpServletRequestWrapper request = mock(HttpServletRequestWrapper.class);
    when(request.getRequestURI()).thenReturn("health");

    // Act
    boolean actualPreHandleResult =
        requestInterceptor.preHandle(request, new Response(), "Handler");

    // Assert
    verify(request).getRequestURI();
    assertTrue(actualPreHandleResult);
  }

  /** Method under test: {@link RequestInterceptor#isHealthEndPoint(HttpServletRequest)} */
  @Test
  void testIsHealthEndPoint() {
    // Arrange, Act and Assert
    assertFalse(requestInterceptor.isHealthEndPoint(new MockHttpServletRequest()));
  }

  /** Method under test: {@link RequestInterceptor#isHealthEndPoint(HttpServletRequest)} */
  @Test
  void testIsHealthEndPoint2() {
    // Arrange
    HttpServletRequestWrapper requestContext = mock(HttpServletRequestWrapper.class);
    when(requestContext.getRequestURI()).thenReturn("https://example.org/example");

    // Act
    boolean actualIsHealthEndPointResult = requestInterceptor.isHealthEndPoint(requestContext);

    // Assert
    verify(requestContext).getRequestURI();
    assertFalse(actualIsHealthEndPointResult);
  }

  /** Method under test: {@link RequestInterceptor#isHealthEndPoint(HttpServletRequest)} */
  @Test
  void testIsHealthEndPoint3() {
    // Arrange
    HttpServletRequestWrapper requestContext = mock(HttpServletRequestWrapper.class);
    when(requestContext.getRequestURI()).thenReturn("health");

    // Act
    boolean actualIsHealthEndPointResult = requestInterceptor.isHealthEndPoint(requestContext);

    // Assert
    verify(requestContext).getRequestURI();
    assertTrue(actualIsHealthEndPointResult);
  }
}
