package com.youth.meeting.application.dto;


public class OrganizerMemberEnrollRequest {


    private String teamName;

    public OrganizerMemberEnrollRequest() {
    }

    public OrganizerMemberEnrollRequest(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamName() {
        return teamName;
    }
}
