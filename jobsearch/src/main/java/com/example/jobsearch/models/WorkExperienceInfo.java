package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class WorkExperienceInfo {
    private int id;
    private int resumeId;

    private int years;
    private String companyName;

    private String position;
    private String responsibilities;
}

