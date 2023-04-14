package com.example.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class CoursefFlterRequestDTO {
    private Integer id;
    private String name;
    private Double prise;
    private Long duration;
    private LocalDate dateFrom;
    private LocalDate dateTo;
}
