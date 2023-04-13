package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CourseFilterByDateBetween {
    private LocalDateTime fromDate;
    private LocalDateTime toDate;
}
