package com.example.service;

import com.example.dto.StudentCourseDTO;
import com.example.entity.StudentCourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;

    public StudentCourseDTO getById(Integer id) {
        StudentCourseEntity entity = get(id);
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setId(entity.getId());
        dto.setStudentId(entity.getStudentId());
        dto.setCourseId(entity.getCourseId());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());
        return dto;
    }

    public boolean update(Integer id, StudentCourseDTO dto) {
        StudentCourseEntity entity = get(id);
        entity.setStudentId(dto.getStudentId());
        entity.setCourseId(dto.getCourseId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setMark(dto.getMark());
        studentCourseRepository.save(entity);
        return true;
    }

    public StudentCourseDTO crate(StudentCourseDTO dto) {
        StudentCourseEntity entity = new StudentCourseEntity();
        if (dto.getStudentId() == null ) {
            throw new AppBadRequestException("Student qani?");
        }
        entity.setStudentId(dto.getStudentId());
        if (dto.getCourseId() == null) {
            throw new AppBadRequestException("Course qani?");
        }
        entity.setCourseId(dto.getCourseId());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setMark(dto.getMark());
        studentCourseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentCourseDTO> getAll() {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAll();
        List<StudentCourseDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudentId(entity.getStudentId());
            dto.setCourseId(entity.getCourseId());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setMark(entity.getMark());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean delete(Integer id) {
        StudentCourseEntity entity = get(id);
        studentCourseRepository.delete(entity);
        return true;
    }

    public StudentCourseEntity get(Integer id) {
        Optional<StudentCourseEntity> optional = studentCourseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public void test() {
//        StudentEntity entity = studentRepository.findByPhone("123");
//        System.out.println(entity);

        //       Optional<StudentEntity> entity = studentRepository.findByPhone("123");
        //       System.out.println(entity);
    }
}
