package com.michaelj.job_portal.repository;

import com.michaelj.job_portal.entity.RecruiterProfile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecruiterProfileRepository extends JpaRepository<RecruiterProfile, Integer> {
}
