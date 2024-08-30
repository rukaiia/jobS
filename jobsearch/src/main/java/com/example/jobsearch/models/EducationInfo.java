package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
@Data
public class EducationInfo {
    private int id;
    private int resumeId;

    private String institution;
    private String program;
    private Date startDate;

    private Date endDate;

    private String degree;
}
