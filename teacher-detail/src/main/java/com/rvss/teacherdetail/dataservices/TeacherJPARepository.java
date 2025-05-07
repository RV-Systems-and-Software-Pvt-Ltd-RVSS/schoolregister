package com.rvss.teacherdetail.dataservices;

import com.rvss.teacherdetail.beans.Teacher;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TeacherJPARepository extends JpaRepository<Teacher, Long> {
    @Query("SELECT e FROM Teacher e WHERE e.slNo = :slNo")
    Optional<Teacher> findBySlNo(@Param("slNo") int slNo);
    List<Teacher> findByClassTeacher(boolean classTeacher);
    @Query("SELECT s FROM Teacher s WHERE s.schoolId = ?1")
    List<Teacher> findByschoolId(String  schoolId);

    @Query("SELECT s FROM Teacher s WHERE s.schoolId = ?1 and s.classTeacher =?2")
    List<Teacher> findAllClassTeachersBySchool(String  schoolId,boolean classTeacher);

    @Query("SELECT s FROM Teacher s WHERE s.schoolId = ?1 and s.asstClassTeacher =?2")
    List<Teacher> findAllAsstClassTeachersBySchool(String  schoolId,boolean asstClassTeacher);

    @Query("SELECT s FROM Teacher s WHERE s.schoolId = ?1 and s.teacherSpecialization =?2")
    List<Teacher> findAllTeachersBySchoolSubject(String  schoolId,String teacherSpecialization);
}
