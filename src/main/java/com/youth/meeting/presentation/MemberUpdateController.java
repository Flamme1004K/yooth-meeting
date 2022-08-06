package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberUpdateService;
import com.youth.meeting.application.dto.MemberUpdateRequest;
import com.youth.meeting.authenticate.JsonWebTokenProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class MemberUpdateController {

    private final MemberUpdateService memberUpdateService;
    private final JsonWebTokenProvider jsonWebTokenProvider;

    public MemberUpdateController(MemberUpdateService memberUpdateService, JsonWebTokenProvider jsonWebTokenProvider) {
        this.memberUpdateService = memberUpdateService;
        this.jsonWebTokenProvider = jsonWebTokenProvider;
    }

    @PutMapping("/members/{memberNo}")
    public ResponseEntity<Void> findMemberInfo(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody MemberUpdateRequest request,
            @RequestHeader(value = "Authorization") String token
    ) {
        jsonWebTokenProvider.parseJwtToken(token);
        memberUpdateService.updateMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }
}
