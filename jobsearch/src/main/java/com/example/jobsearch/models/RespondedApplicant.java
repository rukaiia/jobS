package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@Data
public class RespondedApplicant {
    private int id;

    private int resumeId;

    private int vacancyId;
    private boolean confirmation;
}
