package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberInfoService;
import com.youth.meeting.application.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberInfoController {

    private final MemberInfoService organizerMemberJoinService;

    public MemberInfoController(MemberInfoService organizerMemberJoinService) {
        this.organizerMemberJoinService = organizerMemberJoinService;
    }

    @PostMapping("/members/{memberNo}")
    public ResponseEntity<MemberInfoResponse> findMemberInfo(
            @PathVariable("memberNo") Long memberNo
    ) {
        MemberInfoResponse response = organizerMemberJoinService.findOrganizerMember(memberNo);
        return ResponseEntity.ok(response);
    }
}
