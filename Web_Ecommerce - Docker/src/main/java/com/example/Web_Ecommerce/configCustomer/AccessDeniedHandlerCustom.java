package com.example.Web_Ecommerce.configCustomer;


import com.example.Web_Ecommerce.exception.ErrorMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AccessDeniedHandlerCustom implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
        ErrorMessage message = new ErrorMessage(HttpStatus.FORBIDDEN, "Bạn không có quyền");

        ObjectMapper objectMapper = new ObjectMapper();
        String messageJSON = objectMapper.writeValueAsString(message);

        response.addHeader("Content-type", "application/json");
//        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.setCharacterEncoding("UTF-8");
        response.sendError(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write(messageJSON);
    }
}
