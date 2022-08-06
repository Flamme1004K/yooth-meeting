package com.youth.meeting.application.dto;

import java.time.LocalDate;

public class OrganizerMemberJoinResponse {

    private Long no;
    private String loginId;
    private String email;
    private String name;
    private LocalDate birth;
    private String teamName;


    public OrganizerMemberJoinResponse() {
    }

    public OrganizerMemberJoinResponse(Long no, String loginId) {
        this.no = no;
        this.loginId = loginId;
    }

    public OrganizerMemberJoinResponse(Long no, String loginId, String email, String name, LocalDate birth, String teamName) {
        this.no = no;
        this.loginId = loginId;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.teamName = teamName;
    }

    public Long getNo() {
        return no;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getTeamName() {
        return teamName;
    }
}
