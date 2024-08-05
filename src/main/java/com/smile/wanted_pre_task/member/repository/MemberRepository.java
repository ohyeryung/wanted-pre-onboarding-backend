package com.smile.wanted_pre_task.member.repository;

import com.smile.wanted_pre_task.member.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
