package com.rvss.subjectregister.dataservices;

import com.rvss.subjectregister.beans.Subject;
import com.rvss.subjectregister.beans.SubjectTeacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectJPARepository extends JpaRepository<Subject , Long> {
    @Query("SELECT e FROM Subject e WHERE e.subjectCode = :subjectCode")
    Optional<Subject> findBySubjectCode(@Param("subjectCode") String subjectCode);

    @Query("SELECT s FROM Subject s WHERE s.subjectXII = ?1")
    List<Subject> findSubject_byClass12(boolean subjectXII);

    @Query("SELECT s FROM Subject s WHERE s.subjectIB = ?1")
    List<Subject> findSubject_byClassIB(boolean subjectIB);

    @Query("SELECT s FROM Subject s WHERE s.subjectX = ?1")
    List<Subject> findSubject_byClass10(boolean subjectX);

    @Query("SELECT s FROM Subject s WHERE s.subjectGeneralClass = ?1")
    List<Subject> findSubject_byClassGC(boolean subjectGeneralClass);
}
