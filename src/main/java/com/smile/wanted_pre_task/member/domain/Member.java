package com.smile.wanted_pre_task.member.domain;

import com.smile.wanted_pre_task.job_post.domain.JobPost;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "TB_MEMBER")
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long memberId;
    @Column(name = "MEMBER_EMAIL")
    private String memberEmail;
    @Column(name = "PASSWORD")
    private String password;
    @ManyToOne
    @JoinColumn(name = "POST_ID")
    private JobPost jobPost;

    public void setJobPost(JobPost jobPost) {
        this.jobPost = jobPost;
    }

}
