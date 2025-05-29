package com.michaelj.job_portal.controller;

import com.michaelj.job_portal.entity.JobPostActivity;
import com.michaelj.job_portal.entity.JobSeekerProfile;
import com.michaelj.job_portal.entity.JobSeekerSave;
import com.michaelj.job_portal.entity.Users;
import com.michaelj.job_portal.service.JobPostActivityService;
import com.michaelj.job_portal.service.JobSeekerProfileService;
import com.michaelj.job_portal.service.JobSeekerSaveService;
import com.michaelj.job_portal.service.UsersService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
public class JobSeekerSaveController {
    private final UsersService usersService;
    private final JobSeekerProfileService jobSeekerProfileService;
    private final JobSeekerSaveService jobSeekerSaveService;
    private final JobPostActivityService jobPostActivityService;

    public JobSeekerSaveController(UsersService usersService, JobSeekerProfileService jobSeekerProfileService,
                                   JobSeekerSaveService jobSeekerSaveService, JobPostActivityController jobPostActivityController, JobPostActivityService jobPostActivityService) {
        this.usersService = usersService;
        this.jobSeekerProfileService = jobSeekerProfileService;
        this.jobSeekerSaveService = jobSeekerSaveService;
        this.jobPostActivityService = jobPostActivityService;
    }

    @PostMapping("job-details/save/{id}")
    public String save(@PathVariable("id") int id, JobSeekerSave jobJobSeekerSave) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            String currentUsername = authentication.getName();
            Users users = usersService.findByEmail(currentUsername);
            Optional<JobSeekerProfile> jobSeekerProfile = jobSeekerProfileService.getJobSeekerProfile(users.getUserId());
            JobPostActivity jobDetails = jobPostActivityService.getJobDetails(id);
            if (jobSeekerProfile.isPresent() && jobDetails != null) {
                jobJobSeekerSave.setJob(jobDetails);
                jobJobSeekerSave.setUserId(jobSeekerProfile.get());
            } else {
                throw new RuntimeException("User not found");
            }
            jobSeekerSaveService.addNew(jobJobSeekerSave);
        }
        return "redirect:/dashboard/";
    }

    @GetMapping("saved-jobs")
    public String savedJobs(Model model) {
        List<JobPostActivity> jobPost = new ArrayList<>();
        Object currentUserProfile = usersService.getCurrentUserProfile();

        List<JobSeekerSave>  jobSeekerSaveList = jobSeekerSaveService.getCandidatesJob((JobSeekerProfile) currentUserProfile);
        for(JobSeekerSave jobSeekerSave : jobSeekerSaveList) {
            jobPost.add(jobSeekerSave.getJob());

        }
        model.addAttribute("jobPost", jobPost);
        model.addAttribute("user", currentUserProfile);

        return "saved-jobs";
    }
}
