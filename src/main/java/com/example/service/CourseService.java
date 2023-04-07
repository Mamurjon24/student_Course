package com.example.service;


import com.example.dto.CourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourseDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrise(entity.getPrise());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDuration(entity.getDuration());
        return dto;
    }

    public boolean update(Integer id, CourseDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrise(dto.getPrise());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());
        courseRepository.save(entity);
        return true;
    }

    public CourseDTO crate(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setDuration(dto.getDuration());
        entity.setPrise(dto.getPrise());
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourseDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourseDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrise(entity.getPrise());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setDuration(entity.getDuration());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public boolean delete(Integer id) {
        CourseEntity entity = get(id);
        courseRepository.delete(entity);
        return true;
    }

    public CourseEntity get(Integer id) {
        Optional<CourseEntity> optional = courseRepository.findById(id);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Student not found: " + id);
        }
        return optional.get();
    }

    public void test() {
//        StudentEntity entity = studentRepository.findByPhone("123");
//        System.out.println(entity);

   //     Optional<CourceDTO> entity = courseRepository.findByPhone("123");
    //    System.out.println(entity);
    }
    public CourseDTO convertToDTO(CourseEntity entity) {
        CourseDTO dto = new CourseDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrise(entity.getPrise());
        dto.setDuration(entity.getDuration());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
    public CourseEntity convertToEntity(CourseDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setPrise(dto.getPrise());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public CourseDTO getByName(String name) {
        Optional<CourseEntity> optional = courseRepository.findByName(name);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + name);
        }
        return convertToDTO(optional.get());
    }

    public CourseDTO getByPrise(Double prise) {
        Optional<CourseEntity> optional = courseRepository.findByPrise(prise);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + prise);
        }
        return convertToDTO(optional.get());
    }

    public CourseDTO getByDuration(Long duration) {
        Optional<CourseEntity> optional = courseRepository.findByDuration(duration);
        if (optional.isEmpty()) {
            throw new AppBadRequestException("Course not found: " + duration);
        }
        return convertToDTO(optional.get());
    }

    public List<CourseDTO> getByBeetwenPrise(Double beginPrise, Double endPrise) {
        Iterable<CourseEntity> iterable = courseRepository.findByPriseBetween(beginPrise,endPrise);
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrise(entity.getPrise());
            dto.setDuration(entity.getDuration());
            dto.setCreatedDate(entity.getCreatedDate());
            dtoList.add(dto);
        });
        return dtoList;
    }

    public List<CourseDTO> getByBeetwenDate(LocalDate beginDate, LocalDate endDate) {
        LocalDateTime begin = beginDate.atStartOfDay();
        LocalDateTime end = endDate.atStartOfDay();
        Iterable<CourseEntity> iterable = courseRepository.findByCreatedDateBetween(begin,end);
        List<CourseDTO> dtoList = new LinkedList<>();
        iterable.forEach(entity -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrise(entity.getPrise());
            dto.setCreatedDate(entity.getCreatedDate());
            dto.setDuration(entity.getDuration());
            dtoList.add(dto);
        });
        return dtoList;
    }
}
