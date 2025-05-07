package com.rvss.classdetail.dataservices;

import com.rvss.classdetail.beans.SchoolClass;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IOperationsSchoolClassIMPL implements IOperationsSchoolClass{

    @Override
    public ResponseEntity insertNewSchoolClass(SchoolClass schoolClass, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(schoolClassJPARepository.save(schoolClass));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity updateNewSchoolClass(SchoolClass schoolClass, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity updateSchoolClassTeacherEmail(String classTeacherEmail, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity updateSchoolClassTeacherId(String classTeacherId, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity updateSchoolClassCoTeacherEmail(String coClassTeacherEmail, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity updateSchoolClassCoTeacherId(String coClassTeacherId, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity getSchoolClassClassStd(String classStd, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity getSchoolClassClassId(String classId, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(schoolClassJPARepository.findByclassId(classId));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSchoolClassSchoolId(String schoolId, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(schoolClassJPARepository.findByschoolId(schoolId));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSchoolClassByClassTeacherId(String classTeacherId, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }

    @Override
    public ResponseEntity getAllSchoolClassByClassTeacherEmail(String classTeacherEmail, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(schoolClassJPARepository.findBy_teacher_email(classTeacherEmail));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }
    @Override
    public ResponseEntity getAllSchoolClassByCOClassTeacherEmail(String coClassTeacherEmail, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.OK).body(schoolClassJPARepository.findBy_co_teacher_email(coClassTeacherEmail));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity getAllSchoolClassByClassLocation(String classLocation, SchoolClassJPARepository schoolClassJPARepository) {
        return null;
    }
}
