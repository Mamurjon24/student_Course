package com.example.service;

import com.example.dto.CourceDTO;
import com.example.entity.CourseEntity;
import com.example.exp.AppBadRequestException;
import com.example.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public CourceDTO getById(Integer id) {
        CourseEntity entity = get(id);
        CourceDTO dto = new CourceDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setPrice(entity.getPrice());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setDuration(entity.getDuration());
        return dto;
    }

    public boolean update(Integer id, CourceDTO dto) {
        CourseEntity entity = get(id);
        entity.setName(dto.getName());
        entity.setPrice(dto.getPrice());
        entity.setDuration(dto.getDuration());
        entity.setCreatedDate(dto.getCreatedDate());
        courseRepository.save(entity);
        return true;
    }

    public CourceDTO crate(CourceDTO dto) {
        CourseEntity entity = new CourseEntity();
        entity.setName(dto.getName());
        if (dto.getName() == null || dto.getName().isBlank()) {
            throw new AppBadRequestException("Name qani?");
        }
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setDuration(dto.getDuration());
        entity.setPrice(dto.getPrice());
        courseRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }

    public List<CourceDTO> getAll() {
        Iterable<CourseEntity> iterable = courseRepository.findAll();
        List<CourceDTO> dtoList = new LinkedList<>();

        iterable.forEach(entity -> {
            CourceDTO dto = new CourceDTO();
            dto.setId(entity.getId());
            dto.setName(entity.getName());
            dto.setPrice(entity.getPrice());
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
}
