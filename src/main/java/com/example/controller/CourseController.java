package com.example.controller;


import com.example.dto.*;
import com.example.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(value = "/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/list")
    public ResponseEntity<List<CourseDTO>> getAll() {
        List<CourseDTO> list = courseService.getAll();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id) {
        CourseDTO dto = courseService.getById(id);
        return ResponseEntity.ok(dto);
    }

    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody CourseDTO dto) {
        CourseDTO response = courseService.crate(dto);
        return ResponseEntity.ok(response);
    }

    @PutMapping(value = "/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody CourseDTO dto) {
        return ResponseEntity.ok(courseService.update(id, dto));
    }

    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(courseService.delete(id));
    }

    @GetMapping(value = "/test")
    public ResponseEntity<?> test() {
        courseService.test();
        return ResponseEntity.ok().build();
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<?> getByName(@PathVariable("name") String name) {
        CourseDTO dto = courseService.getByName(name);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByPrise/{prise}")
    public ResponseEntity<?> getByPrise(@PathVariable("prise") Double prise) {
        CourseDTO dto = courseService.getByPrise(prise);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByDuration/{duration}")
    public ResponseEntity<?> getByDuration(@PathVariable("duration") Long duration) {
        CourseDTO dto = courseService.getByDuration(duration);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/getByBeetwenPrise")
    public ResponseEntity<List<CourseDTO>> getByBeetwenPrise(@RequestParam("beginPrise") Double beginPrise,
                                                             @RequestParam("endPrise") Double endPrise) {
        List<CourseDTO> list = courseService.getByBeetwenPrise(beginPrise, endPrise);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/getByBeetwenDate")
    public ResponseEntity<List<CourseDTO>> getByBeetwenDate(@RequestParam("beginDate") LocalDate beginDate,
                                                            @RequestParam("endDate") LocalDate endDate) {
        List<CourseDTO> list = courseService.getByBeetwenDate(beginDate, endDate);
        return ResponseEntity.ok(list);
    }

    @PostMapping(value = "/paging")
    public ResponseEntity<Page<CourseDTO>> paging(@RequestParam(value = "page", defaultValue = "1") int page,
                                                  @RequestParam(value = "size", defaultValue = "2") int size) {
        Page<CourseDTO> response = courseService.pagingtion(page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/pagingbyCoursePrise")
    public ResponseEntity<Page<CourseDTO>> pagingbyCoursePrise(@RequestParam(value = "page", defaultValue = "1") int page,
                                                               @RequestParam(value = "size", defaultValue = "2") int size,
                                                               @RequestBody CourseFilterByPrise filter) {
        Page<CourseDTO> response = courseService.paginationWithPrise(filter.getPrise(), page, size);
        return ResponseEntity.ok(response);
    }

    @PostMapping(value = "/pagingbyCourseDateBetween")
    public ResponseEntity<Page<CourseDTO>> pagingbyCourseDateBetween(@RequestParam(value = "page", defaultValue = "1") int page,
                                                                     @RequestParam(value = "size", defaultValue = "2") int size,
                                                                     @RequestBody CourseFilterByDateBetween fromfilter,
                                                                     @RequestBody CourseFilterByDateBetween tofilter) {
        Page<CourseDTO> response = courseService.paginationWithDateBetween(fromfilter.getFromDate(),tofilter.getToDate(), page, size);
        return ResponseEntity.ok(response);
    }
    @PostMapping(value = "/filter")
    public ResponseEntity<?> filter(@RequestBody CoursefFlterRequestDTO filterDTO) {
        courseService.filter(filterDTO);
        return ResponseEntity.ok().build();
    }


}
