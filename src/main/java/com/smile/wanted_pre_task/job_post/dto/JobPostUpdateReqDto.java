package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.Getter;

@Getter
public class JobPostUpdateReqDto {
    private String title;
    private String position;
    private int reward;
    private String content;
    private String stack;

    public JobPostUpdateReqDto() {

    }

    public JobPostUpdateReqDto(JobPost jobPost) {
        this.title = jobPost.getTitle();
        this.position = jobPost.getPosition();
        this.reward = jobPost.getReward();
        this.content = jobPost.getContent();
        this.stack = jobPost.getStack();
    }

}
