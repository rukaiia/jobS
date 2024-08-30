package com.example.jobsearch.service;

import com.example.jobsearch.dto.user.UserDto;
import com.example.jobsearch.dto.vacancy.InputVacancyDto;
import com.example.jobsearch.dto.vacancy.VacancyDto;
import com.example.jobsearch.exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;

import java.util.List;

public interface VacancyService {
    VacancyDto getVacancyById(int id) throws UserNotFoundException;

    UserDto getUserByVacancy(int vacancyId);

    List<VacancyDto> getVacancies();

    List<VacancyDto> getActiveVacancies();

    Integer getVacanciesCount();

    Integer getVacanciesWithCategoryCount(int categoryId);

    List<VacancyDto> getVacanciesWithPaging(Integer page, Integer pageSize, String category);

    List<VacancyDto> getVacanciesByCategory(String category);

    // Служебный метод
    Boolean isVacancyInSystem(int id);

    Boolean isUsersVacanciesInSystem(int userId);

    // Служебный метод
    Boolean isVacancyActive(int vacancyId);

    void createVacancy(InputVacancyDto vacancyDto);

    void changeVacancy(InputVacancyDto vacancyDto);

    HttpStatus delete(Authentication auth, int id);

    List<VacancyDto> getAllVacanciesByCompany(int userId);

    List<VacancyDto> getVacanciesByCategoryAndUser(int userId, String category);

    void getVacancy(int id, Model model);
}
