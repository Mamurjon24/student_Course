package com.example.dto;

import com.example.enums.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDTO {
    private Integer id;
    private String name;
    private String surName;
    private Integer level;
    private Integer age;
    private StudentGender gender;
    private LocalDateTime createdDate = LocalDateTime.now();
}
