package com.youth.meeting.presentation;

import com.youth.meeting.application.OrganizerMemberJoinService;
import com.youth.meeting.application.ParticipantMemberJoinService;
import com.youth.meeting.application.dto.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class MemberJoinController {

    private final OrganizerMemberJoinService organizerMemberJoinService;
    private final ParticipantMemberJoinService participantMemberJoinService;

    public MemberJoinController(OrganizerMemberJoinService organizerMemberJoinService, ParticipantMemberJoinService participantMemberJoinService) {
        this.organizerMemberJoinService = organizerMemberJoinService;
        this.participantMemberJoinService = participantMemberJoinService;
    }

    @PostMapping("/join-organizers")
    public ResponseEntity<Void> joinOrganizerMember(
            @RequestBody OrganizerMemberJoinRequest request
    ) {
        OrganizerMemberJoinResponse response = organizerMemberJoinService.joinOrganizerMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }

    @PatchMapping("/organizers/{memberNo}")
    public ResponseEntity<Void> enrollOrganizerMember(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody OrganizerMemberEnrollRequest request
    ) {
        organizerMemberJoinService.enrollOrganizerMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/join-participants")
    public ResponseEntity<Void> joinParticipantMember(
            @RequestBody ParticipantMemberJoinRequest request
    ) {
        ParticipantMemberJoinResponse response = participantMemberJoinService.joinParticipantMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }

    @PatchMapping("/participants/{memberNo}")
    public ResponseEntity<Void> enrollParticipantMember(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody ParticipantMemberEnrollRequest request
    ) {
        participantMemberJoinService.enrollParticipantMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }
}
