package com.youth.meeting.application;

import com.youth.meeting.application.dto.MemberInfoResponse;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberInfoService {
    private final MemberRepository memberRepository;

    public MemberInfoService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public MemberInfoResponse findOrganizerMember(Long memberNo) {
        Member member = memberRepository.findById(memberNo).orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. : " + memberNo));
        return new MemberInfoResponse(
                member.getNo(),
                member.getLoginId(),
                member.getEmail(),
                member.getName(),
                member.getBirth(),
                member.getOrganizerInfo().getTeam(),
                member.getParticipantInfo().getDietaryRestrictions(),
                member.getParticipantInfo().getIntroduce()
        );
    }
}
