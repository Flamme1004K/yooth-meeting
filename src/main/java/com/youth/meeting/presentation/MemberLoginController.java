package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberLoginService;
import com.youth.meeting.authenticate.JsonWebTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberLoginController {

    private final MemberLoginService memberLoginService;

    public MemberLoginController(MemberLoginService memberLoginService) {
        this.memberLoginService = memberLoginService;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> loginMember(
            @RequestParam("loginId") String loginId,
            @RequestParam("password") String password
    ) {
        String token = memberLoginService.loginMember(loginId, password);
        return ResponseEntity.ok(token);
    }
}
