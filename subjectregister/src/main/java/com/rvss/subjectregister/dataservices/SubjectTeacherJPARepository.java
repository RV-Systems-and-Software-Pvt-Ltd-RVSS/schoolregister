package com.rvss.subjectregister.dataservices;

import com.rvss.subjectregister.beans.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SubjectTeacherJPARepository extends JpaRepository<SubjectTeacher, Long> {
    @Query("SELECT e FROM SubjectTeacher e WHERE e.subjectCode = :subjectCode")
    List<SubjectTeacher> findBySubjectCode(@Param("subjectCode") String subjectCode);

    @Query("SELECT s FROM SubjectTeacher s WHERE s.schoolId = ?1 and s.subjectName =?2")
    List<SubjectTeacher> findAllSubjectTeachersBySchool(String  schoolId,String subjectName);

    @Query("SELECT s FROM SubjectTeacher s WHERE s.schoolId = ?1 and s.subjectCode =?2 and s.classId =?3 ")
    List<SubjectTeacher> findAllSubjectTeachersBySchool(String  schoolId,String subjectCode,String classId);
}
