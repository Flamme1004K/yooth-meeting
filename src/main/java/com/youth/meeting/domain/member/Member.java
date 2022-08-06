package com.youth.meeting.domain.member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static com.youth.meeting.domain.member.MemberStatus.ORGANIZER;
import static com.youth.meeting.domain.member.MemberStatus.PARTICIPANT;

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
        this.memberStatuses.add(PARTICIPANT);
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

    public List<MemberStatus> getMemberStatuses() {
        return memberStatuses;
    }

    public ParticipantInfo getParticipantInfo() {
        return participantInfo;
    }

    public OrganizerInfo getOrganizerInfo() {
        return organizerInfo;
    }

    public void changeInfo(String dietaryRestrictions, String introduce) {
        if (!memberStatuses.contains(PARTICIPANT)) {
            throw new IllegalArgumentException("참가자 정보만 수정할 수 있습니다.");
        }
        this.participantInfo.changeInfo(dietaryRestrictions, introduce);
    }

    public void enrollOrganizer(String teamName) {
        if (!memberStatuses.contains(ORGANIZER)) {
            throw new IllegalArgumentException("이미 주최자로 등록되어 있습니다");
        }
        this.organizerInfo = new OrganizerInfo(teamName);
    }

    public void enrollParticipant(String dietaryRestrictions, String introduce) {
        if (!memberStatuses.contains(PARTICIPANT)) {
            throw new IllegalArgumentException("참가자 정보만 수정할 수 있습니다.");
        }
        this.participantInfo = new ParticipantInfo(dietaryRestrictions, introduce);
    }

    public void checkPassword(String password) {
        if (!this.password.equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }
}
