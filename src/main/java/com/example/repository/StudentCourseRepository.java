package com.example.repository;

import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer> {

}
