package com.rvss.classdetail.beans;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class SubjectTeacher {
    @Id
    private String assignmentId;
    private boolean assignmentStatus;
    private String subjectCode;
    private String subjectName;
    private String teacherEmpId;
    private String teacherName;
    private String schoolId;
    private String classId;
}
