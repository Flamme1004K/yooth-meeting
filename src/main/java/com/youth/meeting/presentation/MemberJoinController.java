package com.youth.meeting.presentation;

import com.youth.meeting.application.MemberJoinService;
import com.youth.meeting.application.dto.OrganizerMemberJoinRequest;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MemberJoinController {

    private final MemberJoinService memberJoinService;

    public MemberJoinController(MemberJoinService memberJoinService) {
        this.memberJoinService = memberJoinService;
    }

    @PostMapping("/organizers")
    public ResponseEntity<String> joinOrganizerMember(
            @RequestBody OrganizerMemberJoinRequest request
    ) {
        OrganizerMemberJoinResponse response = memberJoinService.joinOrganizerMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getId())).build();
    }
}
