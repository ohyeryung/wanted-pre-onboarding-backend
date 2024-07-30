package com.smile.wanted_pre_task.member.domain;

import javax.persistence.*;

@Entity
@Table(name = "TB_MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "PASSWORD")
    private String password;
}
