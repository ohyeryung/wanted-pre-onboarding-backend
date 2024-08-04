package com.smile.wanted_pre_task.job_post.service;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.company.repository.CompanyRepository;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.job_post.dto.*;
import com.smile.wanted_pre_task.job_post.repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class JobPostServiceImpl implements JobPostService {

    private final JobPostRepository jobPostRepository;
    private final CompanyRepository companyRepository;
    @Override
    public void posting(JobPostDto.Post postDto) {
        // 1. 회사 ID 검증 (게시글이 등록 가능한 회사인지 유효성 체크) - 토큰이 없으니 회사Id를 함께 전달
        Company company = getCompany(postDto.getCompanyId());
        JobPost jobPost = postDto.toRegisterEntity(company);
        // 2. DB 저장
        jobPostRepository.save(jobPost);
    }

    @Override
    public List<JobPostDto.Response> listing() {
        List<JobPost> jobPosts = jobPostRepository.findAllByOrderByCreatedAtDesc();
        return jobPosts.stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public JobPostDto.Update updatePost(Long postId, JobPostDto.Update jobPostUpdateReqDto) {
        // 유효성 체크
        JobPost jobPost = getJobPost(postId);

        JobPost initJobPost = jobPostUpdateReqDto.toUpdateEntity();
        jobPost.update(initJobPost);

        return new JobPostDto.Update(jobPost);
    }

    @Override
    public void deletePost(Long postId) {
        jobPostRepository.deleteById(postId);
    }

    @Override
    public JobPostDetail getDetail(Long postId) {
        JobPost jobPost = getJobPost(postId);
        List<JobPost> jobPosts = jobPostRepository.findAllByCompanyCompanyIdOrderByCreatedAtDesc(jobPost.getCompany().getCompanyId());

        List<JobPostDetailList> jobPostDetailLists = jobPosts.stream()
                .filter(post -> !post.getPostId().equals(postId))
                .map(this::convertToDetailDto)
                .collect(Collectors.toList());

        return new JobPostDetail(jobPost, jobPostDetailLists);
    }
    private JobPostDetailList convertToDetailDto(JobPost jobPost) {
        return new JobPostDetailList(
                jobPost.getPostId(),
                jobPost.getPosition(),
                jobPost.getCompany().getRegion()
        );
    }


    private JobPostDto.Response convertToDto(JobPost jobPost) {
        return new JobPostDto.Response(
                jobPost.getPostId(),
                jobPost.getCompany().getCompanyName(),
                jobPost.getCompany().getNation(),
                jobPost.getCompany().getRegion(),
                jobPost.getPosition(),
                jobPost.getReward(),
                jobPost.getStack(),
                jobPost.getCreatedAt(),
                jobPost.getUpdatedAt()
        );
    }


    private JobPost getJobPost(Long postId) {
        return jobPostRepository.findById(postId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 채용 공고 ID입니다.")
        );
    }

    public Company getCompany(long companyId) {
        return companyRepository.findById(companyId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 회사 ID입니다.")
        );
    }
}
