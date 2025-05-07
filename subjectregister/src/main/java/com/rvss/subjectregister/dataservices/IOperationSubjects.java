package com.rvss.subjectregister.dataservices;

import com.rvss.subjectregister.beans.Subject;
import com.rvss.subjectregister.beans.SubjectTeacher;
import com.rvss.subjectregister.dataservices.SubjectJPARepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public interface IOperationSubjects {
    public ResponseEntity insertNewSubject(Subject subject, SubjectJPARepository subjectJPARepository);
    public ResponseEntity getAllSubjects(SubjectJPARepository subjectJPARepository);
    public ResponseEntity getAllSubjectClassXII(boolean subjectXII , SubjectJPARepository subjectJPARepository);
    public ResponseEntity  getAllSubjectClassX(boolean subjectX , SubjectJPARepository subjectJPARepository);
    public ResponseEntity getAllSubjectIB(boolean subjectIB , SubjectJPARepository subjectJPARepository);
    public ResponseEntity  getAllSubjectGeneral(boolean subjectGeneralClass , SubjectJPARepository subjectJPARepository);

    public ResponseEntity insertNewSubjectTeacher(SubjectTeacher subjectTeacher, SubjectTeacherJPARepository subjectTeacherJPARepository);

}
