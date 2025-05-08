package com.rvss.studentdetail.dataservices;

import com.rvss.studentdetail.beans.Student;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IOperationStudents {
    public ResponseEntity insertNewStudents(Student student,
                                            StudentJPARepository studentJPARepository);
    public ResponseEntity getStudentById(String  student_id,
                                         StudentJPARepository studentJPARepository);
    public ResponseEntity getAllStudentsOfClass(String class_id,
                                                                StudentJPARepository studentJPARepository);

    public ResponseEntity getAllStudentsOfClassStd(String class_std,
                                                StudentJPARepository studentJPARepository);
    public ResponseEntity getAllStudentsOfSchool(String school_id,
                                                          StudentJPARepository studentJPARepository);

    public ResponseEntity getAllStudentsOfteacher_email(String teacher_email,
                                                           StudentJPARepository studentJPARepository);

    public ResponseEntity getAllStudentsOfParents(String guardian_email,
                                                            StudentJPARepository studentJPARepository);



    /*public ResponseEntity findByStudentRollNumber(String  rollNumber,
                                     StudentJPARepository studentJPARepository);*/
}
