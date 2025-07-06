package com.rvss.teacherdetail.dataservices;

import com.rvss.teacherdetail.beans.Teacher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public interface IOperationTeachers {
 public ResponseEntity insertNewTeacher(Teacher teacher, TeacherJPARepository teacherJPARepository);
 public ResponseEntity getTeacherById( String teacherId,TeacherJPARepository teacherJPARepository);
 public ResponseEntity<Teacher> getAllTeachers(TeacherJPARepository teacherJPARepository);
 public ResponseEntity<Teacher> getAllTeachersOfSchool(String school_id,
                                                       TeacherJPARepository teacherJPARepository);
 public ResponseEntity<Teacher> getAllClassTeachers(TeacherJPARepository teacherJPARepository);

 public ResponseEntity<Teacher> getAllClassTeachersBySchool(String school_id,TeacherJPARepository teacherJPARepository);

 public ResponseEntity<Teacher> getAllAsstClassTeachersBySchool(String school_id,TeacherJPARepository teacherJPARepository);
 public ResponseEntity<Teacher> getTeacherBySchoolSubject(String school_id,String subject,TeacherJPARepository teacherJPARepository);
 public ArrayList<Teacher> insertBulkTeacher (ArrayList<Teacher> teacherList);

}
