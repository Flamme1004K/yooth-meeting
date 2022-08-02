package com.youth.meeting.domain.member;

import javax.persistence.*;

@Entity
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long no;

    private Long organizerNo;

    private Long participantNo;

}
