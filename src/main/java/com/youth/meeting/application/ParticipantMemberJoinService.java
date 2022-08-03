package com.youth.meeting.application;

import com.youth.meeting.application.dto.*;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ParticipantMemberJoinService {

    private final MemberRepository memberRepository;

    public ParticipantMemberJoinService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public ParticipantMemberJoinResponse joinParticipantMember(ParticipantMemberJoinRequest request) {
        existMember(request.getId());

        Member member = memberRepository.save(new Member(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getLocalDate(),
                request.getGender(),
                request.getDietaryRestrictions(),
                request.getIntroduce()));

        return new ParticipantMemberJoinResponse(member.getNo(), member.getLoginId());
    }

    private void existMember(String loginId) {
        Boolean isExistMember = memberRepository.existsByLoginId(loginId);
        if (isExistMember) {
            throw new IllegalArgumentException("이미 가입한 사용자입니다.");
        }
    }

    @Transactional
    public void enrollParticipantMember(Long memberNo, ParticipantMemberEnrollRequest request) {
        Member member = memberRepository.findById(memberNo).orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. : " + memberNo));
        member.enrollParticipant(request.getDietaryRestrictions(), request.getIntroduce());
    }
}
