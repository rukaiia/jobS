package com.example.jobsearch.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "parent_id")
    private Category parent;

    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "parent")
    private List<Category> categories;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Resume> resumes;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "category")
    private List<Vacancy> vacancies;
}
