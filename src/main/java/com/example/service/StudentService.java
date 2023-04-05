package com.example.service;

import com.example.dto.CourseDTO;
import com.example.dto.StudentCourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;

    public StudentDTO getById(Integer id) {
        StudentEntity entity = get(id);
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurName(entity.getSurname());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setLevel(entity.getLevel());
        return dto;
    }

    public boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDate(dto.getCreatedDate());
        studentRepository.save(entity);
        return true;
    }

    public StudentDTO crate(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        entity.setName(dto.getName());
        if (dto.getSurName() == null || dto.getSurName().isBlank()) {
            throw new AppBadRequestException("Surname qani?");
        }
        entity.setSurname(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        studentRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentDTO> getAll() {
        Iterable<StudentEntity> iterable = studentRepository.findAll();
        List<StudentDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurname());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean delete(Integer id) {
        StudentEntity entity = get(id);
        studentRepository.delete(entity);
        return true;
    }

    public StudentEntity get(Integer id) {
        Optional<StudentEntity> optional = studentRepository.findById(id);
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
    public StudentDTO convertToDTO(StudentEntity entity) {
        StudentDTO dto = new StudentDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurName(entity.getSurName());
        dto.setLevel(entity.getLevel());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public StudentEntity convertToEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }
}
