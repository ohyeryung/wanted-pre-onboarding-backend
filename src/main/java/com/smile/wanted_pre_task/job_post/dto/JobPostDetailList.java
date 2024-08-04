package com.smile.wanted_pre_task.job_post.dto;

import lombok.Getter;

@Getter
public class JobPostDetailList {
    private Long postId;
    private String position;
    private String region;

    public JobPostDetailList() {

    }
    public JobPostDetailList(Long postId, String position, String region) {
        this.postId = postId;
        this.position = position;
        this.region = region;
    }
}
