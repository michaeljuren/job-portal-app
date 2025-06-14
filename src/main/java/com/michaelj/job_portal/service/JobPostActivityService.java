package com.michaelj.job_portal.service;

import com.michaelj.job_portal.entity.*;
import com.michaelj.job_portal.repository.JobPostActivityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class JobPostActivityService {

    private final JobPostActivityRepository jobPostActivityRepository;

    @Autowired
    public JobPostActivityService(JobPostActivityRepository jobPostActivityRepository) {
        this.jobPostActivityRepository = jobPostActivityRepository;
    }

    public JobPostActivity addNew(JobPostActivity jobPostActivity) {
        return jobPostActivityRepository.save(jobPostActivity);
    }

    public List<RecruiterJobDto> getRecruiterJobs(int recruiter) {
        List<IRecruiterJobs> recruiterJobs = jobPostActivityRepository.getRecruiterJobs(recruiter);

        List<RecruiterJobDto> recruiterJobDtoList = new ArrayList<>();
        for (IRecruiterJobs rec : recruiterJobs) {
            JobLocation location = new JobLocation(rec.getLocationId(), rec.getCity(), rec.getState(), rec.getCountry());
            JobCompany company = new JobCompany(rec.getCompanyId(), rec.getName(), "");
            recruiterJobDtoList.add(new RecruiterJobDto(rec.getTotalCandidates(), rec.getJob_post_id(), rec.getJob_title(), location, company));
        }

        return recruiterJobDtoList;
    }

    public JobPostActivity getJobDetails(int id) {
        return jobPostActivityRepository.findById(id).orElseThrow(() -> new RuntimeException("Job not found"));
    }

    public List<JobPostActivity> getAll() {
        return jobPostActivityRepository.findAll();

    }

    public List<JobPostActivity> search(String job, String location, List<String> type, List<String> remote, LocalDate searchDate) {
        return Objects.isNull(searchDate) ? jobPostActivityRepository.searchWithoutDate(job, location, remote, type) :
                jobPostActivityRepository.search(job, location, remote, type, searchDate);
    }
}
