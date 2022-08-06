package com.youth.meeting.application;

import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService {

    private final MemberRepository memberRepository;

    public MemberLoginService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void loginMember(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("해당 사용자는 존재하지 않습니다."));
        member.checkPassword(password);
    }
}
