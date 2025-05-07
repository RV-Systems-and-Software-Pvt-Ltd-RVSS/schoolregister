package com.rvss.teacherdetail.dataservices;

import com.rvss.teacherdetail.beans.Teacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class IOperationTeachersIMPL implements IOperationTeachers{

    @Override
    public ResponseEntity insertNewTeacher(Teacher teacher, TeacherJPARepository teacherJPARepository){
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(teacherJPARepository.save(teacher));
        }catch(Exception exp){
             return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }

    }

    @Override
    public ResponseEntity getAllTeachers( TeacherJPARepository teacherJPARepository){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findAll());
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }

    }

    @Override
    public ResponseEntity getAllTeachersOfSchool(String schoolId, TeacherJPARepository teacherJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findByschoolId(schoolId));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllClassTeachers( TeacherJPARepository teacherJPARepository){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findByClassTeacher(true));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllClassTeachersBySchool(String school_id, TeacherJPARepository teacherJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findAllClassTeachersBySchool(school_id,true));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }



    @Override
    public ResponseEntity getAllAsstClassTeachersBySchool(String school_id, TeacherJPARepository teacherJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findAllAsstClassTeachersBySchool(school_id,true));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity getTeacherBySchoolSubject(String school_id, String subject,TeacherJPARepository teacherJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(teacherJPARepository.findAllTeachersBySchoolSubject(school_id,subject));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }


    @Override
    public ArrayList<Teacher> insertBulkTeacher(ArrayList<Teacher> teacherList) {
        return null;
    }
}
