package com.example.jobsearch.controller;

import com.example.jobsearch.dto.user.UserDto;
import com.example.jobsearch.service.ProfileService;
import com.example.jobsearch.service.ResumeService;
import com.example.jobsearch.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@Controller
@RequestMapping("users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final ProfileService profileService;
    private final ResumeService resumeService;



    @GetMapping("register")
    public String createUser() {
        return "user/register";
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String createUser(UserDto user, @RequestParam(name = "file") MultipartFile file) {
        userService.createUser(user, file);
        return "redirect: /";
    }

    @GetMapping("profile")
    public String getProfile(Model model) {
        profileService.getProfile(model);
        return "user/profile";
    }

    @GetMapping("profile/{email}")
    public String viewProfile(@PathVariable String email, Model model) {
        profileService.getProfile(email, model);
        return "user/decorationProfile";
    }

    @GetMapping("update")
    public String updateProfile() {
        return "user/update";
    }

    @PostMapping("update")
    @ResponseStatus(HttpStatus.SEE_OTHER)
    public String updateUser(UserDto user, @RequestParam(name = "file") MultipartFile file) {
        userService.updateUser(user, file);
        return "redirect:/users/profile";
    }

    @GetMapping("login")
    public String login() {
        return "/login";
    }

    @GetMapping("/forgot_password")
    public String showForgotPasswordForm() {
        return "user/forgot_password_form";
    }

    @PostMapping("forgot_password")
    public String forgotPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(userService.forgotPassword(request));

        return "user/forgot_password_form";
    }

    @GetMapping("reset_password")
    public String resetPassword(@RequestParam String token, Model model) {
        model.addAllAttributes(userService.resetPasswordGet(token));
        return "user/reset_password_form";
    }

    @PostMapping("reset_password")
    public String resetPassword(HttpServletRequest request, Model model) {
        model.addAllAttributes(userService.resetPasswordPost(request));

        return "user/message";
}


}

