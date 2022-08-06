package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberUpdateService;
import com.youth.meeting.application.dto.MemberInfoResponse;
import com.youth.meeting.application.dto.MemberUpdateRequest;
import com.youth.meeting.authenticate.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;
    private final JwtProvider jwtProvider;

    public MemberUpdateController(MemberUpdateService memberUpdateService, JwtProvider jwtProvider) {
        this.memberUpdateService = memberUpdateService;
        this.jwtProvider = jwtProvider;
    }

    @PutMapping("/members/{memberNo}")
    public ResponseEntity<Void> findMemberInfo(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody MemberUpdateRequest request,
            @RequestHeader(value = "Authorization") String token
    ) {
        jwtProvider.parseJwtToken(token);
        memberUpdateService.updateMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }
}
