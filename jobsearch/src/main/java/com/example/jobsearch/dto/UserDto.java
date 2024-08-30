package com.example.jobsearch.dto;

import lombok.*;

@AllArgsConstructor

@Data
@Getter
@Setter
public class UserDto {
    private int id;
    private String name;
    private String surname;
    private int age;
    private String email;
    private String phoneNumber;
    private String avatar;
    private String accountType;

}
