package com.youth.meeting.application.dto;

public class MemberUpdateRequest {
    private String dietaryRestrictions;
    private String introduce;

    protected MemberUpdateRequest() {
    }

    public MemberUpdateRequest(String dietaryRestrictions, String introduce) {
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
