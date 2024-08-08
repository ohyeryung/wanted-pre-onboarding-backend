package com.smile.wanted_pre_task.application.repository;

import com.smile.wanted_pre_task.application.domain.Application;
import com.smile.wanted_pre_task.job_post.domain.JobPost;
import com.smile.wanted_pre_task.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApplicationRepository extends JpaRepository<Application, Long> {

    Optional<Application> findByJobPostAndMember(JobPost jobPost, Member member);
}
