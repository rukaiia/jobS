package com.example.jobsearch.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@AllArgsConstructor
@Getter
@Setter
public class ContactsInfo {
    private int id;
    private int typeId;
    private int resumeId;
    private String value;
}
