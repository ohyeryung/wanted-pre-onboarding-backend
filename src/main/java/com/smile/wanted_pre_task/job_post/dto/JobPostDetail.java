package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.config.CalculateTime;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.Getter;

import java.util.List;

@Getter
public class JobPostDetail {
    private Long postId;
    private String title;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String stack;
    private String content;
    private String createdAt;
    private String updatedAt;
    private List<JobPostDetailList> jobPostDetailLists;

    public JobPostDetail() {

    }

    public JobPostDetail(JobPost jobPost, List<JobPostDetailList> JobPostDetailList) {
        this.postId = jobPost.getPostId();
        this.title = jobPost.getTitle();
        this.companyName = jobPost.getCompany().getCompanyName();
        this.nation = jobPost.getCompany().getNation();
        this.region = jobPost.getCompany().getRegion();
        this.position = jobPost.getPosition();
        this.reward = jobPost.getReward();
        this.stack = jobPost.getStack();
        this.content = jobPost.getContent();
        this.createdAt = CalculateTime.dateformatForPost(jobPost.getCreatedAt());
        this.updatedAt = CalculateTime.dateformatForPost(jobPost.getUpdatedAt());
        this.jobPostDetailLists = JobPostDetailList;
    }

}