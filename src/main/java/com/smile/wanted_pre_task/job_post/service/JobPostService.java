package com.smile.wanted_pre_task.job_post.service;

import com.smile.wanted_pre_task.job_post.dto.JobPostCommand;
import com.smile.wanted_pre_task.job_post.dto.JobPostResDto;
import com.smile.wanted_pre_task.job_post.dto.JobPostUpdateReqDto;

import java.util.List;

public interface JobPostService {
    void posting(JobPostCommand jobPostCommand);

    List<JobPostResDto> listing();

    JobPostUpdateReqDto updatePost(Long postId, JobPostUpdateReqDto jobPostUpdateReqDto);

    void deletePost(Long postId);
}