package com.rvss.studentdetail.controller;


import com.rvss.studentdetail.beans.Student;
import com.rvss.studentdetail.dataservices.IOperationStudents;
import com.rvss.studentdetail.dataservices.StudentJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/students/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableJpaRepositories
public class DataInfoController {

    @Autowired

    StudentJPARepository studentJPARepository;

    @Autowired
    IOperationStudents iOperationStudents;

    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Student Service is Up");
    }

    @PostMapping("/insertNewStudent")
    public ResponseEntity insertNewStudent(@Valid @RequestBody Student student) {
        ResponseEntity re = iOperationStudents.insertNewStudents(student,studentJPARepository);
        return re;
    }
    @GetMapping("/getStudentsOfSchool")
    public ResponseEntity getStudentsOfSchool(@RequestParam("schoolId") String schoolId){
        ResponseEntity re = iOperationStudents.getAllStudentsOfSchool(schoolId,studentJPARepository);
        if (re.hasBody())
         return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned School");
        }
    }

    @GetMapping("/getStudent")
    public ResponseEntity getStudent(@RequestParam("studentId") String studentId){
        ResponseEntity re = iOperationStudents.getStudentById(studentId,studentJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned Id");
        }
    }

    @GetMapping("/getStudentClassStd")
    public ResponseEntity getStudentClassStd(@RequestParam("classStd") String classStd){
        ResponseEntity re = iOperationStudents.getAllStudentsOfClassStd(classStd,studentJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned Class");
        }
    }

    @GetMapping("/getStudentClassId")
    public ResponseEntity getStudentClassId(@RequestParam("classId") String classId){
        ResponseEntity re = iOperationStudents.getAllStudentsOfClass(classId,studentJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned Class");
        }
    }

    @GetMapping("/getStudentOfTeacher")
    public ResponseEntity getStudentOfTeacher(@RequestParam("teacherEmail") String teacherEmail){
        ResponseEntity re = iOperationStudents.getAllStudentsOfteacher_email(teacherEmail,studentJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned Teacher");
        }
    }

    @GetMapping("/getStudentOfParent")
    public ResponseEntity getStudentOfParent(@RequestParam("guardianEmail") String guardianEmail){
        ResponseEntity re = iOperationStudents.getAllStudentsOfParents(guardianEmail,studentJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Student Registered for the mentioned Parent");
        }
    }

}
