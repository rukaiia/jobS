package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.security.Timestamp;
import java.util.Date;
@AllArgsConstructor
@Getter
@Setter
@Data
public class Message {
    private int id;

    private int respondedApplicants;
    private String content;
    private Timestamp timestamp;
}

