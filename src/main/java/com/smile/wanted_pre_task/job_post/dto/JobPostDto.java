package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.config.CalculateTime;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

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
    @NoArgsConstructor
    public static class Response {
        private Long postId;
        private String companyName;
        private String nation;
        private String region;
        private String position;
        private int reward;
        private String stack;
        private String createdAt;
        private String updatedAt;

        public Response(Long postId, String companyName, String nation, String region, String position, int reward, String stack, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.postId = postId;
            this.companyName = companyName;
            this.nation = nation;
            this.region = region;
            this.position = position;
            this.reward = reward;
            this.stack = stack;
            this.createdAt = CalculateTime.dateformatForPost(createdAt);
            this.updatedAt = CalculateTime.dateformatForPost(updatedAt);
        }
    }


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Update {
        private String title;
        private String position;
        private String region;
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

        public JobPost toUpdateEntity() {
            return JobPost.builder()
                    .title(title)
                    .position(position)
                    .reward(reward)
                    .content(content)
                    .stack(stack)
                    .build();
        }
    }
}
