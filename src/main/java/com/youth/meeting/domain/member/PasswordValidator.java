package com.youth.meeting.domain.member;

import java.util.regex.Pattern;

public class PasswordValidator {

    private static final Pattern PATTERN = Pattern.compile("((?=.*[a-z])(?=.*[0-9])(?=.*[^a-zA-Z0-9]).{7,})");

    public static void validate(String password) {
        if (!PATTERN.matcher(password).matches()) {
            throw new IllegalArgumentException("비밀번호는 문자+숫자+특수문자, 7자리 이상이어야 합니다");
        }
    }
}
