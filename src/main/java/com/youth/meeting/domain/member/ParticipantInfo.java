package com.youth.meeting.domain.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class ParticipantInfo {

    @Column
    private String dietaryRestrictions;

    @Column
    private String introduce;

    protected ParticipantInfo() {
    }

    public ParticipantInfo(String dietaryRestrictions, String introduce) {
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public String getIntroduce() {
        return introduce;
    }

    public void changeInfo(String dietaryRestrictions, String introduce) {
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }
}
