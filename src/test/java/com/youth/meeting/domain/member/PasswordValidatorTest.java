package com.youth.meeting.domain.member;

import com.youth.meeting.application.dto.ParticipantMemberJoinRequest;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class PasswordValidatorTest {

    @Test
    void 비밀번호는_문자_숫자_특수문자_7자리_이상이다() {
        PasswordValidator.validate("asdf!@!&*123");
    }

    @Test
    void 비밀번호는_문자_숫자_특수문자_7자리_이상이_아니면_예외를_발생시킨다() {
        assertThatIllegalArgumentException().isThrownBy(() ->
                PasswordValidator.validate("aaaa")
        );
    }
}