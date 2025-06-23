package com.rvss.sftpicard;

import com.rvss.sftpicard.controller.FileCapController;
import com.rvss.sftpicard.sftp.UpAndDownload;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

public class UpAndDownloadTest {

    @Test
    public void upload(){
        ResponseEntity Re =  new FileCapController().healthCheck();
        System.out.println(Re.toString());
    }

    @Test
    public void testSFTPConnection(){

    }
}
