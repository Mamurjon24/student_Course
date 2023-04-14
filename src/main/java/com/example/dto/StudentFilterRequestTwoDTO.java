package com.example.dto;

import com.example.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class StudentFilterRequestTwoDTO {
    private Integer id;
    private String name;
    private String surName;
    private Integer level;
    private Integer age;
    private StudentGender gender;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
