package com.youth.meeting.domain.member;

import javax.persistence.Embeddable;

@Embeddable
public class OrganizerInfo {
    private String team;

    public OrganizerInfo() {
    }

    public OrganizerInfo(String team) {
        this.team = team;
    }
}
