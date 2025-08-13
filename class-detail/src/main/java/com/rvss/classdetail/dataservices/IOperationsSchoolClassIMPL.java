package com.rvss.classdetail.dataservices;

import com.rvss.classdetail.beans.SchoolClass;
import com.rvss.classdetail.beans.SchoolSubjectClass;
import com.rvss.classdetail.beans.SubjectTeacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.util.stream.Collectors.toList;

@Service
public class IOperationsSchoolClassIMPL implements IOperationsSchoolClass{
    @Value("${external.api.base-url}")
    private String baseUrl;
    @Autowired
    public RestTemplate restTemplate;
    @Override
    public ResponseEntity insertNewSchoolClass(SchoolClass schoolClass, SchoolClassJPARepository schoolClassJPARepository) {
        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(schoolClassJPARepository.save(schoolClass));
        }catch(Exception exp){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exp.getCause());
        }
    }

    @Override
    public ResponseEntity insertNewClassWithSubject(SchoolSubjectClass schoolSubjectClass, SchoolClassJPARepository schoolClassJPARepository) {
        SchoolClass schoolClass = schoolSubjectClass.getSchoolClass();

        List<SubjectTeacher> subjectTeacherList = schoolSubjectClass.getSubjectTeachers().stream().toList();
        ExecutorService executorService = Executors.newFixedThreadPool(subjectTeacherList.size());
        List<CompletableFuture<Void>> futures = schoolSubjectClass.getSubjectTeachers().stream()
                .map(record -> CompletableFuture.runAsync(() ->{processSubjectRegistration(record);},executorService)
                        .exceptionally(ex -> { String error = Optional.ofNullable(ex.getCause())
                                .map(Throwable::getMessage).orElse("Unexpected Error Occurred");
                            return null;
                        }
                )).toList();
        /*Use of threads to call the subject rejister API
        * Number of threads to be created in accordance with the Number of element present in the list */

        try{
            return ResponseEntity.status(HttpStatus.CREATED).body(schoolClassJPARepository.save(schoolClass));
        }catch(Exception exp) {String error = Optional.ofNullable(exp.getCause())
                .map(Throwable::getMessage)
                .orElse("Unexpected error occurred");
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(Map.of("error", error));
        }

    }

    private ResponseEntity processSubjectRegistration(SubjectTeacher record) {
        String url = baseUrl + "info/subjects/api/v1/insertMultipleSubjectTeacher";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity request = new HttpEntity(record, headers);
        return restTemplate.postForEntity(url, request, String.class);
    }

    @Override
    public Optional<ResponseEntity> getClassWithSubjectTeacher(String schoolId, String classId){
        return null;
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
