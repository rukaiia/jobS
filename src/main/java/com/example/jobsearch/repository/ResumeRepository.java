package com.example.jobsearch.repository;

import com.example.jobsearch.model.Resume;
import io.micrometer.common.lang.NonNullApi;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ResumeRepository extends JpaRepository<Resume, Integer> {
    List<Resume> findAllByIsActiveTrue();

    List<Resume> findAllByCategoryNameAndIsActiveTrue(String category);

    List<Resume> findAllByUserIdAndIsActiveTrue(Integer id);

    Boolean existsByUserId(Integer userId);

    Optional<Resume> findById(Integer integer);

    Integer countAllByIsActiveTrue();

    @Transactional
    @Modifying
    @Query(value = "update resumes" +
            " set name = :name, category_id = :categoryId, salary = :salary, is_active = :isActive, update_time = :updateTime" +
            " where id = :id;", nativeQuery = true)
    void updateBy(String name, Integer categoryId, Double salary, Boolean isActive, LocalDateTime updateTime, Integer id);

    @Query(value = "select * from RESUMES where IS_ACTIVE = true " +
            "limit :perPage " +
            "offset :offset;", nativeQuery = true)
    List<Resume> findPagedResumes(Integer perPage, Integer offset);
}
