package com.rvss.sftpicard.controller;


import com.jcraft.jsch.SftpException;
import com.rvss.sftpicard.beans.SFTPConfig;
import com.rvss.sftpicard.sftp.JavaSSHService;
import com.rvss.sftpicard.beans.SFTPConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "cap/sftp/api/v1",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RestController
public class FileCapController {

    @PutMapping("/pushFiles")
    public ResponseEntity<String> pushFiles(@RequestParam("localpath") String localpath,
                                            @RequestParam("fileName") String fileName) throws SftpException {

        JavaSSHService javaSSHService = new JavaSSHService();
        SFTPConfig sftpConfig = new SFTPConfig();
        sftpConfig.setFileName(fileName);
        sftpConfig.setLocalpath(localpath);
        return ResponseEntity.status(HttpStatus.OK).body(
                javaSSHService.fileOperation(sftpConfig,"Upload")
        ) ;
    }
    @PutMapping("/pushOrginalFile")
    public ResponseEntity<String> pushFiles(@RequestParam("file")MultipartFile multipartFile,
                                            @RequestParam("fileName") String fileName,
                                            @RequestParam("type") String type) throws SftpException, IOException {
        if(type.contains("jpeg")|| type.contains("jpg")) {
            fileName = fileName+".jpg";
        }
        else if(type.contains("png")) {
               fileName = fileName+".png";
           } else if (type.contains("css")) {
            fileName = fileName+".css";
        }else if(type.contains("xls")|| type.contains("xlsx")){
            fileName = fileName+".xlsx";
        }
        else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("File is not in the acceptable format");
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new JavaSSHService().fileUploadService(multipartFile,fileName)
        );
    }
    @GetMapping("/getFiles")
    public ResponseEntity<String> getFiles(@RequestParam("localpath") String localpath,
                                            @RequestParam("fileName") String fileName
                                         ) throws SftpException {

        JavaSSHService javaSSHService = new JavaSSHService();
        SFTPConfig sftpConfig = new SFTPConfig();
        sftpConfig.setFileName(fileName);
        sftpConfig.setLocalpath(localpath);
        return ResponseEntity.status(HttpStatus.OK).body(
                javaSSHService.fileOperation(sftpConfig,"Download")
        ) ;
    }

    @GetMapping("/healthCheck")
    public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Service is Up");
    }
}
