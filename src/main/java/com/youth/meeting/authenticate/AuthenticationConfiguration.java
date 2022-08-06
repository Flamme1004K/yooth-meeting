package com.youth.meeting.authenticate;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AuthenticationConfiguration implements WebMvcConfigurer {
    private final JsonWebTokenProvider jwtTokenProvider;

    public AuthenticationConfiguration(JsonWebTokenProvider jwtTokenProvider) {
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new JsonWebTokenInterceptor(jwtTokenProvider)).excludePathPatterns("/login", "/join-organizers", "/join-participants");
    }
}
