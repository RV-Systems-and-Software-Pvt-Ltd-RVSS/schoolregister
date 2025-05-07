package com.rvss.subjectregister.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.rvss.subjectregister.beans.SubjectTeacher;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MultipleSubjectTeacher {
    public List<SubjectTeacher> subjectTeachers;
}
