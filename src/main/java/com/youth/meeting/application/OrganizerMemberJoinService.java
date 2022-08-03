package com.youth.meeting.application;

import com.youth.meeting.application.dto.OrganizerMemberJoinRequest;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrganizerMemberJoinService {

    private final MemberRepository memberRepository;

    public OrganizerMemberJoinService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public OrganizerMemberJoinResponse joinOrganizerMember(OrganizerMemberJoinRequest request) {
        existMember(request.getId());

        Member member = memberRepository.save(new Member(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getLocalDate(),
                request.getName(),
                request.getGender(),
                request.getTeamName()));

        return new OrganizerMemberJoinResponse(member.getNo(), member.getLoginId());
    }

    private void existMember(String loginId) {
        Boolean isExistMember = memberRepository.existsByLoginId(loginId);
        if (isExistMember) {
            throw new IllegalArgumentException("이미 가입한 사용자입니다.");
        }
    }
}