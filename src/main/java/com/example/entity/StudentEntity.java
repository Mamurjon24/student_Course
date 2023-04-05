package com.example.entity;

import com.example.dto.StudentDTO;
import com.example.enums.StudentGender;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "student")
public class StudentEntity extends StudentDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "sur_name")
    private String surname;
    @Column(name = "level")
    private Integer level;
    @Column(name = "age")
    private Integer age;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private StudentGender gender;
    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
