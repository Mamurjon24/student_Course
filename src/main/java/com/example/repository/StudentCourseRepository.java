package com.example.repository;

import com.example.entity.StudentCourseEntity;
import com.example.mapper.StudentMarkAndCourseNameMapper;
import com.example.mapperInterface.CourseInfoMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public interface StudentCourseRepository extends CrudRepository<StudentCourseEntity, Integer>,
        PagingAndSortingRepository<StudentCourseEntity, Integer> {

    //Iterable<StudentCourseEntity> findAllByStudentIdAndCreatedDateBetween(Integer studentId, LocalDateTime startDate,LocalDateTime endDate);

    @Query("FROM StudentCourseEntity WHERE student.id =:studentId AND createdDate >:startDate AND createdDate <:endDate")
    Iterable<StudentCourseEntity> findAllByStudentIdAndCreatedDateBetween(@Param("studentId") Integer studentId,
                                                                          @Param("startDate") LocalDateTime startDate,
                                                                          @Param("endDate") LocalDateTime endDate);

    // Iterable<StudentCourseEntity> findAllByStudentIdAndCreatedDateBeforeAndCreatedDateAfter(Integer studentId, LocalDateTime fromDate,LocalDateTime toDate);
    @Query("FROM StudentCourseEntity WHERE student.id =:studentId AND createdDate >:fromDate AND createdDate <:toDate")
    Iterable<StudentCourseEntity> findAllByStudentIdAndCreatedDateBeforeAndCreatedDateAfter(@Param("studentId") Integer studentId,
                                                                                            @Param("fromDate") LocalDateTime fromDate,
                                                                                            @Param("toDate") LocalDateTime toDate);

    //9. Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.
    Iterable<StudentCourseEntity> findAllByStudentIdOrderByCreatedDateDesc(Integer studentId);

    //9. Studentni olgan barcha baxolari vaqt boyicha kamayish tartibida sord qiling.
    @Query("SELECT mark FROM StudentCourseEntity WHERE student.id =:studentId ORDER BY createdDate ASC ")
    List<Float> findAllByStudentIdOrderByCreatedDateDescSql(@Param("studentId") Integer studentId);

    // 10. Studentni berilgan curse dan olgan baxolari vaqt boyicha kamayish tartibida sord qiling.
    List<StudentCourseEntity> getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDesc(Integer studentId, Integer courseId);

    // 10. Studentni berilgan curse dan olgan baxolari vaqt boyicha kamayish tartibida sord qiling.
    @Query("SELECT mark FROM StudentCourseEntity WHERE student.id =:studentId AND course.id =:courseId ORDER BY createdDate ASC ")
    List<Float> getStudentCourseEntitiesByStudentIdAndCourseIdOrderByCreatedDateDescsql(@Param("studentId") Integer studentId,
                                                                                        @Param("courseId") Integer courseId);

    //11. Studentni eng oxirda olgan baxosi, va curse nomi.

    @Query(value = "SELECT c.id, c.name " +
            " from  student_course as scm " +
            " inner join course as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    List<Object[]> findLastCourseMarkerAsNative(@Param("studentId") Integer studentId);

    @Query(value = "SELECT scm.student_id as sId, scm.mark as mark, " +
            "  c.id as cId, c.name as cName " +
            " from  student_course as scm " +
            " inner join course as c on c.id = scm.course_id " +
            " where scm.student_id = :studentId  " +
            "order by scm.created_date desc limit 1 ", nativeQuery = true)
    CourseInfoMapper findLastCourseMarkerAsNativeMapping(@Param("studentId") Integer studentId);

    @Query("SELECT new com.example.mapper.StudentMarkAndCourseNameMapper(sce.course.name,sce.mark)  FROM StudentCourseEntity as sce WHERE sce.student.id =:studentId ORDER BY sce.createdDate ASC limit 1")
    StudentMarkAndCourseNameMapper getTopStudentCourseMarkByStudentId(@Param("studentId") Integer studentId);

    //12.Studentni olgan eng katta 3ta baxosi.
    @Query("SELECT mark FROM StudentCourseEntity WHERE student.id =:studentId ORDER BY mark DESC limit 3")
    List<Float> getMaxThreeMarksOfStudent(@Param("studentId") Integer studentId);

    //13.Studentni eng birinchi olgan baxosi.
    @Query("SELECT mark FROM StudentCourseEntity WHERE student.id =:studentId ORDER BY createdDate ASC limit 1")
    Float getFirstMarkByStudentId(@Param("studentId") Integer studentId);

    //14. Studenti berilgan course dan olgan birinchi baxosi.
    @Query("SELECT mark FROM StudentCourseEntity WHERE student.id =:studentId AND course.id =:courseId ORDER BY createdDate ASC limit 1")
    Float getFirstMarkByStudentIdAndCourseId(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    //15. Studentni berilgan cuorse dan olgan eng baland baxosi.
    @Query("SELECT MAX (mark) FROM StudentCourseEntity WHERE student.id =:studentId AND course.id =:courseId ")
    Float getMaxMarkOfStudentByCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    //16. Studentni o'rtacha olgan baxolari.
    @Query("SELECT AVG (mark) FROM StudentCourseEntity WHERE student.id =:studentId ")
    Float getAvgMarkOfStudent(@Param("studentId") Integer studentId);

    //17. Studentni berilgan curse dan olgan o'rtacha baxolari.
    @Query("SELECT AVG (mark) FROM StudentCourseEntity WHERE student.id =:studentId AND course.id =:courseId ")
    Float getAvgMarkOfStudentByCourse(@Param("studentId") Integer studentId, @Param("courseId") Integer courseId);

    //18. Studentni berilgan baxodan katta bo'lgan baxolari soni.
//    @Query("SELECT COUNT(AVG (mark) > givenMark =:givenMark)  FROM StudentCourseEntity WHERE student.id =:studentId ")
//    Float getCountMarksOfStudendFromGivenMark(@Param("studentId") Integer studentId,@Param("givenMark") Integer givenMark);

    @Query("SELECT COUNT(sce) FROM StudentCourseEntity sce WHERE sce.student.id =:studentId AND sce.mark >:givenMark")
    Long getCountMarksOfStudentFromGivenMark(@Param("studentId") Integer studentId, @Param("givenMark") Float givenMark);

    //19. Berilgan Cursdan eng baland baxo.
    @Query("SELECT MAX (mark) FROM StudentCourseEntity WHERE course.id =:courseId ")
    Float getMaxMarkByCourseId(@Param("courseId") Integer courseId);

    //Page<StudentCourseEntity> findAllByName(String name, Pageable pageable);
    //20. Berilgan Cursdan o'lingan o'rtacha baxo.
    @Query("SELECT AVG (mark) FROM StudentCourseEntity WHERE course.id =:courseId ")
    Float getAvgMarkByCourseId(@Param("courseId") Integer courseId);

    //21. Berilgan Course dan olingna baxolar soni.
    @Query("SELECT COUNT (mark) FROM StudentCourseEntity WHERE course.id =:courseId ")
    Long getCountMarkByCourseId(@Param("courseId") Integer courseId);

    //23. StudentCourseMark pagination by given studentId. List should be sorted by createdDate.
    Page<StudentCourseEntity> findAllByStudentId(Integer id, Pageable pageable);

    //24. StudentCourseMark pagination by given courseId.  List should be sorted by createdDate.
    Page<StudentCourseEntity> findAllByCourseId(Integer id, Pageable pageable);
}
