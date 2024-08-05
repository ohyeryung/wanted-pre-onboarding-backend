package com.smile.wanted_pre_task.job_post.dto;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.global.config.CalculateTime;
import com.smile.wanted_pre_task.job_post.domain.JobPost;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class JobPostDto {

    @Builder
    @Getter
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Post {
        
        @NotNull(message = "회사 ID는 필수 입력 값입니다.")
        private Long companyId;
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;
        @NotBlank(message = "채용 포지션은 필수 입력 값입니다.")
        private String position;

        @NotNull(message = "채용 보상금은 필수 입력 값입니다.")
        private int reward;
        private String content;
        @NotBlank(message = "사용기술은 필수 입력 값입니다.")
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
        @NotBlank(message = "제목은 필수 입력 값입니다.")
        private String title;
        @NotBlank(message = "채용 포지션은 필수 입력 값입니다.")
        private String position;

        @NotNull(message = "채용 보상금은 필수 입력 값입니다.")
        private int reward;
        private String content;
        @NotBlank(message = "사용기술은 필수 입력 값입니다.")
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

    @Getter
    @NoArgsConstructor
    public static class Apply {
        @NotNull(message = "채용 공고 ID는 필수 입력 값입니다.")
        private Long postId;
        @NotNull(message = "회원 ID는 필수 입력 값입니다.")
        private Long memberId;

        public Apply(Long postId, Long memberId) {
            this.postId = postId;
            this.memberId = memberId;
        }
    }
}
