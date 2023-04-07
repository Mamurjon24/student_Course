package com.example.repository;

import com.example.dto.CourseDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer> {

    Optional<CourseEntity> findByName(String name);

    Optional<CourseEntity> findByPrise(Double prise);

    Optional<CourseEntity> findByDuration(Long duration);

    Iterable<CourseEntity> findByPriseBetween(Double beginPrise, Double endPrise);

    Iterable<CourseEntity> findByCreatedDateBetween(LocalDateTime begin,LocalDateTime end);
}
