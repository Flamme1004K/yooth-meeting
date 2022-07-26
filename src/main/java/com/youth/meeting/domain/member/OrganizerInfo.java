package com.youth.meeting.domain.member;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class OrganizerInfo {
    @Column
    private String team;

    protected OrganizerInfo() {
    }

    public OrganizerInfo(String team) {
        this.team = team;
    }

    public String getTeam() {
        return team;
    }
}
