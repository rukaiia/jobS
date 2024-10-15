package com.example.jobsearch.controller;

import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ProfileService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("employer")
public class EmployerController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ProfileService profileService;
    private final CategoryService categoryService;

    @GetMapping("vacancies/add")
    public String addVacancy(Model model) {
        model.addAttribute("categories", categoryService.getAllCategories());

        return "employer/createVacancyTemplate";
    }

    @GetMapping("/resumes")
    public String getResumes( Model model) {
        model.addAttribute("resumes", resumeService.getResumes());

        return "employer/resumes";
    }

//    @GetMapping("resumes/{id}")
//    public String getResume(@PathVariable int id, Model model) {
//        resumeService.getResumeById(id);
//        return "employer/resumes";
//    }

//    @GetMapping("/resumes/{id}")
//    public String getResume(@PathVariable int id, Model model) {
//        model.addAttribute("resume", resumeService.getResumeById(id));
//        return "employer/resumes";
//    }

    @GetMapping("resumes/{id}")
    public String getResumeId(@PathVariable int id, Model model) {
        resumeService.getResume(id,model);

        return "employer/resumes";
    }
}

