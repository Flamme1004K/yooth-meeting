package com.youth.meeting.presentation;

import com.youth.meeting.application.OrganizerMemberJoinService;
import com.youth.meeting.application.ParticipantMemberJoinService;
import com.youth.meeting.application.dto.*;
import com.youth.meeting.authenticate.JwtProvider;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
public class MemberJoinController {

    private final OrganizerMemberJoinService organizerMemberJoinService;
    private final ParticipantMemberJoinService participantMemberJoinService;
    private final JwtProvider jwtProvider;

    public MemberJoinController(OrganizerMemberJoinService organizerMemberJoinService, ParticipantMemberJoinService participantMemberJoinService, JwtProvider jwtProvider) {
        this.organizerMemberJoinService = organizerMemberJoinService;
        this.participantMemberJoinService = participantMemberJoinService;
        this.jwtProvider = jwtProvider;
    }

    @PostMapping("/organizers")
    public ResponseEntity<Void> joinOrganizerMember(
            @RequestBody OrganizerMemberJoinRequest request
    ) {
        OrganizerMemberJoinResponse response = organizerMemberJoinService.joinOrganizerMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }

    @PatchMapping("/organizers/{memberNo}")
    public ResponseEntity<Void> enrollOrganizerMember(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody OrganizerMemberEnrollRequest request,
            @RequestHeader(value = "Authorization") String token
    ) {
        jwtProvider.parseJwtToken(token);
        organizerMemberJoinService.enrollOrganizerMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/participants")
    public ResponseEntity<Void> joinParticipantMember(
            @RequestBody ParticipantMemberJoinRequest request
    ) {
        ParticipantMemberJoinResponse response = participantMemberJoinService.joinParticipantMember(request);
        return ResponseEntity.created(URI.create("/join" + response.getNo())).build();
    }

    @PatchMapping("/participants/{memberNo}")
    public ResponseEntity<Void> enrollParticipantMember(
            @PathVariable("memberNo") Long memberNo,
            @RequestBody ParticipantMemberEnrollRequest request,
            @RequestHeader(value = "Authorization") String token
    ) {
        jwtProvider.parseJwtToken(token);
        participantMemberJoinService.enrollParticipantMember(memberNo, request);
        return ResponseEntity.noContent().build();
    }
}
