package com.youth.meeting.application;

import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.springframework.stereotype.Service;

@Service
public class MemberLoginService {

    private final MemberRepository memberRepository;
    private final TokenCreator tokenCreator;

    public MemberLoginService(MemberRepository memberRepository, TokenCreator tokenCreator) {
        this.memberRepository = memberRepository;
        this.tokenCreator = tokenCreator;
    }

    public String loginMember(String loginId, String password) {
        Member member = memberRepository.findByLoginId(loginId).orElseThrow(() -> new IllegalArgumentException("해당 사용자는 존재하지 않습니다."));
        member.checkPassword(password);
        return tokenCreator.createToken(loginId);
    }
}
