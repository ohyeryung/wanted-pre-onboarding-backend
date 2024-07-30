package com.smile.wanted_pre_task.company.repository;

import com.smile.wanted_pre_task.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
