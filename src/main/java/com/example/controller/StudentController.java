package com.example.controller;

import com.example.dto.StudenFilterByGenderDTO;
import com.example.dto.StudentDTO;
import com.example.dto.StudentFilterRequestDTO;
import com.example.dto.StudentFilterRequestTwoDTO;
import com.example.enums.StudentGender;
import com.example.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/list")
    public ResponseEntity<List<StudentDTO>> getAll() {
        List<StudentDTO> list = studentService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        StudentDTO dto = studentService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody StudentDTO dto) {
        StudentDTO response = studentService.crate(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody StudentDTO dto) {
        return ResponseEntity.ok(studentService.update(id, dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(studentService.delete(id));
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        studentService.test();
        studentService.test1();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        StudentDTO dto = studentService.getByName(name);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getBySurName/{surName}")
    public ResponseEntity<?> getBySurName(@PathVariable("surName") String surName) {
        StudentDTO dto = studentService.getBySurName(surName);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByLevel/{level}")
    public ResponseEntity<?> getByLevel(@PathVariable("level") Integer level) {
        StudentDTO dto = studentService.getByLevel(level);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByAge/{age}")
    public ResponseEntity<?> getByAge(@PathVariable("age") Integer age) {
        StudentDTO dto = studentService.getByAge(age);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByGender/{gender}")
    public ResponseEntity<List<StudentDTO>> getByGender(@PathVariable("gender") StudentGender gender) {
        List<StudentDTO> list = studentService.getByGender(gender);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByDate/{date}")
    public ResponseEntity<List<StudentDTO>> getByDate(@PathVariable("date") LocalDate date) {
        List<StudentDTO> list = studentService.getByDate(date);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByBeetwenDate")
    public ResponseEntity<List<StudentDTO>> getByBeetwenDate(@RequestParam("beginDate") LocalDate beginDate,
                                                             @RequestParam("endDay") LocalDate endDate) {
        List<StudentDTO> list = studentService.getByBeetwenDate(beginDate, endDate);
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/paging")
    public ResponseEntity<Page<StudentDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                   @RequestParam(value = "size", defaultValue = "2") int size) {
        Page<StudentDTO> response = studentService.pagingtion(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/pagingbyStudentLevel")
    public ResponseEntity<Page<StudentDTO>> pagingWithLevel(@RequestParam(value = "page", defaultValue = "1") int page,
                                                            @RequestParam(value = "size", defaultValue = "2") int size,
                                                            @RequestBody StudentFilterRequestDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithLevel(filter.getLevel(), page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/pagingbyStudentGender")
    public ResponseEntity<Page<StudentDTO>> pagingbyStudentGender(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                  @RequestParam(value = "size", defaultValue = "2") int size,
                                                                  @RequestBody StudenFilterByGenderDTO filter) {
        Page<StudentDTO> response = studentService.paginationWithGender(filter.getGender(), page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/filter")
    public ResponseEntity<?> filter(@RequestBody StudentFilterRequestTwoDTO filterDTO) {
        studentService.filter(filterDTO);
        return ResponseEntity.ok().build();
    }
}
