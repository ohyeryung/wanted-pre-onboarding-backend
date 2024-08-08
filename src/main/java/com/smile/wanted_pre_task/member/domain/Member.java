package com.smile.wanted_pre_task.member.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "MEMBER_ID", columnDefinition = "BINARY(16)")
    private UUID memberId;
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "PASSWORD")
    private String password;


}
