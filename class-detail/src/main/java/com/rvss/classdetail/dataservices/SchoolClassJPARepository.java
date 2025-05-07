package com.rvss.classdetail.dataservices;

import com.rvss.classdetail.beans.SchoolClass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface SchoolClassJPARepository extends JpaRepository<SchoolClass, Long> {

    @Query("SELECT s FROM SchoolClass s WHERE s.schoolId = ?1")
    List<SchoolClass> findByschoolId(String  schoolId);

    @Query("SELECT s FROM SchoolClass s WHERE s.classId = ?1")
    List<SchoolClass> findByclassId(String classId);

    @Query("SELECT s FROM SchoolClass s WHERE s.classTeacherEmail = ?1")
    List<SchoolClass> findBy_teacher_email(String classTeacherEmail);
    @Query("SELECT s FROM SchoolClass s WHERE s.coClassTeacherEmail = ?1")
    List<SchoolClass> findBy_co_teacher_email(String coClassTeacherEmail);

    /*@Query("SELECT s FROM Class s WHERE s.classStd = ?1")
    List<SchoolClass> findByclass_std(String classStd);



    @Query("SELECT s FROM Class s WHERE s.classTeacherId = ?1")
    List<SchoolClass> findBy_teacher_Id(String classTeacherId);

    @Query("SELECT s FROM Class s WHERE s.coClassTeacherId = ?1")
    List<SchoolClass> findBy_co_teacher_Id(String coClassTeacherId);

    @Query("SELECT s FROM Class s WHERE s.coClassTeacherEmail = ?1")
    List<SchoolClass> findBy_co_teacher_email(String coClassTeacherEmail);*/
}
