package com.example.jobsearch.service;

import com.example.jobsearch.dto.user.AuthUserDto;
import com.example.jobsearch.dto.user.EmployeeFindDto;
import com.example.jobsearch.dto.user.UserAvatarFileDto;
import com.example.jobsearch.dto.user.UserDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

public interface UserService {

    Map<String, Object> forgotPassword(HttpServletRequest request);



    Map<String, Object> resetPasswordGet(String token);

    Map<String, Object> resetPasswordPost(HttpServletRequest request);

    List<UserDto> getUsers();

    UserDto getUserById(int id);

    List<UserDto> getUserByName(String name);

    UserDto getUserByPhone(String phone);

    UserDto getUserByEmail(String email);

    Boolean isUserInSystem(String email);

    Boolean isUserInSystem(int id);

    Boolean isEmployee(String userEmail);

    Boolean isEmployee(int userId);

    Boolean isEmployer(String userEmail);

    Boolean isEmployer(int userId);

    HttpStatus createUser(UserDto user, MultipartFile file);

    void uploadUserAvatar(UserAvatarFileDto avatarDto);

    ResponseEntity<?> downloadAvatar(int userId);

    List<UserDto> getEmployee(EmployeeFindDto employeeFindDto);

    List<UserDto> getEmployer(String name);

    void updateUser(UserDto user, MultipartFile file);

    HttpStatus login(AuthUserDto authUserDto);
}
