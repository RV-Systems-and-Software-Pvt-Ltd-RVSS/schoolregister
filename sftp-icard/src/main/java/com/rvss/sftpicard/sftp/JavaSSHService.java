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

public class JavaSSHService {
    private final String sftpPath = "/Users/sudip_das/Documents/RVSS_SFTP_FOLDER";
    private final String sftpHost = "Sudips-MacBook-Pro.local";
    private final String sftpPort = "22";
    private final String sftpUser = "sudip_das";
    private final String sftpPassword = "tech@rahul2022";
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
        String localpath = sftpConfig.getLocalpath();
        String fileName = sftpConfig.getFileName();
            if (operation=="Upload") channelSftp.put(localpath+"/"+fileName,sftpPath);
            else
                channelSftp.get(sftpPath+"/"+fileName,localpath);
        channelSftp.disconnect();
        session.disconnect();
        System.out.println("Disconnect from Sftp");
        return null;
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
}
