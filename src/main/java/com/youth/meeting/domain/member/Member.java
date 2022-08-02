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

    @ManyToOne
    private MemberInfo memberInfo;

    protected Member() {
    }

    public Member(String loginId, String email, String password, LocalDate localDate, String gender) {
        PasswordValidator.validate(password);
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.localDate = localDate;
        this.gender = gender;
        this.memberStatuses.add(MemberStatus.ORGANIZER);
    }

    public String getLoginId() {
        return loginId;
    }
}
