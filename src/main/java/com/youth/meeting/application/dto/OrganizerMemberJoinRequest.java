package com.youth.meeting.application.dto;

import java.time.LocalDate;

public class OrganizerMemberJoinRequest {

    private String id;

    private String email;

    private String name;

    private String password;

    private LocalDate birth;

    private String gender;

    private String teamName;

    public OrganizerMemberJoinRequest() {
    }

    public OrganizerMemberJoinRequest(String id, String email, String name, String password, LocalDate birth, String gender, String teamName) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.password = password;
        this.birth = birth;
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

    public LocalDate getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    public String getName() {
        return name;
    }

    public String getTeamName() {
        return teamName;
    }
}
