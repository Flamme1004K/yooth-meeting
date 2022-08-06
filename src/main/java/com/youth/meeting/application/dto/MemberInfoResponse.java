package com.youth.meeting.application.dto;

import com.youth.meeting.domain.member.Member;

import java.time.LocalDate;

import static com.youth.meeting.domain.member.MemberStatus.ORGANIZER;
import static com.youth.meeting.domain.member.MemberStatus.PARTICIPANT;

public class MemberInfoResponse {

    private Long no;
    private String loginId;
    private String email;
    private String name;
    private LocalDate birth;
    private String teamName;
    private String dietaryRestrictions;
    private String introduce;

    public static MemberInfoResponse of(Member member) {
        return new MemberInfoResponse(
                member.getNo(),
                member.getLoginId(),
                member.getEmail(),
                member.getName(),
                member.getBirth(),
                member.getMemberStatuses().contains(ORGANIZER) ? member.getOrganizerInfo().getTeam() : null,
                member.getMemberStatuses().contains(PARTICIPANT) ? member.getParticipantInfo().getDietaryRestrictions() : null,
                member.getMemberStatuses().contains(PARTICIPANT) ? member.getParticipantInfo().getIntroduce() : null
        );
    }

    public MemberInfoResponse() {
    }

    public MemberInfoResponse(Long no, String loginId) {
        this.no = no;
        this.loginId = loginId;
    }

    public MemberInfoResponse(Long no, String loginId, String email, String name, LocalDate birth, String teamName, String dietaryRestrictions, String introduce) {
        this.no = no;
        this.loginId = loginId;
        this.email = email;
        this.name = name;
        this.birth = birth;
        this.teamName = teamName;
        this.dietaryRestrictions = dietaryRestrictions;
        this.introduce = introduce;
    }

    public Long getNo() {
        return no;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getTeamName() {
        return teamName;
    }

    public String getDietaryRestrictions() {
        return dietaryRestrictions;
    }

    public String getIntroduce() {
        return introduce;
    }
}
