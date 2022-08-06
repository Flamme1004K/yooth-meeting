package com.youth.meeting.domain.member;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

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
        member.enrollParticipant("aaaa","bbbb");

        // then
        assertThat(member.getMemberStatuses()).containsExactly(MemberStatus.ORGANIZER, MemberStatus.PARTICIPANT);
    }
}