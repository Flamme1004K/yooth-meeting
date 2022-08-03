package com.youth.meeting.application;

import com.youth.meeting.application.dto.ParticipantMemberJoinRequest;
import com.youth.meeting.application.dto.ParticipantMemberJoinResponse;
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
class ParticipantMemberJoinServiceTest {

    @InjectMocks
    private ParticipantMemberJoinService participantMemberJoinService;
    @Mock
    private MemberRepository memberRepository;

    @Test
    void 참여를_가입시킨다() {
        // given
        ParticipantMemberJoinRequest request = new ParticipantMemberJoinRequest("aaaa", "bbbbb@abc.com", "asdf!@#45", LocalDate.of(2022, 8, 3), "남", "나의상세.pdf", "안녕하세여");
        given(memberRepository.existsByLoginId(any())).willReturn(false);
        given(memberRepository.save(any())).willReturn(new Member(request.getId(), request.getEmail(), request.getPassword(), request.getLocalDate(), request.getGender(), request.getDietaryRestrictions(), request.getIntroduce()));
        // when
        ParticipantMemberJoinResponse participantMemberJoinResponse = participantMemberJoinService.joinParticipantMember(request);

        // then
        assertAll(() -> {
            assertThat(participantMemberJoinResponse.getLoginId()).isEqualTo("aaaa");
        });
    }

    @Test
    void 참여자가_이미_가입되어있으면_실패한다() {
        // given
        ParticipantMemberJoinRequest request = new ParticipantMemberJoinRequest("aaaa", "bbbbb@abc.com", "asdf!@#45", LocalDate.of(2022, 8, 3), "남", "나의상세.pdf", "안녕하세여");
        given(memberRepository.existsByLoginId(any())).willReturn(true);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                participantMemberJoinService.joinParticipantMember(request)
        );
    }

    @Test
    void 참여자_가입시_비밀번호는_문자_숫자_특수문자_7자리_이상이_아니면_예외를_발생시킨다() {
        // given
        ParticipantMemberJoinRequest request = new ParticipantMemberJoinRequest("aaaa", "bbbbb@abc.com", "aaaa", LocalDate.of(2022, 8, 3), "남", "나의상세.pdf", "안녕하세여");
        given(memberRepository.existsByLoginId(any())).willReturn(false);

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                participantMemberJoinService.joinParticipantMember(request)
        );
    }
}