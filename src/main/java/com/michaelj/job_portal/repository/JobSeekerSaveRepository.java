package com.michaelj.job_portal.repository;

import com.michaelj.job_portal.entity.JobPostActivity;
import com.michaelj.job_portal.entity.JobSeekerApply;
import com.michaelj.job_portal.entity.JobSeekerProfile;
import com.michaelj.job_portal.entity.JobSeekerSave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JobSeekerSaveRepository extends JpaRepository<JobSeekerSave, Integer> {

    List<JobSeekerSave> findByUserId(JobSeekerProfile userAccountId);

    List<JobSeekerSave> findByJob(JobPostActivity job);
}
