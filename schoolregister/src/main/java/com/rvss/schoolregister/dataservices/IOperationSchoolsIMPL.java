package com.rvss.schoolregister.dataservices;

import com.rvss.schoolregister.beans.SchoolJPABean;
import com.rvss.schoolregister.beans.Schools;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;


@Service
public class IOperationSchoolsIMPL implements  IOperationSchools {
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public Schools insertNewSchool(Schools school, SchoolRepository schoolRepository) {
        schoolRepository.deleteAll();
        schoolRepository.insert(school);
        return school;
    }

    @Override
    public void insertSchoolRegistration(SchoolJPABean schoolJPABean) throws SQLException {
        Connection connection = null;
        MySQLConnection mySQLConnection = new MySQLConnection();
        String query = "INSERT INTO school (school_id, " +
                "school_name, school_email, school_address, school_city, " +
                "school_state, school_pincode, school_admin_email, " +
                "school_registration_date, icard_template, " +
                "school_status, school_contact_number, school_fax) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try {
            connection = MySQLConnection.getConnection();
            // Your existing code for saving the school object
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, schoolJPABean.getSchool_id());
            statement.setString(2, schoolJPABean.getSchool_name());
            statement.setString(3, schoolJPABean.getSchool_email());
            statement.setString(4, schoolJPABean.getSchool_address());
            statement.setString(5, schoolJPABean.getSchool_city());
            statement.setString(6, schoolJPABean.getSchool_state());
            statement.setString(7, schoolJPABean.getSchool_pincode());
            statement.setString(8, schoolJPABean.getSchool_admin_email());
            statement.setString(9, schoolJPABean.getSchool_registration_date());
            statement.setString(10,schoolJPABean.getIcard_template());
            statement.setString(11,schoolJPABean.getSchool_status());
            statement.setString(12,schoolJPABean.getSchool_contact_number());
            statement.setString(13, schoolJPABean.getFax());

            statement.executeUpdate();
            statement.close();
        } finally {
            MySQLConnection.closeConnection(connection);
        }
    }


    @Override
    public Boolean updateSchoolInformation(Schools school,SchoolRepository schoolRepository) {
        schoolRepository.getSchoolById(school.getSchool_id());
        return true;
    }

    @Override
    public List getAllSchool(SchoolRepository schoolRepository) {
         List lis = schoolRepository.findAll();
         return lis;
    }


    @Override
    public Schools getAllSchoolInformation(String schoolName,SchoolRepository schoolRepository) {
        return schoolRepository.findByName(schoolName);
    }

    @Override
    public Schools getAllSchoolInformationById(String schoolId, SchoolRepository schoolRepository) {
        Schools school = schoolRepository.getSchoolById(schoolId);
        return null;
    }

    @Override
    public String getSchoolICardTemplate(Schools school) {
        return null;
    }


}
