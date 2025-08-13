package com.rvss.classdetail.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SchoolSubjectClass {
    private SchoolClass schoolClass;
    private List<SubjectTeacher> subjectTeachers;
}
