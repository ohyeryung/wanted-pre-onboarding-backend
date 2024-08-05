package com.smile.wanted_pre_task.job_post.service;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.company.repository.CompanyRepository;
import com.smile.wanted_pre_task.job_post.dto.JobPostDto;
import com.smile.wanted_pre_task.job_post.repository.JobPostRepository;
import com.smile.wanted_pre_task.member.domain.Member;
import com.smile.wanted_pre_task.member.repository.MemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class JobPostServiceImplTest {

    @Autowired
    private JobPostRepository jobPostRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private JobPostServiceImpl jobPostService;

    @BeforeEach
    @Transactional
    void beforeEach() {
        jobPostRepository.deleteAll();
        Company mockCompany = Company.builder().companyId(1L).companyName("원티드코리아").nation("대한민국").region("서울").build();
        companyRepository.save(mockCompany);

        Member mockMember = Member.builder().memberId(1L).memberEmail("test@email.com").password("test1234").build();
        memberRepository.save(mockMember);
    }

    @AfterEach
    void tearDown() {
        jobPostRepository.deleteAll();
        companyRepository.deleteAll();
        memberRepository.deleteAll();
    }


    @Test
    @Transactional
    @DisplayName("채용 공고를 등록합니다.")
    void posting() {
        // given
        JobPostDto.Post post = JobPostDto.Post.builder()
                .companyId(1L)
                .title("2024 백엔드 채용 공고")
                .position("백엔드 신입/경력 개발자")
                .reward(100000)
                .content("원티드랩에서 인재를 채용합니다. 자격요건은..")
                .stack("Java")
                .build();

        // when
        JobPostDto.Response response = jobPostService.posting(post);

        // then
        assertNotNull(response.getPostId());

    }

    @Test
    @Transactional
    @DisplayName("채용 공고를 수정합니다.")
    void updatePost() {
    }

    @Test
    void deletePost() {
    }

    @Test
    void listing() {
    }

    @Test
    void search() {
    }

    @Test
    void getDetail() {
    }

    @Test
    void apply() {
    }

}