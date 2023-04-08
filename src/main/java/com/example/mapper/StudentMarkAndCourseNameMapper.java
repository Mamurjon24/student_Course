package com.example.mapper;

import lombok.Data;

@Data
public class StudentMarkAndCourseNameMapper {
    private String courseName;
    private Float studentCourseMark;

    public StudentMarkAndCourseNameMapper(String courseName, Float studentCourseMark) {
        this.courseName = courseName;
        this.studentCourseMark = studentCourseMark;
    }
}
