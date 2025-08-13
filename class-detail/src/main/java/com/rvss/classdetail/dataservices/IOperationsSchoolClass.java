package com.rvss.classdetail.dataservices;
import com.rvss.classdetail.beans.SchoolClass;
import com.rvss.classdetail.beans.SchoolSubjectClass;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.util.Optional;

@Service
public interface IOperationsSchoolClass {
    public ResponseEntity insertNewSchoolClass(SchoolClass schoolClass,
                                            SchoolClassJPARepository schoolClassJPARepository) ;
    public ResponseEntity insertNewClassWithSubject(SchoolSubjectClass schoolSubjectClass , SchoolClassJPARepository schoolClassJPARepository);
    public Optional<ResponseEntity> getClassWithSubjectTeacher(String schoolId, String classId);
    public ResponseEntity updateNewSchoolClass(SchoolClass schoolClass,
                                               SchoolClassJPARepository schoolClassJPARepository) ;

    public ResponseEntity updateSchoolClassTeacherEmail(String classTeacherEmail,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity updateSchoolClassTeacherId(String classTeacherId,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity updateSchoolClassCoTeacherEmail(String coClassTeacherEmail,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity updateSchoolClassCoTeacherId(String coClassTeacherId,SchoolClassJPARepository schoolClassJPARepository);

    public ResponseEntity getSchoolClassClassStd(String classStd,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity getSchoolClassClassId(String classId,SchoolClassJPARepository schoolClassJPARepository);

    public ResponseEntity getAllSchoolClassSchoolId(String schoolId,SchoolClassJPARepository schoolClassJPARepository);

    public ResponseEntity getAllSchoolClassByClassTeacherId(String classTeacherId,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity getAllSchoolClassByClassTeacherEmail(String classTeacherEmail,SchoolClassJPARepository schoolClassJPARepository);
    public ResponseEntity getAllSchoolClassByCOClassTeacherEmail(String coClassTeacherEmail,SchoolClassJPARepository schoolClassJPARepository);

    public ResponseEntity getAllSchoolClassByClassLocation(String classLocation,SchoolClassJPARepository schoolClassJPARepository);
}
