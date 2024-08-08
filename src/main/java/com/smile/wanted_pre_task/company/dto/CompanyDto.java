package com.smile.wanted_pre_task.company.dto;

import com.smile.wanted_pre_task.company.domain.Company;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

public class CompanyDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private String companyName;
        private String nation;
        private String region;

        public Company toEntity(UUID companyId) {
            return Company.builder()
                    .companyId(companyId)
                    .companyName(companyName)
                    .nation(nation)
                    .region(region)
                    .build();
        }
    }
}
