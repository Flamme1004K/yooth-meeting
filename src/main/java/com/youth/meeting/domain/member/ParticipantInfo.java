package com.youth.meeting.domain.member;

import javax.persistence.Embeddable;

@Embeddable
public class ParticipantInfo {

    private String dietaryRestrictions;

    private String introduce;

    protected ParticipantInfo() {
    }

    public ParticipantInfo(String dietaryRestrictions, String introduce) {
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }
}
