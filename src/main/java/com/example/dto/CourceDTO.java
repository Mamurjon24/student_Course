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
public class CourceDTO  {
    private Integer id;
    private String name;
    private Double price;
    private Long duration;
    private LocalDateTime createdDate = LocalDateTime.now();

}
