package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberInfoService;
import com.youth.meeting.application.dto.*;
import com.youth.meeting.authenticate.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberInfoController {

    private final MemberInfoService organizerMemberJoinService;
    private final JwtProvider jwtProvider;

    public MemberInfoController(MemberInfoService organizerMemberJoinService, JwtProvider jwtProvider) {
        this.organizerMemberJoinService = organizerMemberJoinService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/members/{memberNo}")
    public ResponseEntity<MemberInfoResponse> findMemberInfo(
            @PathVariable("memberNo") Long memberNo,
            @RequestHeader(value = "Authorization") String token
    ) {
        jwtProvider.parseJwtToken(token);
        MemberInfoResponse response = organizerMemberJoinService.findMember(memberNo);
        return ResponseEntity.ok(response);
    }
}
