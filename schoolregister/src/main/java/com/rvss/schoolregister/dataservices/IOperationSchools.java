package com.rvss.schoolregister.dataservices;

import com.rvss.schoolregister.beans.SchoolJPABean;
import com.rvss.schoolregister.beans.Schools;

import java.sql.SQLException;
import java.util.List;

public interface IOperationSchools {
    Schools insertNewSchool(Schools school,SchoolRepository schoolRepository);
    void insertSchoolRegistration(SchoolJPABean schoolJPABean) throws SQLException;

    Boolean updateSchoolInformation(Schools school, SchoolRepository schoolRepository);
    List getAllSchool(SchoolRepository schoolRepository);
    Schools getAllSchoolInformation(String schoolName,SchoolRepository schoolRepository);
    Schools getAllSchoolInformationById(String schoolId,SchoolRepository schoolRepository);
    String getSchoolICardTemplate (Schools school);


}
