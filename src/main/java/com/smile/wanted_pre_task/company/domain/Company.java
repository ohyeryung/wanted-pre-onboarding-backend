package com.smile.wanted_pre_task.company.domain;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "TB_COMPANY")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "COMPANY_ID", columnDefinition = "BINARY(16)")
    private UUID companyId;
    @Column(name = "COMPANY_NAME")
    private String companyName;
    @Column(name = "NATION")
    private String nation;
    @Column(name = "REGION")
    private String region;
}
