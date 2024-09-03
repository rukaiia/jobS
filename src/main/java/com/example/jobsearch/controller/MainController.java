package com.example.jobsearch.controller;

import com.example.jobsearch.service.CategoryService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import com.example.jobsearch.service.VacancyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("")
@RequiredArgsConstructor
public class MainController {
    private final VacancyService vacancyService;
    private final UserService userService;
    private final CategoryService categoryService;
    private final ResumeService resumeService;


    @GetMapping
    public String getMainPage(Model model,
                              @RequestParam(name = "vacancyPage", defaultValue = "0") Integer vacancyPage,
                              @RequestParam(name = "vacancyPageSize", defaultValue = "5") Integer vacancyPageSize,
                              @RequestParam(name = "filter", defaultValue = "none") String Vcategory,
                              @RequestParam(name = "resumePage", defaultValue = "0") Integer resumePage,
                              @RequestParam(name = "resumePageSize", defaultValue = "5") Integer resumePageSize,
                              @RequestParam(name = "filter", defaultValue = "none") String Rcategory,
                              @RequestParam(name = "userPage", defaultValue = "0") Integer userPage,
                              @RequestParam(name = "userPageSize", defaultValue = "5") Integer userPageSize)

    {

        model.addAttribute("vacancies", vacancyService.getVacanciesWithPaging(vacancyPage, vacancyPageSize, Vcategory));
        model.addAttribute("vacancyPage", vacancyPage);
        model.addAttribute("vacancyPageSize", vacancyPageSize);


        model.addAttribute("categories", categoryService.getAllCategories());


        model.addAttribute("resumes", resumeService.getResumesWithPaging(resumePage,resumePageSize));
        model.addAttribute("resumePage", resumePage);
        model.addAttribute("resumePageSize", resumePageSize);

        model.addAttribute("user", userService.getUsers());


        return "main";
    }


    @GetMapping("vacancies/{id}")
    public String getVacancy(@PathVariable int id, Model model) {
        vacancyService.getVacancy(id, model);
        return "employer/vacancy";
    }

    @GetMapping("login")
    public String getTestLogin() {
        return "login";
    }

    @GetMapping("resumes/{id}")
    public String getResume(@PathVariable int id, Model model) {
        resumeService.getResume(id,model);
        return "employer/resumes";
    }
//    @GetMapping("/resumes")
//    public String getResumes(Model model){
//        model.addAttribute("resumes" , resumeService.getResumes());
//        return "employer/resumes";
//    }
}
