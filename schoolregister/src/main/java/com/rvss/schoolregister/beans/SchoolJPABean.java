package com.rvss.schoolregister.beans;



import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.stereotype.Component;


@Component

@ToString
@Setter
@Getter
public class SchoolJPABean {
    @Id
    private String school_id;
    private String school_name;
    private String school_email;
    private String school_address;
    private String school_city;
    private String school_state;
    private String school_pincode;
    private String school_admin_email;
    private String school_registration_date;
    private String icard_template;
    private String school_status;
    private String school_contact_number;
    private String fax;


}
