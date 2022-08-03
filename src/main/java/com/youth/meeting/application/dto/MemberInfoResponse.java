package com.youth.meeting.application.dto;

import java.time.LocalDate;

public class MemberInfoResponse {

    private Long no;
    private String loginId;
    private String email;
    private String name;
    private LocalDate birth;
    private String teamName;
    private String dietaryRestrictions;
    private String introduce;

    public MemberInfoResponse() {
    }

    public MemberInfoResponse(Long no, String loginId) {
        this.no = no;
        this.loginId = loginId;
    }

    public MemberInfoResponse(Long no, String loginId, String email, String name, LocalDate birth, String teamName, String dietaryRestrictions, String introduce) {
        this.no = no;
        this.loginId = loginId;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.teamName = teamName;
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }
}
