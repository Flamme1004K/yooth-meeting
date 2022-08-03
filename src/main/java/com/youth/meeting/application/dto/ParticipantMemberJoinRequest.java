package com.youth.meeting.application.dto;

import java.time.LocalDate;

public class ParticipantMemberJoinRequest {

    private String id;

    private String email;

    private String password;

    private String name;

    private LocalDate localDate;

    private String gender;

    private String dietaryRestrictions;

    private String introduce;

    protected ParticipantMemberJoinRequest() {
    }

    public ParticipantMemberJoinRequest(String id, String email, String password, String name, LocalDate localDate, String gender, String dietaryRestrictions, String introduce) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.localDate = localDate;
        this.gender = gender;
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
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

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public String getIntroduce() {
        return introduce;
    }
}
