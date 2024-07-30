package com.smile.wanted_pre_task.job_post.repository;

import com.smile.wanted_pre_task.job_post.domain.JobPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobPostRepository extends JpaRepository<JobPost, Long> {
}
