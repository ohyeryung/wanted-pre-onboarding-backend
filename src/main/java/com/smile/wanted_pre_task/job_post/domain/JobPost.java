package com.smile.wanted_pre_task.job_post.domain;

import com.smile.wanted_pre_task.company.domain.Company;
import com.smile.wanted_pre_task.config.Timestamped;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Table(name = "TB_JOBPOST")
@Builder
@Getter
public class JobPost extends Timestamped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "POST_ID")
    private Long postId;

    @Column(name = "TITLE")
    private String title;
    @Column(name = "POSITION")
    private String position;
    @Column(name = "REWARD")
    private int reward;
    @Column(name = "CONTENT")
    private String content;
    @Column(name = "STACK")
    private String stack;
    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    public JobPost(Long postId, String title, String position, int reward, String content, String stack, Company company) {
        this.postId = postId;
        this.title = title;
        this.position = position;
        this.reward = reward;
        this.content = content;
        this.stack = stack;
        this.company = company;
    }

    public JobPost() {

    }

    public void update(JobPost jobPostUpdateReqDto) {
        this.title = jobPostUpdateReqDto.getTitle();
        this.position = jobPostUpdateReqDto.getPosition();
        this.reward = jobPostUpdateReqDto.getReward();
        this.content = jobPostUpdateReqDto.getContent();
        this.stack = jobPostUpdateReqDto.getStack();
    }
}
