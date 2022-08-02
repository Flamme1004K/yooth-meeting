package com.youth.meeting.application;

import com.youth.meeting.application.dto.OrganizerMemberJoinRequest;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberJoinService {

    private final MemberRepository memberRepository;

    public MemberJoinService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional
    public OrganizerMemberJoinResponse joinOrganizerMember(OrganizerMemberJoinRequest request) {
        Boolean isExistMember = memberRepository.existsByLoginId(request.getId());
        if (isExistMember) {
            throw new IllegalArgumentException("이미 가입한 사용자입니다.");
        }

        Member member = memberRepository.save(new Member(
                request.getId(),
                request.getEmail(),
                request.getPassword(),
                request.getLocalDate(),
                request.getGender()));

        return new OrganizerMemberJoinResponse(member.getLoginId());
    }
}
