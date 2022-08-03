package com.youth.meeting.application.dto;

import java.time.LocalDate;

public class OrganizerMemberJoinRequest {

    private String id;

    private String email;

    private String password;

    private LocalDate localDate;

    private String gender;

    private String teamName;

    public OrganizerMemberJoinRequest() {
    }

    public OrganizerMemberJoinRequest(String id, String email, String password, LocalDate localDate, String gender, String teamName) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.localDate = localDate;
        this.gender = gender;
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public String getGender() {
        return gender;
    }

    public String getTeamName() {
        return teamName;
    }
}
