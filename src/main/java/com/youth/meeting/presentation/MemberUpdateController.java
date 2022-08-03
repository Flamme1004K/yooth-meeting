package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberUpdateService;
import com.youth.meeting.application.dto.MemberInfoResponse;
import com.youth.meeting.application.dto.MemberUpdateRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;

    public MemberUpdateController(MemberUpdateService memberUpdateService) {
        this.memberUpdateService = memberUpdateService;
    }

    @PutMapping("/members/{memberNo}")
    public ResponseEntity<Void> findMemberInfo(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody MemberUpdateRequest request
    ) {
        memberUpdateService.updateMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }
}
