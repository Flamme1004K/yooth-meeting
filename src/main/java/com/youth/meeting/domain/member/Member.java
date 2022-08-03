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

    private LocalDate localDate;

    private String gender;

    @Convert(converter = MemberStatusArrayConverter.class)
    private List<MemberStatus> memberStatuses = new ArrayList<>();

    @Embedded
    private ParticipantInfo participantInfo;

    @Embedded
    private OrganizerInfo organizerInfo;

    protected Member() {
    }

    public Member(String loginId, String email, String password, LocalDate localDate, String gender, String teamName) {
        PasswordValidator.validate(password);
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.localDate = localDate;
        this.gender = gender;
        this.organizerInfo = new OrganizerInfo(teamName);
        this.memberStatuses.add(MemberStatus.ORGANIZER);
    }

    public String getLoginId() {
        return loginId;
    }
}
