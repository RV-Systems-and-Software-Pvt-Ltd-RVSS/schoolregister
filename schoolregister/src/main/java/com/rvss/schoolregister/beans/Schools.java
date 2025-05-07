package com.rvss.schoolregister.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;



@Data
@Document("Schools")
@AllArgsConstructor

public class Schools {
    public Schools(String school_id,
                   String school_name,
                   String school_email,
                   String school_address,
                   String school_city,
                   String school_state,
                   String school_pincode,
                   String school_status,
                   Phones phones,
                   String school_adminEmail,
                   String school_registration_date,
                   SchoolFiles schoolFiles,
                   String icard_template,
                   Principal principal) {
        super();
        this.school_id = school_id;
        this.school_name = school_name;
        this.school_registration_date = school_registration_date;
        this.school_email = school_email;
        this.school_address = school_address;
        this.school_city = school_city;
        this.school_state = school_state;
        this.school_pincode = school_pincode;
        this.school_status = school_status;
        this.phones = phones;
        this.school_adminEmail = school_adminEmail;
        this.schoolFiles = schoolFiles;
        this.icard_template = icard_template;
        this.principal =principal;


    }
    public Schools(){

    }
    private String school_id;
    private String school_name;
    private String school_email;
    private String school_address;
    private String school_city;
    private String school_state;
    private String school_pincode;
    private String school_adminEmail;
    private String school_registration_date;
    private String icard_template;



    private String school_status;
    private Phones phones;
    private Principal principal;
    private SchoolFiles schoolFiles;


    @Id
    private String id;
    @Override
    public String toString() {
        return "Schools{" +
                "school_id='" + school_id + '\'' +
                ", school_name='" + school_name + '\'' +
                ", school_email='" + school_email + '\'' +
                ", school_address='" + school_address + '\'' +
                ", school_city='" + school_city + '\'' +
                ", school_state='" + school_state + '\'' +
                ", school_pincode='" + school_pincode + '\'' +
                ", school_adminEmail='" + school_adminEmail + '\'' +
                ", school_registration_date='" + school_registration_date + '\'' +
                ", icard_template='" + icard_template + '\'' +
                ", school_status='" + school_status + '\'' +
                ", phones=" + phones +
                ", principal=" + principal +
                ", schoolFiles=" + schoolFiles +
                ", id='" + id + '\'' +
                '}';
    }

}
