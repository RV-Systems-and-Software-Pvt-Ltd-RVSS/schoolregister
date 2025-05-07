package com.rvss.teacherdetail.beans;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class SFTPConfig {
    private String localpath ;
    private String fileName ;

    public SFTPConfig() {

    }
}
