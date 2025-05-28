package com.michaelj.job_portal.repository;

import com.michaelj.job_portal.entity.JobPostActivity;
import com.michaelj.job_portal.entity.JobSeekerApply;
import com.michaelj.job_portal.entity.JobSeekerProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerApplyRepository extends JpaRepository<JobSeekerApply, Integer> {

    List<JobSeekerApply> findByUserId(JobSeekerProfile userId);

    List<JobSeekerApply> findByJob(JobPostActivity job);

}
