package com.rvss.subjectregister.controller;


import com.rvss.subjectregister.beans.Subject;
import com.rvss.subjectregister.beans.SubjectTeacher;
import com.rvss.subjectregister.beans.MultipleSubjectTeacher;
import com.rvss.subjectregister.dataservices.IOperationSubjects;
import com.rvss.subjectregister.dataservices.SubjectJPARepository;
import com.rvss.subjectregister.dataservices.SubjectTeacherJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
import java.util.List;


@RequestMapping(path = "info/subjects/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableJpaRepositories
public class DataInfoController {

    @Autowired
    SubjectJPARepository subjectJPARepository;
    @Autowired
    SubjectTeacherJPARepository subjectTeacherJPARepository;
    @Autowired
    IOperationSubjects iOperationSubjects;

    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Subject Service is Up");
    }
    @GetMapping("/getAllSubjects") public ResponseEntity getAllSubjects(){
        ResponseEntity re = iOperationSubjects.getAllSubjects(subjectJPARepository);
        return re;
    }
    @GetMapping("/getAllSubjects12") public ResponseEntity getAllSubjects12(@RequestParam("subjectXII") boolean subjectXII){
        ResponseEntity re = iOperationSubjects.getAllSubjectClassXII(subjectXII, subjectJPARepository);
        return re;
    }
    @GetMapping("/getAllSubjects10") public ResponseEntity getAllSubjects10(@RequestParam("subjectX") boolean subjectX){
        ResponseEntity re = iOperationSubjects.getAllSubjectClassX(subjectX, subjectJPARepository);
        return re;
    }@GetMapping("/getAllSubjectsIB") public ResponseEntity getAllSubjectsIB(@RequestParam("subjectIB") boolean subjectIB){
        ResponseEntity re = iOperationSubjects.getAllSubjectIB(subjectIB, subjectJPARepository);
        return re;
    }
    @GetMapping("/getAllSubjectsGC") public ResponseEntity getAllSubjectsGC(@RequestParam("subjectGeneralClass") boolean subjectGeneralClass){
        ResponseEntity re = iOperationSubjects.getAllSubjectGeneral(subjectGeneralClass, subjectJPARepository);
        return re;
    }
    @PostMapping("/insertNewSubject")
    public ResponseEntity insertNewSubject(@Valid @RequestBody Subject subject) {
        ResponseEntity re = iOperationSubjects.insertNewSubject(subject,subjectJPARepository);
        return re;
    }
    @PostMapping("/insertNewSubjectTeacher")
    public ResponseEntity insertNewSubjectTeacher(@Valid @RequestBody SubjectTeacher subjectTeacher) {
        ResponseEntity re = iOperationSubjects.insertNewSubjectTeacher(subjectTeacher,subjectTeacherJPARepository);
        return re;
    }

    /*@PostMapping("/insertMultipleSubjectTeacher")
    public List<ResponseEntity> insertMultipleSubjectTeacher(@Valid @RequestBody MultipleSubjectTeacher multipleSubjectTeacher) {
        List<ResponseEntity> responses = new ArrayList<ResponseEntity>();
        for (int i = 0; i<= multipleSubjectTeacher.getSubjectTeachers().size(); i++)
        {  ResponseEntity re =
            iOperationSubjects.insertNewSubjectTeacher(
                    multipleSubjectTeacher.getSubjectTeachers().get(i),
                    subjectTeacherJPARepository);
            responses.set(i,re);
        }
          return responses;
    }*/

    @PostMapping("/insertMultipleSubjectTeacher")
    public CompletableFuture<List<ResponseEntity>> insertMultipleSubjectTeacher(@Valid @RequestBody List<SubjectTeacher> subjectTeachers) {
        List<CompletableFuture<ResponseEntity>> futureResponses = subjectTeachers.stream()
                .map(subjectTeacher -> CompletableFuture.supplyAsync(() ->iOperationSubjects.insertNewSubjectTeacher(subjectTeacher, subjectTeacherJPARepository)))
                .toList();

        return CompletableFuture.allOf(futureResponses.toArray(new CompletableFuture[0]))
                .thenApply(voidResult ->
                        futureResponses.stream()
                                .map(CompletableFuture::join)
                                .toList());
    }
}
