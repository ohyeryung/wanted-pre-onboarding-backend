package com.smile.wanted_pre_task.application.domain;

import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.member.domain.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_APPLY_HISTORY")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Application {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "APPLY_ID")
    private Long applyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "POST_ID")
    private JobPost jobPost;

    @ManyToOne(fetch  = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_ID")
    private Member member;

}
