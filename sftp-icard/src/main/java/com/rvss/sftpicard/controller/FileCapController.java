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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.File;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;

import java.io.IOException;

//@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping(path = "cap/sftp/api/v1",
      //  consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
     //   produces = MediaType.APPLICATION_JSON_VALUE,
        method = {RequestMethod.GET, RequestMethod.POST,RequestMethod.PUT})
@RestController
public class FileCapController {

	private final String IMG_DIR = "C:"+File.separator+"download";
    /*@PutMapping("/pushFiles")
    public ResponseEntity<String> pushFiles(@RequestParam("localpath") String localpath,
    			@RequestParam("fileName") String fileName) throws SftpException {

        JavaSSHService javaSSHService = new JavaSSHService();
        SFTPConfig sftpConfig = new SFTPConfig();
        sftpConfig.setFileName(fileName);
        sftpConfig.setLocalpath(localpath);
        return ResponseEntity.status(HttpStatus.OK).body(
                javaSSHService.fileOperation(sftpConfig,"Upload")
        ) ;
    }*/
    
    @PutMapping(value = "/pushFiles", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> pushFiles(@RequestParam("file") MultipartFile file) throws SftpException {

        JavaSSHService javaSSHService = new JavaSSHService();
        SFTPConfig sftpConfig = new SFTPConfig();
        sftpConfig.setFileName(file.getOriginalFilename());
        sftpConfig.setFile(file);
        //sftpConfig.setLocalpath(localpath);
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
    public ResponseEntity<String> getFiles(@RequestParam("fileName") String fileName
                                         ) throws SftpException {

        JavaSSHService javaSSHService = new JavaSSHService();
        /*SFTPConfig sftpConfig = new SFTPConfig();
        sftpConfig.setFileName(fileName);
        sftpConfig.setLocalpath(localpath);*/
        return ResponseEntity.status(HttpStatus.OK).body(
                javaSSHService.downloadFromSftp(fileName)
        ) ;
    }

    @GetMapping("/healthCheck")
    public ResponseEntity healthCheck(){
        return ResponseEntity.ok("Service is Up");
    }
    
    
	
    @GetMapping("displayId")
    public ResponseEntity<Resource> getImage(@RequestParam("fileName") String fileName) throws SftpException {
        try {
        	
        	//get file from SFTP
        	JavaSSHService javaSSHService = new JavaSSHService();
        	String fileDwnld = javaSSHService.downloadFromSftp(fileName);
        	
            Path filePath = Paths.get(IMG_DIR).resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());

            if (!resource.exists()) {
                return ResponseEntity.notFound().build();
            }

            return ResponseEntity.ok()
                    .contentType(MediaType.IMAGE_JPEG) // or IMAGE_PNG depending on file
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
                    .body(resource);

        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
