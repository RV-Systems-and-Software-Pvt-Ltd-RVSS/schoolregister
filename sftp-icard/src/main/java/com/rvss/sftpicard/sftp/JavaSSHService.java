package com.rvss.sftpicard.sftp;

import com.jcraft.jsch.*;
import com.rvss.sftpicard.beans.SFTPConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


public class JavaSSHService {
	private final Path dirPath = Paths.get("C:", "upload");
	private final Path dirPathDwnld = Paths.get("C:", "download");
    private final String sftpPath = "/uploads/RVSS_SFTP_FOLDER";
    private final String sftpHost = "34.133.118.140"; //"Sudips-MacBook-Pro.local";
    private final String sftpPort = "22";
    private final String sftpUser = "sftpuser";
    private final String sftpPassword = "123"; // "tech@rahul2022";
    JSch jsch = new JSch();
    private Channel channel;
    private ChannelSftp channelSftp;
    private Session session;

    public JavaSSHService(){
        try{
            session = jsch.getSession(sftpUser, sftpHost , Integer.valueOf(sftpPort));
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(sftpPassword);
            System.out.println("Connecting -------");
            session.connect();
            System.out.println("Established Connection");
            channel = session.openChannel("sftp");
            channelSftp = (ChannelSftp) channel;
            channelSftp.connect();
            System.out.println("Channel Sftp Opened");
        }catch(Exception e){
            e.printStackTrace();
        }

    }
    public String fileOperation(@NotNull SFTPConfig sftpConfig, String operation) throws SftpException {
        //String localpath = sftpConfig.getLocalpath();
    	
        String fileName = sftpConfig.getFileName();
        this.createTempDir(fileName, sftpConfig.getFile());
        
        if (operation=="Upload") {
        	channelSftp.put(this.dirPath.toString()+"/"+fileName,sftpPath);
        	try {
        	boolean deleted = Files.deleteIfExists(this.dirPath.resolve(fileName));

            if (deleted) {
                System.out.println("File deleted from temp dir " + this.dirPath.toString());
            } else {
                System.out.println("File not found: " + this.dirPath.toString());
            }
        	} catch (IOException e) {
        		System.out.println("IOException = "+e.getMessage());
        	}
        } else
            channelSftp.get(sftpPath+"/"+fileName,this.dirPath.toString()+"/"+fileName);
        channelSftp.disconnect();
        session.disconnect();
        System.out.println("Disconnect from Sftp");
        return "File Uploaded to SFTP server "+sftpPath;
    }

    public String fileUploadService(MultipartFile fileObject, String fileName) throws SftpException, IOException {
        InputStream initialStream = fileObject.getInputStream();
        //System.out.println("Data in Stream"+initialStream.readAllBytes().toString()+"   "+fileObject.getOriginalFilename());
        String remoteFilePath = sftpPath + "/" + fileName;
        channelSftp.cd(sftpPath);
        channelSftp.setInputStream(initialStream);
        channelSftp.put(initialStream,remoteFilePath,ChannelSftp.OVERWRITE);
        channelSftp.disconnect();
        session.disconnect();
        System.out.println("Disconnect from Sftp");
        return remoteFilePath;
    }
    
    public String downloadFromSftp(String fileName) throws SftpException {
    	System.out.println("sftpPath -> " + sftpPath + " fileName -> " + fileName+" downloadFilePath -> "+dirPathDwnld.toString());
        // Perform the download from SFTP and save in server path
        channelSftp.get(this.sftpPath + "/" + fileName, dirPathDwnld.toString());

        channelSftp.disconnect();
        session.disconnect();

        System.out.println("Downloaded " + fileName + " to " + dirPathDwnld.toString());
        return "Downloaded " + fileName + " to " + dirPathDwnld.toString();
    }
    
    private void createTempDir (String fileName, MultipartFile fileObject) {
    	try {
            // 1. Create directory if it doesn't exist
            Files.createDirectories(dirPath);

            // 2. Define full path to the file
            Path targetFile = dirPath.resolve(fileName);

            // 3. Create the file (if it doesn't already exist)
            if (!Files.exists(targetFile)) {
                Files.createFile(targetFile);
             // Write file content (binary) directly
                
                System.out.println("Created file: " + targetFile);
            } else {
                System.out.println("File already exists: " + targetFile);
            }
            Files.write(targetFile, fileObject.getBytes());
            

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
