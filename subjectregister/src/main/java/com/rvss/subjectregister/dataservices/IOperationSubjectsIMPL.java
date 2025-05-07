package com.rvss.subjectregister.dataservices;

import com.rvss.subjectregister.beans.Subject;
import com.rvss.subjectregister.beans.SubjectTeacher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOperationSubjectsIMPL implements IOperationSubjects{
    @Override
    public ResponseEntity insertNewSubject(Subject subject, SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectJPARepository.save(subject));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }



    @Override
    public ResponseEntity insertNewSubjectTeacher(SubjectTeacher subjectTeacher, SubjectTeacherJPARepository subjectTeacherJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(subjectTeacherJPARepository.save(subjectTeacher));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity getAllSubjects(SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(subjectJPARepository.findAll());
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity getAllSubjectClassXII(boolean subjectXII , SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(subjectJPARepository.findSubject_byClass12(subjectXII));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSubjectClassX(boolean subjectX, SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(subjectJPARepository.findSubject_byClass10(subjectX));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSubjectIB(boolean subjectIB , SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(subjectJPARepository.findSubject_byClassIB(subjectIB));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSubjectGeneral(boolean subjectGeneralClass , SubjectJPARepository subjectJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(subjectJPARepository.findSubject_byClassGC(subjectGeneralClass));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }


}
