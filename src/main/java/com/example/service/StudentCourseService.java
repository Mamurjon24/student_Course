package com.example.service;

import com.example.dto.StudentCourseDTO;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class StudentCourseService {
    @Autowired
    private StudentCourseRepository studentCourseRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private CourseService courseService;

    public StudentCourseDTO getById(Integer id) {
        StudentCourseEntity entity = get(id);
        StudentCourseDTO dto = new StudentCourseDTO();
        dto.setId(entity.getId());
        dto.setStudent(studentService.convertToDTO(entity.getStudent()));
        dto.setCourse(courseService.convertToDTO(entity.getCourse()));
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setMark(entity.getMark());
        return dto;
    }

    public boolean update(Integer id, StudentCourseDTO dto) {
        StudentCourseEntity entity = get(id);
        entity.setStudent(studentService.convertToEntity(dto.getStudent()));
        entity.setCourse(courseService.convertToEntity(dto.getCourse()));
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setMark(dto.getMark());
        studentCourseRepository.save(entity);
        return true;
    }

    public StudentCourseDTO create(StudentCourseDTO dto) {
        StudentCourseEntity entity = new StudentCourseEntity();
        entity.setStudent(studentService.convertToEntity(dto.getStudent()));
        entity.setCourse(courseService.convertToEntity(dto.getCourse()));
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setMark(dto.getMark());
        entity = studentCourseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<StudentCourseDTO> getAll() {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAll();
        List<StudentCourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudent(studentService.convertToDTO(entity.getStudent()));
            dto.setCourse(courseService.convertToDTO(entity.getCourse()));
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

    public List<StudentCourseDTO> getMarkByStudentAndCreatedDate(Integer studentId,LocalDate startDate, LocalDate endDate) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAllByStudentIdAndCreatedDateBetween(studentId,
                startDate.atStartOfDay(),LocalDateTime.of(endDate,LocalTime.MAX));
        List<StudentCourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentCourseDTO dto = new StudentCourseDTO();
            dto.setId(entity.getId());
            dto.setStudent(studentService.convertToDTO(entity.getStudent()));
            dto.setCourse(courseService.convertToDTO(entity.getCourse()));
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setMark(entity.getMark());
            dtoList.add(dto);
        });
        return dtoList;
    }
}
