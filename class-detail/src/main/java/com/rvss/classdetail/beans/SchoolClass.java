package com.rvss.classdetail.beans;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SchoolClass {
    @Id
    private String classId;
    private String classStd;
    private String classSection;
    private String classLocation;
    private String classTeacherName;
    private String classTeacherId;
    private String classTeacherEmail;
    private String coClassTeacherName;
    private String coClassTeacherId;
    private String coClassTeacherEmail;
    private String subjects;
    private String schoolId;

}
