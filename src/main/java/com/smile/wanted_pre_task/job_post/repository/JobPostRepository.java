package com.smile.wanted_pre_task.job_post.repository;

import com.smile.wanted_pre_task.job_post.domain.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {

    List<JobPost> findAllByOrderByCreatedAtDesc();
    List<JobPost> findAllByCompanyCompanyIdOrderByCreatedAtDesc (Long companyId);
}
