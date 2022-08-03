package com.youth.meeting.application.dto;


public class ParticipantMemberEnrollRequest {

    private String dietaryRestrictions;

    private String introduce;

    protected ParticipantMemberEnrollRequest() {
    }

    public ParticipantMemberEnrollRequest(String dietaryRestrictions, String introduce) {
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public String getIntroduce() {
        return introduce;
    }
}
