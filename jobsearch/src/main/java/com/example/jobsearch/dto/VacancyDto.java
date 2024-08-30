package com.example.jobsearch.dto;

import lombok.*;

import java.security.Timestamp;
import java.util.List;
@AllArgsConstructor
@Getter
@Setter
@Builder

public class VacancyDto {
    private long id;

    private String name;
    private String description;

    private int categoryId;

    private double salary;
    private int expFrom;
    private int expTo;
    private boolean isActive;
    private int authorId;
    private Timestamp createdDate;

    private Timestamp updateTime;

    private List<Integer> applicantIds;
}

