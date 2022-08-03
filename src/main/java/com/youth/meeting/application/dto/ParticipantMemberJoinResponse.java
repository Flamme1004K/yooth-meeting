package com.youth.meeting.application.dto;

public class ParticipantMemberJoinResponse {

    private Long no;
    private String loginId;

    public ParticipantMemberJoinResponse() {
    }

    public ParticipantMemberJoinResponse(Long no, String loginId) {
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
