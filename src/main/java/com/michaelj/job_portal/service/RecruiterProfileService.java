package com.michaelj.job_portal.service;

import com.michaelj.job_portal.entity.RecruiterProfile;
import com.michaelj.job_portal.repository.RecruiterProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecruiterProfileService {
    private final RecruiterProfileRepository recruiterProfileRepository;

    @Autowired
    public RecruiterProfileService(RecruiterProfileRepository recruiterProfileRepository) {
        this.recruiterProfileRepository = recruiterProfileRepository;
    }

    public Optional<RecruiterProfile> getRecruiterProfile(Integer userAccountId) {
        return recruiterProfileRepository.findById(userAccountId);
    }
}
