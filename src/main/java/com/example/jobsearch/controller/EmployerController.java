package com.example.jobsearch.controller;

import com.example.jobsearch.service.ProfileService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@Controller
@RequiredArgsConstructor
public class EmployerController {
    private final VacancyService vacancyService;
    private final ResumeService resumeService;
    private final ProfileService profileService;

    @GetMapping("/employer/resumes")
    public String getPage(Model model,
                          @RequestParam(name = "page", defaultValue = "0") Integer page,
                          @RequestParam(name = "pageSize", defaultValue = "5") Integer pageSize) {

        model.addAttribute("resumes", resumeService.getResumesWithPaging(page, pageSize));


        model.addAttribute("currentPage", page);
        model.addAttribute("pageSize", pageSize);

        return "employer/resumes";
    }
}

