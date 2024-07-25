package com.meetingRoomBooking.configs;

import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.meetingRoomBooking.interceptor.RequestInterceptor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

@ContextConfiguration(classes = {WebConfig.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class WebConfigTest {
  @MockBean private RequestInterceptor requestInterceptor;

  @Autowired private WebConfig webConfig;

  /** Method under test: {@link WebConfig#addInterceptors(InterceptorRegistry)} */
  @Test
  void testAddInterceptors() {

    InterceptorRegistry registry = mock(InterceptorRegistry.class);
    when(registry.addInterceptor(Mockito.<HandlerInterceptor>any()))
        .thenReturn(new InterceptorRegistration(new RequestInterceptor()));

    webConfig.addInterceptors(registry);

    verify(registry).addInterceptor(isA(HandlerInterceptor.class));
  }
}
