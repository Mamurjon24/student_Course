package com.example.controller;

import com.example.dto.*;
import com.example.entity.StudentCourseEntity;
import com.example.entity.StudentEntity;
import com.example.mapper.StudentMarkAndCourseNameMapper;
import com.example.service.StudentCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
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

    @GetMapping(value = "/test2")
    public ResponseEntity<?> test2() {
        studentCourseService.test();
        return ResponseEntity.ok().build();
    }

    @GetMapping(value = "/getMarkByStudentAndCreatedDate")
    public ResponseEntity<List<StudentCourseDTO>> getMarkByStudentAndCreatedDate(@RequestParam("studentId") Integer studentId,
                                                                                 @RequestParam("startDate") LocalDate startDate,
                                                                                 @RequestParam("endDate") LocalDate endDate) {
        List<StudentCourseDTO> list = studentCourseService.getMarkByStudentAndCreatedDate(studentId, startDate, endDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getAllByStudentIdAndCreatedDateBeforeAndCreatedDateAfter")
    public ResponseEntity<List<StudentCourseDTO>> findAllByStudentIdAndCreatedDateBeforeAndCreatedDateAfter(@RequestParam("studentId") Integer studentId,
                                                                                                            @RequestParam("startDate") LocalDate startDate,
                                                                                                            @RequestParam("endDate") LocalDate endDate) {
        List<StudentCourseDTO> list = studentCourseService.getMarkByStudentAndTwoDates(studentId, startDate, endDate);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/findAllByStudentIdOrderByCreatedDateDesc/{id}")
    public ResponseEntity<List<StudentCourseDTO>> findAllByStudentIdOrderByCreatedDateDesc(@PathVariable("id") Integer id) {
        List<StudentCourseDTO> list = studentCourseService.findAllByStudentIdOrderByCreatedDateDesc(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/findAllByStudentIdOrderByCreatedDateDescSql")
    public ResponseEntity<List<Float>> findAllByStudentIdOrderByCreatedDateDescSql(@RequestParam("studentId") Integer studentId) {
        List<Float> list = studentCourseService.findAllByStudentIdOrderByCreatedDateDescSql(studentId);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDesc")
    public ResponseEntity<List<StudentCourseDTO>> getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDesc(@RequestParam("studentId") Integer studentId,
                                                                                                                       @RequestParam("courseId") Integer courseId) {
        List<StudentCourseDTO> list = studentCourseService.getMarkByStudentIdAndCourseIdByCreatedDate(studentId, courseId);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getMarkByStudentIdAndCourseIdByCreatedDateSql")
    public ResponseEntity<List<Float>> getMarkByStudentIdAndCourseIdByCreatedDateSql(@RequestParam("studentId") Integer studentId,
                                                                                     @RequestParam("courseId") Integer courseId) {
        List<Float> list = studentCourseService.getMarkByStudentIdAndCourseIdByCreatedDateSql(studentId, courseId);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/getStudentCourseNameByMarkAscSql")
    public ResponseEntity<?> getStudentCourseNameByMarkAscSql(@RequestParam("studentId") Integer studentId) {
        StudentMarkAndCourseNameMapper studentCourseNameByMark = studentCourseService.getStudentCourseNameByMarkAsc(studentId);
        return ResponseEntity.ok(studentCourseNameByMark);
    }

    @GetMapping(value = "/findMaxThreeMarksOfStudent/{id}")
    public ResponseEntity<List<Float>> findMaxThreeMarksOfStudent(@PathVariable("id") Integer id) {
        List<Float> list = studentCourseService.findMaxThreeMarksOfStudent(id);
        return ResponseEntity.ok(list);
    }

    @GetMapping(value = "/findFirstMarkByStudentId/{id}")
    public ResponseEntity<?> findFirstMarkByStudentId(@PathVariable("id") Integer id) {
        Float mark = studentCourseService.getFirstMarkByStudentId(id);
        return ResponseEntity.ok(mark);
    }

    @GetMapping(value = "/findFirstMarkByStudentIdAndCourseId")
    public ResponseEntity<?> findFirstMarkByStudentIdAndCourseId(@RequestParam("studentId") Integer studentId,
                                                                 @RequestParam("courseId") Integer courseId) {
        Float mark = studentCourseService.getFirstMarkByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(mark);
    }

    @GetMapping(value = "/findMaxMarkOfStudentByCourse")
    public ResponseEntity<?> findMaxMarkOfStudentByCourse(@RequestParam("studentId") Integer studentId,
                                                          @RequestParam("courseId") Integer courseId) {
        Float mark = studentCourseService.getMaxMarkOfStudentByCourse(studentId, courseId);
        return ResponseEntity.ok(mark);
    }

    @GetMapping(value = "/findAvgMarkOfStudent/{id}")
    public ResponseEntity<?> findAvgMarkOfStudent(@PathVariable("id") Integer id) {
        Float mark = studentCourseService.getAvgMarkOfStudent(id);
        return ResponseEntity.ok(mark);
    }

    @GetMapping(value = "/findAvgMarkOfStudentByCourse")
    public ResponseEntity<?> findAvgMarkOfStudentByCourse(@RequestParam("studentId") Integer studentId,
                                                          @RequestParam("courseId") Integer courseId) {
        Float mark = studentCourseService.getAvgMarkOfStudentByCourse(studentId, courseId);
        return ResponseEntity.ok(mark);
    }

    @GetMapping(value = "/findCountMarksOfStudentFromGivenMark")
    public ResponseEntity<?> findCountMarksOfStudentFromGivenMark(@RequestParam("studentId") Integer studentId,
                                                                  @RequestParam("givenMark") Float givenMark) {
        Long count = studentCourseService.getCountMarksOfStudentFromGivenMark(studentId, givenMark);
        return ResponseEntity.ok(count);
    }

    @GetMapping(value = "/findMaxMarkByCourseId/{id}")
    public ResponseEntity<?> findMaxMarkByCourseId(@PathVariable("id") Integer id) {
        Float mark = studentCourseService.getMaxMarkByCourseId(id);
        return ResponseEntity.ok(mark);
    }
    @GetMapping(value = "/findAvgMarkByCourseId/{id}")
    public ResponseEntity<?> getAvgMarkByCourseId(@PathVariable("id") Integer id) {
        Float mark = studentCourseService.getAvgMarkByCourseId(id);
        return ResponseEntity.ok(mark);
    }
    @GetMapping(value = "/findCountMarkByCourseId/{id}")
    public ResponseEntity<?> findCountMarkByCourseId(@PathVariable("id") Integer id) {
        Long mark = studentCourseService.getCountMarkByCourseId(id);
        return ResponseEntity.ok(mark);
    }
    @GetMapping(value = "/paging")
    public ResponseEntity<Page<StudentCourseDTO>> paging(@RequestParam(value = "page",defaultValue = "1") int page,
                                                         @RequestParam(value = "size",defaultValue = "2") int size) {
        Page<StudentCourseDTO> response = studentCourseService.pagingtion(page,size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/pagingbyStudentId")
    public ResponseEntity<Page<StudentCourseDTO>> pagingWithStudntId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "2") int size,
                                                                     @RequestBody StudentCourseGetStudentByIdDTO filter) {
        Page<StudentCourseDTO> response = studentCourseService.paginationWithStidentId(filter.getId(), page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/pagingbyCourseId")
    public ResponseEntity<Page<StudentCourseDTO>> pagingWithCoursetId(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "2") int size,
                                                                     @RequestBody StudentCourseGetByCourseIdDTO filter) {
        Page<StudentCourseDTO> response = studentCourseService.pagingWithCoursetId(filter.getId(), page, size);
        return ResponseEntity.ok(response);
    }

}
