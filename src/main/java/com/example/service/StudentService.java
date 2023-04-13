package com.example.service;

import com.example.dto.StudentDTO;
import com.example.entity.StudentEntity;
import com.example.enums.StudentGender;
import com.example.exp.AppBadRequestException;
import com.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
        dto.setSurName(entity.getSurName());
        dto.setAge(entity.getAge());
        dto.setGender(entity.getGender());
        dto.setCreatedDay(entity.getCreatedDay());
        dto.setLevel(entity.getLevel());
        return dto;
    }

    public boolean update(Integer id, StudentDTO dto) {
        StudentEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setSurName(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDay(dto.getCreatedDay());
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
        entity.setSurName(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDay(dto.getCreatedDay());
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
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
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
        dto.setCreatedDay(entity.getCreatedDay());
        return dto;
    }

    public StudentEntity convertToEntity(StudentDTO dto) {
        StudentEntity entity = new StudentEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurName(dto.getSurName());
        entity.setAge(dto.getAge());
        entity.setGender(dto.getGender());
        entity.setLevel(dto.getLevel());
        entity.setCreatedDay(dto.getCreatedDay());
        return entity;
    }

    public StudentDTO getByName(String name) {
        StudentEntity entity =  studentRepository.findByName(name);
        if (entity == null) {
            throw new AppBadRequestException("Student not found: " + name);
        }
        return convertToDTO(entity);
    }

    public StudentDTO getBySurName(String surName) {
        Optional<StudentEntity> optional = studentRepository.findBySurName(surName);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + surName);
        }
        return convertToDTO(optional.get());
    }

    public StudentDTO getByLevel(Integer level) {
        Optional<StudentEntity> optional = studentRepository.findByLevel(level);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + level);
        }
        return convertToDTO(optional.get());
    }

    public StudentDTO getByAge(Integer age) {
        Optional<StudentEntity> optional = studentRepository.findByAge(age);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + age);
        }
        return convertToDTO(optional.get());
    }

    public List<StudentDTO> getByGender(StudentGender gender) {
        Iterable<StudentEntity> iterable = studentRepository.findByGender(gender);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<StudentDTO> getByDate(LocalDate date) {
        LocalDateTime day = date.atStartOfDay();
        Iterable<StudentEntity> iterable = studentRepository.findAllByCreatedDay(day);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<StudentDTO> getByBeetwenDate(LocalDate beginDate, LocalDate endDate) {
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay();
        Iterable<StudentEntity> iterable = studentRepository.findByCreatedDayBetween(begin,end);
        List<StudentDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        });
        return dtoList;
    }
    public Page<StudentDTO> pagingtion(int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        // Iterable<StudentCourseEntity> iterable = studentCourseRepository.findAll(sort);
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAll(pageable);
        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList,pageable,totalCount);
        return response;
    }
    public Page<StudentDTO> paginationWithLevel(Integer level, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "id");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByLevel(level, pageable);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList,pageable,totalCount);
        return response;
    }

    public Page<StudentDTO> paginationWithGender(StudentGender gender, int page, int size) {
        Sort sort = Sort.by(Sort.Direction.DESC, "createdDate");
        Pageable pageable = PageRequest.of(page - 1, size, sort);
        Page<StudentEntity> pageObj = studentRepository.findAllByGender(gender, pageable);

        Long totalCount = pageObj.getTotalElements();
        List<StudentEntity> entityList = pageObj.getContent();
        List<StudentDTO> dtoList = new LinkedList<>();
        for (StudentEntity entity : entityList) {
            StudentDTO dto = new StudentDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setSurName(entity.getSurName());
            dto.setAge(entity.getAge());
            dto.setGender(entity.getGender());
            dto.setCreatedDay(entity.getCreatedDay());
            dto.setLevel(entity.getLevel());
            dtoList.add(dto);
        }
        Page<StudentDTO> response = new PageImpl<StudentDTO>(dtoList,pageable,totalCount);
        return response;
    }
}
