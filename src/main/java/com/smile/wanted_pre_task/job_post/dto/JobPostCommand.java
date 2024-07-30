package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.Getter;

@Getter
public class JobPostCommand {
    private Long companyId;
    private String title;
    private String position;
    private int reward;
    private String content;
    private String stack;

    public JobPostCommand() {

    }

    public JobPost toRegisterEntity(Company company) {
        return JobPost.builder()
                .title(title)
                .position(position)
                .reward(reward)
                .content(content)
                .stack(stack)
                .company(company)
                .build();
    }
}