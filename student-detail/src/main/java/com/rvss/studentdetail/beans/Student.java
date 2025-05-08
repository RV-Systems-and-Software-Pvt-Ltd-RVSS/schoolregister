package com.rvss.studentdetail.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student {
    private int slNo;
    @Id
    private  String studentId;
    private  String rollNumber;
    private  String firstName;
    private  String middleName;
    private  String lastName;
    private  String schoolName;
    private  String teacherEmail;
    private  String schoolId;
    private  String classStd;
    private  String classSection;
    private  String classId;
    private  String studentICardStatus;
    private  String studentICardFileName;
    private  String studentBloodGroup;
    private  String guardianFirstName;
    private  String guardianLastName;
    private  String guardianContactNumber;
    private  String guardianEmail;
    private  String addressLine1;
    private  String addressLine2;
    private  String state;
    private  String pin;
}
