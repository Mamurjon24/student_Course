package com.example.dto;

import com.example.enums.StudentGender;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudenFilterByGenderDTO {
    private StudentGender gender;
}
