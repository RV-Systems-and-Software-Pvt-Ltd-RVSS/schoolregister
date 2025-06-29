package com.rvss.sftpicard.beans;

import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
public class SFTPConfig {
    private String localpath ;
    private String fileName ;
    private MultipartFile file;

    public SFTPConfig() {

    }
}
