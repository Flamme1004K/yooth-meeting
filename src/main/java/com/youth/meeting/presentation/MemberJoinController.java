package com.youth.meeting.presentation;

import com.youth.meeting.application.OrganizerMemberJoinService;
import com.youth.meeting.application.ParticipantMemberJoinService;
import com.youth.meeting.application.dto.OrganizerMemberJoinRequest;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import com.youth.meeting.application.dto.ParticipantMemberJoinRequest;
import com.youth.meeting.application.dto.ParticipantMemberJoinResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@RestController
public class MemberJoinController {

    private final OrganizerMemberJoinService organizerMemberJoinService;
    private final ParticipantMemberJoinService participantMemberJoinService;

    public MemberJoinController(OrganizerMemberJoinService organizerMemberJoinService, ParticipantMemberJoinService participantMemberJoinService) {
        this.organizerMemberJoinService = organizerMemberJoinService;
        this.participantMemberJoinService = participantMemberJoinService;
    }

    @PostMapping("/organizers")
    public ResponseEntity<Void> joinOrganizerMember(
            @RequestBody OrganizerMemberJoinRequest request
    ) {
        OrganizerMemberJoinResponse response = organizerMemberJoinService.joinOrganizerMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }

    @PostMapping("/participants")
    public ResponseEntity<Void> joinParticipantMember(
            @RequestBody ParticipantMemberJoinRequest request
    ) {
        ParticipantMemberJoinResponse response = participantMemberJoinService.joinParticipantMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }
}
