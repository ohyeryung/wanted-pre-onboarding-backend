package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class JobPostDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        private Long companyId;
        private String title;
        private String position;
        private int reward;
        private String content;
        private String stack;

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

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Response {
        private Long postId;
        private String companyName;
        private String nation;
        private String region;
        private String position;
        private int reward;
        private String stack;
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private String title;
        private String position;
        private int reward;
        private String content;
        private String stack;

        public Update(JobPost jobPost) {
            this.title = jobPost.getTitle();
            this.position = jobPost.getPosition();
            this.reward = jobPost.getReward();
            this.content = jobPost.getContent();
            this.stack = jobPost.getStack();
        }
    }
}
