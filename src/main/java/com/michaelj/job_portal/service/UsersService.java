package com.michaelj.job_portal.service;

import com.michaelj.job_portal.entity.JobSeekerProfile;
import com.michaelj.job_portal.entity.RecruiterProfile;
import com.michaelj.job_portal.entity.Users;
import com.michaelj.job_portal.repository.JobSeekerProfileRepository;
import com.michaelj.job_portal.repository.RecruiterProfileRepository;
import com.michaelj.job_portal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class UsersService {
    private final UserRepository userRepository;
    private final JobSeekerProfileRepository jobSeekerRepository;
    private final RecruiterProfileRepository recruiterRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UsersService(UserRepository userRepository, JobSeekerProfileRepository jobSeekerRepository, RecruiterProfileRepository recruiterRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.jobSeekerRepository = jobSeekerRepository;
        this.recruiterRepository = recruiterRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public Users addNewUser(Users user) {
       user.setActive(true);
       user.setRegistrationDate(new Date(System.currentTimeMillis()));
       user.setPassword(passwordEncoder.encode(user.getPassword()));
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

    public Object getCurrentUserProfile() {
      Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

      if (!(authentication instanceof AnonymousAuthenticationToken)) {
          String username = authentication.getName();
          Users user =  userRepository.findByEmail(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));

          int userId = user.getId();

          if(authentication.getAuthorities().contains(new SimpleGrantedAuthority("Recruiter"))){
            RecruiterProfile recruiter = recruiterRepository.findById(userId).orElse(new RecruiterProfile());
            return recruiter;
          } else {
            JobSeekerProfile jobSeeker = jobSeekerRepository.findById(userId).orElse(new JobSeekerProfile());
            return jobSeeker;
          }


      }
        return null;
    }
}
