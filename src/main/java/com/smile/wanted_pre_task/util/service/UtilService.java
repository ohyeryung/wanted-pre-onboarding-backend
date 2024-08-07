package com.smile.wanted_pre_task.util.service;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.company.repository.CompanyRepository;
import com.smile.wanted_pre_task.global.exception.ResponseMessage;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.job_post.repository.JobPostRepository;
import com.smile.wanted_pre_task.member.domain.Member;
import com.smile.wanted_pre_task.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class UtilService {

    private final MemberRepository memberRepository;
    private final CompanyRepository companyRepository;
    private final JobPostRepository jobPostRepository;

    public Member getMember(UUID memberId) {
        return memberRepository.findById(memberId).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.MEMBER_NOT_FOUND)
        );
    }

    public JobPost getJobPost(Long postId) {
        return jobPostRepository.findById(postId).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.JOB_POST_NOT_FOUND)
        );
    }

    public Company toEntity(String companyId) {
        Company company = new Company();
        company.setCompanyId(UUID.fromString(companyId));// String을 UUID로 변환
        log.info("========================================");
        log.info("companyId : {}", company.getCompanyId());
        return company;
    }
    @Transactional(readOnly = true)
    public Company getCompany(String companyId) {
        Company company = toEntity(companyId);
        return companyRepository.findById(company.getCompanyId()).orElseThrow(
                () -> new NoSuchElementException(ResponseMessage.COMPANY_NOT_FOUND)
        );
    }

}
