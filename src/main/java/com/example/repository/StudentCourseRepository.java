package com.example.repository;

import com.example.entity.StudentCourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {
    Iterable<StudentCourseEntity> findAllByStudentIdAndCreatedDateBetween(Integer studentId, LocalDateTime startDate,LocalDateTime endDate);
}
