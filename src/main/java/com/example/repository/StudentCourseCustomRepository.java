package com.example.repository;

import com.example.dto.CoursefFlterRequestDTO;
import com.example.dto.StudentCourseFilterRequestDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentCourseEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class StudentCourseCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CourseEntity> getAll() {
        Query query = this.entityManager.createQuery("Select sc From StudentCourseEntity  as sc");
        List studentCourseList = query.getResultList();
        return studentCourseList;
    }

    public List<StudentCourseEntity> filter(StudentCourseFilterRequestDTO filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select sc From StudentCourseEntity sc INNER JOIN  sc.course c " +
                " INNER JOIN sc.student s " +
                " where visible = true ");

        if (filterDTO.getStudentId() != null) {
            builder.append(" and s.id = :studentId");
            params.put("studentId", filterDTO.getStudentId());
        }
        if (filterDTO.getCourseId() != null) {
            builder.append(" and c.id = :courseId");
            params.put("courseId", filterDTO.getStudentId());
        }
        if (filterDTO.getStudentName() != null) {
            builder.append(" and s.name = :studentName");
            params.put("student", filterDTO.getStudentName());
        }
        if (filterDTO.getCourseName() != null) {
            builder.append(" and c.name = :courseName");
            params.put("courseName", filterDTO.getCourseName());
        }
        if (filterDTO.getMarkFrom() != null && filterDTO.getMarkTo() != null) {
            builder.append(" and sc.mark between :markFrom and :markTo");
            params.put("markFrom", filterDTO.getMarkFrom());
            params.put("markTo", filterDTO.getMarkTo());
        } else if (filterDTO.getMarkFrom() != null) {
            builder.append(" and sc.mark >= :markFrom ");
            params.put("markFrom", filterDTO.getMarkFrom());
        } else if (filterDTO.getMarkTo() != null) {
            builder.append(" and sc.mark >= :markTo ");
            params.put("markTo", filterDTO.getMarkTo());
        }
        if (filterDTO.getDateFrom() != null && filterDTO.getDateTo() != null) {
            builder.append(" and c.createdDate between :dateFrom and :dateTo ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MAX));
        } else if (filterDTO.getDateFrom() != null) {
            builder.append(" and c.createdDate >= :dateFrom ");
            params.put("dateFrom", LocalDateTime.of(filterDTO.getDateFrom(), LocalTime.MIN));
        } else if (filterDTO.getDateTo() != null) {
            builder.append(" and c.createdDate <= :dateTo ");
            params.put("dateTo", LocalDateTime.of(filterDTO.getDateTo(), LocalTime.MIN));
        }
        // ....
        Query query = this.entityManager.createQuery(builder.toString());
        for (Map.Entry<String, Object> param : params.entrySet()) {
            query.setParameter(param.getKey(), param.getValue());
        }

        List courseList = query.getResultList();
        return courseList;
    }
}
