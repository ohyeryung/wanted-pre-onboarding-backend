package com.smile.wanted_pre_task.job_post.service;

import com.smile.wanted_pre_task.job_post.dto.*;

import java.util.List;

public interface JobPostService {
    void posting(JobPostDto.Post jobPost);

    List<JobPostDto.Response> listing();

    JobPostDto.Update updatePost(Long postId, JobPostDto.Update jobPostUpdateReqDto);

    void deletePost(Long postId);

    JobPostDetail getDetail(Long postId);
}