package com.smile.wanted_pre_task.application.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Getter
@Builder
@NoArgsConstructor
public class ApplicationDto {
    @NotNull(message = "채용 공고 ID는 필수 입력 값입니다.")
    private Long postId;
    @NotNull(message = "회원 ID는 필수 입력 값입니다.")
    private UUID memberId;

    public ApplicationDto(Long postId, UUID memberId) {
        this.postId = postId;
        this.memberId = memberId;
    }
}
