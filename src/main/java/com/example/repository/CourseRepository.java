package com.example.repository;

import com.example.entity.CourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface CourseRepository extends CrudRepository<CourseEntity, Integer>, PagingAndSortingRepository<CourseEntity, Integer> {

    Optional<CourseEntity> findByName(String name);

    Optional<CourseEntity> findByPrise(Double prise);

    Optional<CourseEntity> findByDuration(Long duration);

    Iterable<CourseEntity> findByPriseBetween(Double beginPrise, Double endPrise);

    Iterable<CourseEntity> findByCreatedDateBetween(LocalDateTime begin,LocalDateTime end);

    Page<CourseEntity> findAllByPrise(Double prise, Pageable pageable);

    Page<CourseEntity> findAllByCreatedDateBetween(LocalDateTime fromDate, LocalDateTime toDate, Pageable pageable);
}
