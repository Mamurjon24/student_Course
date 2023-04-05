package com.example.dto;

import com.example.entity.CourseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentCourseDTO {
    private Integer id;
    private StudentDTO student;
    private CourseDTO course;
    private Float mark;
    private LocalDateTime createdDate = LocalDateTime.now();

}
