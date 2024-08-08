package com.smile.wanted_pre_task.company.repository;

import com.smile.wanted_pre_task.company.domain.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
