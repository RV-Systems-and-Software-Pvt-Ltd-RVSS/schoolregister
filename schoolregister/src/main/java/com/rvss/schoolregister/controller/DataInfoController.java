package com.rvss.schoolregister.controller;


import com.rvss.schoolregister.beans.SchoolJPABean;
import com.rvss.schoolregister.beans.Schools;
import com.rvss.schoolregister.dataservices.*;
import com.rvss.schoolregister.mailservices.EmailService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import javax.validation.Valid;
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "info/schools/api/v1",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST})
@RestController
@EnableMongoRepositories

public class DataInfoController {

    @Autowired
    public SchoolRepository schoolRepository;
    @Autowired
    public SchoolJPABean schoolJPABean;

    @Autowired
    private EmailService emailService;
    @Autowired
    public DatastageServices datastageServices;
    @Autowired
    public IOperationSchools iOperationSchools;

    @GetMapping("/getSchools")
    public ResponseEntity<List> getSchools(@RequestParam("school_name") String school_name){

        List schoolList = DatastageServices.getSchoolDetailsOnName(school_name,schoolRepository,iOperationSchools);
        return ResponseEntity.status(HttpStatus.OK).body(schoolList);
    }
    @GetMapping("/getAllSchools")
        public ResponseEntity<List> getAllSchools(){
            List schoolList = iOperationSchools.getAllSchool(schoolRepository);
            return ResponseEntity.status(HttpStatus.OK).body(schoolList);
        }
        @Async
    @PostMapping("/insertNewSchool")
        public CompletableFuture<ResponseEntity> insertNewSchool(@Valid @RequestBody Schools schools) throws SQLException {
        //Schools schools1 = new Schools();
       Schools schools2 =iOperationSchools.insertNewSchool(schools,schoolRepository);
       schoolJPABean.setSchool_id(schools.getSchool_id());
       schoolJPABean.setSchool_name(schools.getSchool_name());
       schoolJPABean.setSchool_email(schools.getSchool_email());
       schoolJPABean.setSchool_address(schools.getSchool_address());
       schoolJPABean.setSchool_city(schools.getSchool_city());
       schoolJPABean.setSchool_state(schools.getSchool_state());
       schoolJPABean.setSchool_pincode(schools.getSchool_pincode());
       schoolJPABean.setSchool_admin_email(schools.getSchool_adminEmail());
       schoolJPABean.setSchool_registration_date(schools.getSchool_registration_date());
       schoolJPABean.setIcard_template(schools.getIcard_template());
       schoolJPABean.setSchool_status(schools.getSchool_status());
       schoolJPABean.setSchool_contact_number(schools.getPhones().getMobile_number());
       schoolJPABean.setFax(schools.getPhones().getFax());

            iOperationSchools.insertSchoolRegistration(schoolJPABean);
        return CompletableFuture.completedFuture(ResponseEntity.status(HttpStatus.CREATED).body(schools2));
    }


    @GetMapping("/healthCheck") public ResponseEntity healthCheck(){
        return ResponseEntity.ok("School Service is Up");
    }
}
