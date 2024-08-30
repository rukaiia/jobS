package com.example.jobsearch.dto;

import lombok.*;

import java.security.Timestamp;

@NoArgsConstructor
@Data

public class ResumeDto {
    private int id;

    private int applicantId;

    private String name;
    private int categoryId;

    private double salary;
    private boolean isActive;
    private Timestamp createdDate;

    private Timestamp updateTime;





}


