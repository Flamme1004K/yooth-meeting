package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberInfoService;
import com.youth.meeting.application.dto.*;
import com.youth.meeting.authenticate.JsonWebTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberInfoController {

    private final MemberInfoService organizerMemberJoinService;
    public MemberInfoController(MemberInfoService organizerMemberJoinService) {
        this.organizerMemberJoinService = organizerMemberJoinService;
    }

    @GetMapping("/members/{memberNo}")
    public ResponseEntity<MemberInfoResponse> findMemberInfo(
            @PathVariable("memberNo") Long memberNo
    ) {
        MemberInfoResponse response = organizerMemberJoinService.findMember(memberNo);
        return ResponseEntity.ok(response);
    }
}
