package com.youth.meeting.domain.member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.youth.meeting.domain.member.AES256.decrypt;
import static com.youth.meeting.domain.member.AES256.encrypt;
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
    private Set<MemberStatus> memberStatuses = new HashSet<>();

    @Embedded
    private ParticipantInfo participantInfo;

    @Embedded
    private OrganizerInfo organizerInfo;

    protected Member() {
    }

    private Member(String loginId, String email, String password, LocalDate birth, String name, String gender) {
        PasswordValidator.validate(password);
        this.loginId = loginId;
        this.email = email;
        this.password = encrypt(password);
        this.birth = birth;
        this.name = name;
        this.gender = gender;
    }

    public Member(String loginId, String email, String password, LocalDate birth, String name, String gender, String teamName) {
        this(loginId, email, password, birth, name, gender);
        this.organizerInfo = new OrganizerInfo(teamName);
        this.memberStatuses.add(MemberStatus.ORGANIZER);
    }

    public Member(String loginId, String email, String password, LocalDate birth, String name, String gender, String dietaryRestrictions, String introduce) {
        this(loginId, email, password, birth, name, gender);
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

    public Set<MemberStatus> getMemberStatuses() {
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
        this.memberStatuses.add(ORGANIZER);
        this.organizerInfo = new OrganizerInfo(teamName);
    }

    public void enrollParticipant(String dietaryRestrictions, String introduce) {
        this.memberStatuses.add(PARTICIPANT);
        this.participantInfo = new ParticipantInfo(dietaryRestrictions, introduce);
    }

    public void checkPassword(String password) {
        if (!decrypt(this.password).equals(password)) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
    }

    public static Member JoinOrganizer(String loginId, String email, String password, LocalDate birth, String name, String gender, String teamName) {
        return new Member(loginId, email, password, birth, name, gender, teamName);
    }

    public static Member JoinParticipant(String loginId, String email, String password, LocalDate birth, String name, String gender, String dietaryRestrictions, String introduce) {
        return new Member(loginId, email, password, birth, name, gender, dietaryRestrictions, introduce);
    }
}
