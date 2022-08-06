package com.youth.meeting.authenticate;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class JsonWebTokenInterceptor implements HandlerInterceptor {
    private final JsonWebTokenProvider jsonWebTokenProvider;

    public JsonWebTokenInterceptor(JsonWebTokenProvider jsonWebTokenProvider) {
        this.jsonWebTokenProvider = jsonWebTokenProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("Authorization");
        jsonWebTokenProvider.parseJwtToken(token);
        return HandlerInterceptor.super.preHandle(request, response, handler);
    }
}
