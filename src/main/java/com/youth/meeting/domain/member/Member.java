package com.youth.meeting.domain.member;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private String id;

    private String email;

    private String password;

    private LocalDate localDate;

    private String gender;

    private MemberStatus memberStatus;

    @Convert(converter = MemberStatusArrayConverter.class)
    private List<MemberStatus> memberStatuses;

    @ManyToOne
    private MemberInfo memberInfo;

    protected Member() {
    }
}
