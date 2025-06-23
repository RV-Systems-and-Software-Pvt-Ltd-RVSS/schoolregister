package com.rvss.sftpicard.sftp;

import org.springframework.integration.sftp.session.DefaultSftpSessionFactory;
import org.springframework.integration.sftp.session.SftpSession;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;

public class UpAndDownload {
    private DefaultSftpSessionFactory getDefaultSession(){
        DefaultSftpSessionFactory factory = new DefaultSftpSessionFactory();
        factory.setHost("Sudips-MacBook-Pro");
        factory.setPort(22);
        factory.setAllowUnknownKeys(true);
        factory.setUser("SFTP_USER");
        factory.setPassword("tech@Rahul2023");
        return factory;
    }
    public void upload(){
        SftpSession session = getDefaultSession().getSession();
        InputStream inputStream =
                UpAndDownload.class.getClassLoader().getResourceAsStream("application.properties");
        try{
            session.write(inputStream,"/Users/sudip_das/Documents/RVSS_SFTP_FOLDER"
                    + LocalDateTime.now()+".txt");
        }catch (IOException ioex){
            ioex.printStackTrace();
        }
        session.close();
    }
}
