package com.example.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class StudentCourseFilterRequestDTO {
    private Integer id;
    private Integer studentId;
    private Integer courseId;
    private Float markFrom;
    private Float markTo;
    private LocalDate dateFrom;
    private LocalDate dateTo;
    private String studentName;
    private String courseName;
}
