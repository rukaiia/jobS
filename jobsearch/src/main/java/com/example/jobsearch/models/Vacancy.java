package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
@Builder

public class Vacancy {
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

