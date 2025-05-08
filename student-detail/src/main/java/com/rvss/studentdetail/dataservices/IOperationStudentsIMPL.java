package com.rvss.studentdetail.dataservices;

import com.rvss.studentdetail.beans.Student;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOperationStudentsIMPL implements IOperationStudents{

    @Override
    public ResponseEntity insertNewStudents(Student student, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(studentJPARepository.save(student));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

   @Override
    public ResponseEntity  getAllStudentsOfClass(String class_id, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findByclass_id(class_id));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity  getAllStudentsOfClassStd(String class_std, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findByclass_std(class_std));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }


    @Override
    public ResponseEntity getAllStudentsOfSchool(String schoolId, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findBySchoolId(schoolId));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity getStudentById(String student_id, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findByStudentId(student_id));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllStudentsOfteacher_email(String teacherEmail, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findBy_teacher_email(teacherEmail));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllStudentsOfParents(String guardianEmail,
                                                            StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findByguardian_email(guardianEmail));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }



    /*public ResponseEntity findByStudentRollNumber(String rollNumber, StudentJPARepository studentJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(studentJPARepository.findByrollNumber(rollNumber));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }*/
}
