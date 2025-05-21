package com.michaelj.job_portal.service;

import com.michaelj.job_portal.entity.JobSeekerProfile;
import com.michaelj.job_portal.entity.RecruiterProfile;
import com.michaelj.job_portal.entity.Users;
import com.michaelj.job_portal.repository.JobSeekerProfileRepository;
import com.michaelj.job_portal.repository.RecruiterProfileRepository;
import com.michaelj.job_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final JobSeekerProfileRepository jobSeekerRepository;
    private final RecruiterProfileRepository recruiterRepository;

    @Autowired
    public UsersService(UserRepository userRepository, JobSeekerProfileRepository jobSeekerRepository, RecruiterProfileRepository recruiterRepository) {
        this.userRepository = userRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.recruiterRepository = recruiterRepository;
    }

    public Users addNewUser(Users user) {
       user.setActive(true);
       user.setRegistrationDate(new Date(System.currentTimeMillis()));
       Users savedUser = userRepository.save(user);

       int userTypeId = user.getUserTypeId().getUserTypeId();
       if(userTypeId == 1) {
            recruiterRepository.save(new RecruiterProfile(savedUser));
       } else if(userTypeId == 2) {
            jobSeekerRepository.save(new JobSeekerProfile(savedUser));
       }

       return savedUser;
    }

    public Optional<Users> getUserByEmail(String email) {
        return userRepository.findByEmail(email); }
}
