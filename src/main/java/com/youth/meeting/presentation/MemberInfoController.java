package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberInfoService;
import com.youth.meeting.application.dto.*;
import com.youth.meeting.authenticate.JsonWebTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberInfoController {

    private final MemberInfoService organizerMemberJoinService;
    private final JsonWebTokenProvider jsonWebTokenProvider;

    public MemberInfoController(MemberInfoService organizerMemberJoinService, JsonWebTokenProvider jsonWebTokenProvider) {
        this.organizerMemberJoinService = organizerMemberJoinService;
        this.jsonWebTokenProvider = jsonWebTokenProvider;
    }

    @PostMapping("/members/{memberNo}")
    public ResponseEntity<MemberInfoResponse> findMemberInfo(
            @PathVariable("memberNo") Long memberNo,
            @RequestHeader(value = "Authorization") String token
    ) {
        jsonWebTokenProvider.parseJwtToken(token);
        MemberInfoResponse response = organizerMemberJoinService.findMember(memberNo);
        return ResponseEntity.ok(response);
    }
}
