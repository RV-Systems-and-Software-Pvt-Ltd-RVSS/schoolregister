package com.rvss.teacherdetail.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Teacher {

    private int slNo;
    private String teacherName;
    private String teacherAddress;
    @Id
    private String teacherEmpId;
    private String teacherDesignation;
    private String teacherQualification;
    private String teacherType;
    private String teacherSpecialization;
    private String teacherPhoneNo;
    private String teacherEmailAddress;
    private boolean teacherIsInService;
    private boolean classTeacher;
    private String  teacherCoreSubject;
    private boolean asstClassTeacher;
    private String schoolId;
}

