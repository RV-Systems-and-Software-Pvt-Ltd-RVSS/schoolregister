package com.rvss.studentdetail.dataservices;

import com.rvss.studentdetail.beans.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StudentJPARepository extends JpaRepository<Student, Long>{
    /*Optional<Students> findBy_student_id(@Param("student_id") String student_id);
    Optional<Students> findByrollNumber(@Param("rollNumber") String rollNumber);
    List<Students> findByschool_Id(@Param("school_id")String school_id);
    */

    @Query("SELECT s FROM Student s WHERE s.schoolId = ?1")
    List<Student> findBySchoolId(String schoolId);

    @Query("SELECT s FROM Student s WHERE s.studentId = ?1")
    List<Student> findByStudentId(String studentId);
    @Query("SELECT s FROM Student s WHERE s.classId = ?1")
    List<Student> findByclass_id(String classId);
    @Query("SELECT s FROM Student s WHERE s.classStd = ?1")
    List<Student> findByclass_std(String classStd);
    @Query("SELECT s FROM Student s WHERE s.teacherEmail = ?1")
    List<Student> findBy_teacher_email(String teacherEmail);

    @Query("SELECT s FROM Student s WHERE s.guardianEmail = ?1")
    List<Student> findByguardian_email(String guardianEmail);


}
