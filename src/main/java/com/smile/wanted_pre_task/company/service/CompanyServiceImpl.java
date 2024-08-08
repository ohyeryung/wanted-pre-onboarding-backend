package com.smile.wanted_pre_task.company.service;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.company.dto.CompanyDto;
import com.smile.wanted_pre_task.company.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyServiceImpl implements CompanyService{

    private final CompanyRepository companyRepository;
    @Override
    @Transactional
    public void enroll(CompanyDto.Post post) {
        UUID companyId = UUID.randomUUID();
        Company company = post.toEntity(companyId);
        companyRepository.save(company);
    }
}
