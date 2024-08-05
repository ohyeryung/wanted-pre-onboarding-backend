package com.smile.wanted_pre_task.job_post.service;

import com.smile.wanted_pre_task.job_post.dto.*;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface JobPostService {
    JobPostDto.Response posting(JobPostDto.Post jobPost);

    List<JobPostDto.Response> listing();

    JobPostDto.Update updatePost(Long postId, JobPostDto.Update jobPostUpdateReqDto);

    void deletePost(Long postId);

    JobPostDetail getDetail(Long postId);

    List<JobPostDto.Response> search(String query, Pageable pageable);

    JobPostDto.Apply apply(Long postId, Long memberId);
}