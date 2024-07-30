package com.smile.wanted_pre_task.job_post.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class JobPostResDto {
    private Long postId;
    private String companyName;
    private String nation;
    private String region;
    private String position;
    private int reward;
    private String stack;

}