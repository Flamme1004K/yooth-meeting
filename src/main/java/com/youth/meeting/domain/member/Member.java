package com.youth.meeting.domain.member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String loginId;

    private String email;

    private String password;

    private LocalDate birth;

    private String gender;

    private String name;

    @Convert(converter = MemberStatusArrayConverter.class)
    private List<MemberStatus> memberStatuses = new ArrayList<>();

    @Embedded
    private ParticipantInfo participantInfo;

    @Embedded
    private OrganizerInfo organizerInfo;

    protected Member() {
    }

    public Member(String loginId, String email, String password, LocalDate birth, String name, String gender, String teamName) {
        PasswordValidator.validate(password);
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.name = name;
        this.gender = gender;
        this.organizerInfo = new OrganizerInfo(teamName);
        this.memberStatuses.add(MemberStatus.ORGANIZER);
    }

    public Member(String loginId, String email, String password, LocalDate birth, String name, String gender, String dietaryRestrictions, String introduce) {
        PasswordValidator.validate(password);
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.birth = birth;
        this.name = name;
        this.gender = gender;
        this.participantInfo = new ParticipantInfo(dietaryRestrictions, introduce);
        this.memberStatuses.add(MemberStatus.PARTICIPANT);
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

    public String getPassword() {
        return password;
    }

    public LocalDate getBirth() {
        return birth;
    }

    public String getGender() {
        return gender;
    }

    public List<MemberStatus> getMemberStatuses() {
        return memberStatuses;
    }

    public ParticipantInfo getParticipantInfo() {
        return participantInfo;
    }

    public OrganizerInfo getOrganizerInfo() {
        return organizerInfo;
    }
}
