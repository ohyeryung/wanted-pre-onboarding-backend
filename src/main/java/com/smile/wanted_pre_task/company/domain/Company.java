package com.smile.wanted_pre_task.company.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_COMPANY")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPANY_ID")
    private Long companyId;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "NATION")
    private String nation;
    @Column(name = "REGION")
    private String region;
}
