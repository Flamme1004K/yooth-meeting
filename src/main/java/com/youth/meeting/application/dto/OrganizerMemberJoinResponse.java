package com.youth.meeting.application.dto;

public class OrganizerMemberJoinResponse {

    private Long no;
    private String loginId;

    public OrganizerMemberJoinResponse() {
    }

    public OrganizerMemberJoinResponse(Long no, String loginId) {
        this.no = no;
        this.loginId = loginId;
    }

    public Long getNo() {
        return no;
    }

    public String getLoginId() {
        return loginId;
    }
}
