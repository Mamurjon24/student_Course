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
public class CourseDTO {
    private Integer id;
    private String name;
    private Double prise;
    private Long duration;
    private LocalDateTime createdDate = LocalDateTime.now();

}
