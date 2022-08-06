package com.youth.meeting.presentation;

import com.youth.meeting.authenticate.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class MemberLoginController {

    private final JwtProvider jwtProvider;

    public MemberLoginController(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @GetMapping(value = "/Login/{userId}")
    public ResponseEntity<String> loginMember(@PathVariable("userId") String userId) {
        return ResponseEntity.ok(jwtProvider.createToken(userId));
    }
}
