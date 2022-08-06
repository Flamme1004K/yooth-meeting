package com.youth.meeting.domain.member;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

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

}