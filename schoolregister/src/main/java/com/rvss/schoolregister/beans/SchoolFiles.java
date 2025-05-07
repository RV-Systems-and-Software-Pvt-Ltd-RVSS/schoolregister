package com.rvss.schoolregister.beans;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class SchoolFiles {

    private String image1_id;
    private String image2_id;
    private String image1_location;
    private String image2_location;

}
