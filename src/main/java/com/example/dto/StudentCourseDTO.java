package com.example.dto;

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
    private Integer StudentId;
    private Integer CourseId;
    private LocalDateTime createdDate = LocalDateTime.now();
    private Float mark;

}
