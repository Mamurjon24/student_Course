package com.example.controller;

import com.example.dto.StudentCourseDTO;
import com.example.dto.StudentDTO;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/studentcourse")
public class StudentCourseController {
    @Autowired
    private StudentCourseService studentCourseService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentCourseDTO>> getAll() {
        List<StudentCourseDTO> list = studentCourseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentCourseDTO dto = studentCourseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentCourseDTO dto) {
        StudentCourseDTO response = studentCourseService.create(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentCourseDTO dto) {
        return ResponseEntity.ok(studentCourseService.update(id, dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentCourseService.delete(id));
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        studentCourseService.test();
        return ResponseEntity.ok().build();
    }
    @GetMapping(value = "/getMarkByStudentAndCreatedDate")
    public ResponseEntity<List<StudentCourseDTO>> getMarkByStudentAndCreatedDate (@RequestParam("studentId") Integer studentId,
                                                                                  @RequestParam("startDate") LocalDate startDate,
                                                                                  @RequestParam("endDate") LocalDate endDate) {
        List<StudentCourseDTO> list = studentCourseService.getMarkByStudentAndCreatedDate(studentId,startDate,endDate);
        return ResponseEntity.ok(list);
    }


}
