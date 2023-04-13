package com.example.repository;

import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends CrudRepository<StudentEntity, Integer>, PagingAndSortingRepository<StudentEntity, Integer> {

    StudentEntity findByName(String name);

    Optional<StudentEntity>findBySurName(String surName);

    Optional<StudentEntity> findByLevel(Integer level);

    Optional<StudentEntity> findByAge(Integer age);

    Iterable<StudentEntity> findByGender(StudentGender gender);

    Iterable<StudentEntity> findAllByCreatedDay(LocalDateTime date);

    Iterable<StudentEntity> findByCreatedDayBetween(LocalDateTime begin, LocalDateTime end);

    /// SQL Query

//    @Transactional
//    @Modifying
//    @Query("update StudentEntity set level = :level where id = :id")
//    Integer changeLevel(@Param("id") Integer id,@Param("level") Integer level);//
//    @Query("FROM StudentEntity where name like ?1")
//    List<StudentEntity> findByNamAndAge (String name);
//    @Query("select  new StudentEntity (id,name,surName) FROM StudentEntity ")
//    List<StudentEntity> findByNameAndAge ();

    Page<StudentEntity> findAllByLevel(Integer Level, Pageable pageable);

    Page<StudentEntity> findAllByGender(StudentGender gender, Pageable pageable);



}
