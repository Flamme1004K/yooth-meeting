package com.youth.meeting.domain.member;

import com.youth.meeting.application.dto.ParticipantMemberJoinRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

class MemberTest {

    @Test
    void 주최자를_생성한다() {
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");
        assertThat(member.getMemberStatuses()).containsExactly(MemberStatus.ORGANIZER);
    }

    @Test
    void 참여자를_생성한다() {
        Member member = Member.JoinParticipant("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "adsf", "asdf");
        assertThat(member.getMemberStatuses()).containsExactly(MemberStatus.PARTICIPANT);
    }

    @Test
    void 주최자만하는_유저는_참가자_정보를_수정하지_못한다() {
        // given
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> member.changeInfo("aaaa", "bbbb"));
    }

    @Test
    void 주최자는_참가자로도_참여할_수_있다() {
        // given
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");

        // when
        member.enrollParticipant("aaaa", "bbbb");

        // then
        assertThat(member.getMemberStatuses()).containsExactly(MemberStatus.ORGANIZER, MemberStatus.PARTICIPANT);
    }

    @Test
    void 참가자는_주최자로도_참여할_수_있다() {
        // given
        Member member = Member.JoinParticipant("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "adsf", "asdf");

        // when
        member.enrollOrganizer("채식주의자");

        // then
        assertThat(member.getOrganizerInfo().getTeam()).isEqualTo("채식주의자");
    }

    @Test
    void 참가자는_참여자_정보를_수정할_수_있다() {
        // given
        Member member = Member.JoinParticipant("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "adsf", "asdf");

        // when
        member.changeInfo("eeee", "eeee");

        // then
        assertAll(() -> {
            assertThat(member.getParticipantInfo().getDietaryRestrictions()).isEqualTo("eeee");
            assertThat(member.getParticipantInfo().getIntroduce()).isEqualTo("eeee");
        });
    }

    @Test
    void 주최자는_참여자_정보를_수정하면_예외를_일으킨다() {
        // given
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");

        // then
        assertThatIllegalArgumentException().isThrownBy(() -> member.changeInfo("eeee", "eeee"));
    }

    @Test
    void 가입시_비밀번호는_문자_숫자_특수문자_7자리_이상이_아니면_예외를_발생시킨다() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                Member.JoinOrganizer("aaaa", "adsf!@34", "aaaa", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자")
        );
    }

    @Test
    void 비밀번호를_체크한다() {
        // given
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");

        // then
        member.checkPassword("adsf!@34");
    }

    @Test
    void 비밀번호를_체크시_다르면_예외를_발생시킨다() {
        // given
        Member member = Member.JoinOrganizer("aaaa", "adsf!@34", "adsf!@34", LocalDate.of(2022, 03, 03), "name", "남", "채식주의자");

        // then
        assertThatIllegalArgumentException().isThrownBy(() ->
                member.checkPassword("adsf!@34ㅁㄴㅇㅁㄴㅇ")
        );
    }
}