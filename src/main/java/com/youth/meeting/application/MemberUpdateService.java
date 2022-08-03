package com.youth.meeting.application;

import com.youth.meeting.application.dto.MemberInfoResponse;
import com.youth.meeting.application.dto.MemberUpdateRequest;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberUpdateService {
    private final MemberRepository memberRepository;

    public MemberUpdateService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Transactional(readOnly = true)
    public void updateMember(Long memberNo, MemberUpdateRequest request) {
        Member member = memberRepository.findById(memberNo).orElseThrow(() -> new IllegalArgumentException("해당 회원은 없습니다. : " + memberNo));
        member.changeInfo(request.getDietaryRestrictions(), request.getIntroduce());
    }
}
