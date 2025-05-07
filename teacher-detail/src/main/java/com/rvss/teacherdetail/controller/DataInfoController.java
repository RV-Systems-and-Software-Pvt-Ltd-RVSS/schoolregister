package com.rvss.teacherdetail.controller;

import com.rvss.teacherdetail.beans.Teacher;
import com.rvss.teacherdetail.dataservices.IOperationTeachers;
import com.rvss.teacherdetail.dataservices.TeacherJPARepository;
import com.rvss.teacherdetail.sftp.JavaSSHService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/teachers/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableJpaRepositories
public class DataInfoController {

    @Autowired
    TeacherJPARepository teacherJPARepository;

    @Autowired
    IOperationTeachers iOperationTeachers;
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importTeacherJob;



    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Teacher Service is Up");
    }
    @GetMapping("/getAllClassTeachers") public ResponseEntity getAllTeachers(){
        ResponseEntity re = iOperationTeachers.getAllClassTeachers(teacherJPARepository);
        return re;
    }

    @GetMapping("/getAllTeachers") public ResponseEntity getAllClassTeachers(){
        ResponseEntity re = iOperationTeachers.getAllTeachers(teacherJPARepository);
        return re;
    }


    @PostMapping("/insertNewTeacher")
    public ResponseEntity insertNewTeacher(@Valid @RequestBody Teacher teacher) {
        ResponseEntity re = iOperationTeachers.insertNewTeacher(teacher,teacherJPARepository);
        return re;
    }

    @GetMapping("/getTeachersOfSchool")
    public ResponseEntity getTeachersOfSchool(@RequestParam("schoolId") String schoolId){
        ResponseEntity re = iOperationTeachers.getAllTeachersOfSchool(schoolId,teacherJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Teachers Registered for the mentioned School");
        }
    }
    @GetMapping("/getClassTeachersOfSchool")
    public ResponseEntity getClassTeachersOfSchool(@RequestParam("schoolId") String schoolId){
        ResponseEntity re = iOperationTeachers.getAllClassTeachersBySchool(schoolId,teacherJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Teachers Registered for the mentioned School");
        }
    }
    @GetMapping("/getAsstClassTeachersOfSchool")
    public ResponseEntity getAsstClassTeachersOfSchool(@RequestParam("schoolId") String schoolId){
        ResponseEntity re = iOperationTeachers.getAllAsstClassTeachersBySchool(schoolId,teacherJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Teachers Registered for the mentioned School");
        }
    }
    @GetMapping("/getTeachersOfSchoolSubject")
    public ResponseEntity getTeachersOfSchoolSubject(
            @RequestParam String schoolId,
            @RequestParam(required = false) String subject
    ){
        ResponseEntity re = iOperationTeachers.getTeacherBySchoolSubject(schoolId,subject,teacherJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Teachers Registered for the mentioned School");
        }
    }
    @PostMapping("/import")
    public ResponseEntity importTeachers(@RequestParam("filename") String filename) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addString("filename", filename)
                    .addLong("time", System.currentTimeMillis()) // Ensures a unique job instance
                    .toJobParameters();
           jobLauncher.run(importTeacherJob, jobParameters);
            return ResponseEntity.ok("Job started successfully");
            /*return ResponseEntity.status(HttpStatus.OK).body(
                    new JavaSSHService().fileUploadService(filename)
            );*/
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Job failed to start");
        }
    }
}

