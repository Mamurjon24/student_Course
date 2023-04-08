package com.example.service;

import com.example.dto.StudentCourseDTO;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.mapper.StudentMarkAndCourseNameMapper;
import com.example.repository.StudentCourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
                LocalDateTime.of(startDate,LocalTime.MIN),LocalDateTime.of(endDate,LocalTime.MAX));
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

    public List<StudentCourseDTO> getMarkByStudentAndTwoDates(Integer studentId, LocalDate fromDate, LocalDate toDate) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAllByStudentIdAndCreatedDateBeforeAndCreatedDateAfter(studentId,
                LocalDateTime.of(fromDate,LocalTime.MAX),LocalDateTime.of(toDate,LocalTime.MAX));
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
    public List<StudentCourseDTO> findAllByStudentIdOrderByCreatedDateDesc(Integer id) {
        Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAllByStudentIdOrderByCreatedDateDesc(id);
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
    public List<Float> findAllByStudentIdOrderByCreatedDateDescSql(Integer studentId) {
        List<Float> markList = studentCourseRepository.findAllByStudentIdOrderByCreatedDateDescSql(studentId);
        return markList;
    }

    public List<StudentCourseDTO> getMarkByStudentIdAndCourseIdByCreatedDate(Integer studentId, Integer courseId) {
       List<StudentCourseEntity> entityList = studentCourseRepository.getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDesc(studentId,courseId);
        List<StudentCourseDTO> dtoList = new LinkedList<>();
       entityList.forEach(entity -> {
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
    public List<Float> getMarkByStudentIdAndCourseIdByCreatedDateSql(Integer studentId, Integer courseId) {
        List<Float> markList = studentCourseRepository.getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDescsql(studentId,courseId);
    return markList;
    }

    public StudentMarkAndCourseNameMapper getStudentCourseNameByMarkAsc(Integer studentId) {
        StudentMarkAndCourseNameMapper markAndCourseNameMapper = studentCourseRepository.getTopStudentCourseMarkByStudentId(studentId);
        return markAndCourseNameMapper;
    }
    public List<Float> findMaxThreeMarksOfStudent(Integer studentId) {
        List<Float> markList = studentCourseRepository.getMaxThreeMarksOfStudent(studentId);
        return markList;
    }

    public Float getFirstMarkByStudentId(Integer id) {
        Float mark = studentCourseRepository.getFirstMarkByStudentId(id);
        return mark;
    }

    public Float getFirstMarkByStudentIdAndCourseId(Integer studentId, Integer courseId) {
        Float mark = studentCourseRepository.getFirstMarkByStudentIdAndCourseId(studentId,courseId);
        return mark;
    }

    public Float getMaxMarkOfStudentByCourse(Integer studentId, Integer courseId) {
        Float mark = studentCourseRepository.getMaxMarkOfStudentByCourse(studentId,courseId);
        return mark;
    }

    public Float getAvgMarkOfStudent(Integer id) {
        Float mark = studentCourseRepository.getAvgMarkOfStudent(id);
        return mark;
    }

    public Float getAvgMarkOfStudentByCourse(Integer studentId, Integer courseId) {
        Float mark = studentCourseRepository.getAvgMarkOfStudentByCourse(studentId,courseId);
        return mark;
    }

    public Long getCountMarksOfStudentFromGivenMark(Integer studentId, Float givenMark) {
        Long count = studentCourseRepository.getCountMarksOfStudentFromGivenMark(studentId,givenMark);
        return count;
    }
}
