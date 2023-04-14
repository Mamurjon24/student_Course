package com.example.repository;

import com.example.dto.CoursefFlterRequestDTO;
import com.example.dto.StudentFilterRequestTwoDTO;
import com.example.entity.CourseEntity;
import com.example.entity.StudentEntity;
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
public class CourseCustomRepository {
    @Autowired
    private EntityManager entityManager;

    public List<CourseEntity> getAll() {
        Query query = this.entityManager.createQuery("Select c From CourseEntity  as c");
        List courseList = query.getResultList();
        return courseList;
    }

    public List<CourseEntity> filter(CoursefFlterRequestDTO filterDTO) {
        Map<String, Object> params = new HashMap<>();
        StringBuilder builder = new StringBuilder();
        builder.append("Select c From CourseEntity as c where id in (1,2,3,4,5) ");

        if (filterDTO.getName() != null) {
            builder.append(" and c.name = :name");
            params.put("name", filterDTO.getName());
        }
        if (filterDTO.getPrise() != null) {
            builder.append(" and c.prise = :prise");
            params.put("prise", filterDTO.getPrise());
        }
        if (filterDTO.getDuration() != null) {
            builder.append(" and c.duration = :duration");
            params.put("duration", filterDTO.getDuration());
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
