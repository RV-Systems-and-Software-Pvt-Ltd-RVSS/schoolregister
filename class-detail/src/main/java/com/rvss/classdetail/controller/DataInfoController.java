package com.rvss.classdetail.controller;

import com.rvss.classdetail.beans.SchoolClass;
import com.rvss.classdetail.dataservices.SchoolClassJPARepository;
import com.rvss.classdetail.dataservices.IOperationsSchoolClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/schoolclass/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableJpaRepositories
public class DataInfoController {
    @Autowired
    IOperationsSchoolClass iOperationsSchoolClass;

    @Autowired
    SchoolClassJPARepository schoolClassJPARepository;

    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Class Service is Up");
    }
    @PostMapping("/insertNewClass")
    public ResponseEntity insertNewClass(@Valid @RequestBody SchoolClass schoolClass) {
        ResponseEntity re = iOperationsSchoolClass.insertNewSchoolClass(schoolClass,schoolClassJPARepository);
        return re;
    }
    @GetMapping("/getAllClassBySchool")
    public ResponseEntity getAllClassBySchool(@RequestParam("schoolId") String schoolId){
        ResponseEntity re = iOperationsSchoolClass.getAllSchoolClassSchoolId(schoolId,schoolClassJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Designated class created for the mentioned School");
        }
    }
    @GetMapping("/getClass")
    public ResponseEntity getClass(@RequestParam("classId") String classId){
        ResponseEntity re = iOperationsSchoolClass.getSchoolClassClassId(classId,schoolClassJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Designated class created for the mentioned Class");
        }
    }
    @GetMapping("/getClassByClassTeacher")
    public ResponseEntity getClassByClassTeacher(@RequestParam("classTeacherEmail") String classTeacherEmail){
        ResponseEntity re = iOperationsSchoolClass.getAllSchoolClassByClassTeacherEmail(classTeacherEmail,schoolClassJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Designated class created for the mentioned Class");
        }
    }
    @GetMapping("/getClassByCOClassTeacher")
    public ResponseEntity getClassByCOClassTeacher(@RequestParam("coClassTeacherEmail") String coClassTeacherEmail){
        ResponseEntity re = iOperationsSchoolClass.getAllSchoolClassByCOClassTeacherEmail(coClassTeacherEmail,schoolClassJPARepository);
        if (re.hasBody())
            return re;
        else{
            return ResponseEntity.status(HttpStatus.OK).body("There is No Designated class created for the mentioned Class");
        }
    }
}
