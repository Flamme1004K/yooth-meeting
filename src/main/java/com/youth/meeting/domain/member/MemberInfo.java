package com.youth.meeting.domain.member;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MemberInfo {

    @Id
    private Long no;

    private String dietaryRestrictions;

    private String introduce;
}
