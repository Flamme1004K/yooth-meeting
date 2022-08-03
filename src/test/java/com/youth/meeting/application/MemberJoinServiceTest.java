package com.youth.meeting.application;

import com.youth.meeting.application.dto.OrganizerMemberJoinRequest;
import com.youth.meeting.application.dto.OrganizerMemberJoinResponse;
import com.youth.meeting.domain.member.Member;
import com.youth.meeting.domain.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MemberJoinServiceTest {

    @InjectMocks
    private MemberJoinService memberJoinService;
    @Mock
    private MemberRepository memberRepository;

    @Test
    void 주최자를_가입시킨다() {
        // given
        OrganizerMemberJoinRequest request = new OrganizerMemberJoinRequest("aaaa", "bbbbb@abc.com", "asdf!@#45", LocalDate.of(2022, 8, 3), "남", "아보카도");
        given(memberRepository.existsByLoginId(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(new Member(request.getId(), request.getEmail(), request.getPassword(), request.getLocalDate(), request.getGender(), request.getTeamName()));
        // when
        OrganizerMemberJoinResponse organizerMemberJoinResponse = memberJoinService.joinOrganizerMember(request);

        // then
        assertAll(() -> {
            assertThat(organizerMemberJoinResponse.getId()).isEqualTo("aaaa");
        });
    }

    @Test
    void 주최자가_이미_가입되어있으면_실패한다() {
        // given
        OrganizerMemberJoinRequest request = new OrganizerMemberJoinRequest("aaaa", "bbbbb@abc.com", "asdf!@#45", LocalDate.of(2022, 8, 3), "남", "아보카도");
        given(memberRepository.existsByLoginId(any())).willReturn(true);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                memberJoinService.joinOrganizerMember(request)
        );
    }

    @Test
    void 주최자_가입시_비밀번호는_문자_숫자_특수문자_7자리_이상이_아니면_예외를_발생시킨다() {
        // given
        OrganizerMemberJoinRequest request = new OrganizerMemberJoinRequest("aaaa", "bbbbb@abc.com", "aaaa", LocalDate.of(2022, 8, 3), "남", "아보카도");
        given(memberRepository.existsByLoginId(any())).willReturn(false);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                memberJoinService.joinOrganizerMember(request)
        );
    }
}