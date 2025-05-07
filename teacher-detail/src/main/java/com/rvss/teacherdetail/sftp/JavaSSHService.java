package com.rvss.teacherdetail.sftp;

import com.jcraft.jsch.*;
import com.rvss.teacherdetail.beans.SFTPConfig;
import org.jetbrains.annotations.NotNull;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Files;
import java.util.List;

@Service
public class JavaSSHService {
    private final String sftpPath = "/Users/sudip_das";
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
    public Boolean fileUploadService( String fileName) throws SftpException, IOException {
        //InputStream initialStream = fileObject.getInputStream();
        //System.out.println("Data in Stream"+initialStream.readAllBytes().toString()+"   "+fileObject.getOriginalFilename());
        String remoteFilePath = sftpPath + "/" + fileName;
        channelSftp.cd(sftpPath);
        boolean fileExists = false;
        try {
            SftpATTRS attr = channelSftp.lstat(remoteFilePath);
            fileExists = true;
            //InputStream inputStream = channelSftp.ch(remoteFilePath);
            int permissions = attr.getPermissions();

            System.out.println("Permissions: " + Integer.toOctalString(permissions & 0777));
            System.out.println("User permissions: " + ((permissions & 0700) >> 6));
            System.out.println("Group permissions: " + ((permissions & 0070) >> 3));
            System.out.println("Other permissions: " + (permissions & 0007));

            // Print other file attributes
            System.out.println("Size: " + attr.getSize());
            System.out.println("Owner: " + attr.getUId());
            System.out.println("Group: " + attr.getGId());
            System.out.println("Last modified time: " + attr.getMTime());


            // Iterate through the list and print file attributes
            /*List<ChannelSftp.LsEntry> list = channelSftp.ls(remoteFilePath);
            for (ChannelSftp.LsEntry entry : list) {
                String directory_fileName = entry.getFilename();
                SftpATTRS attrs = entry.getAttrs();
                int permissions = attrs.getPermissions();

                System.out.println("File: " + fileName);
                System.out.println("Permissions: " + Integer.toOctalString(permissions & 0777));
                System.out.println("User permissions: " + ((permissions & 0700) >> 6));
                System.out.println("Group permissions: " + ((permissions & 0070) >> 3));
                System.out.println("Other permissions: " + (permissions & 0007));
                System.out.println("Size: " + attrs.getSize());
                System.out.println("Owner: " + attrs.getUId());
                System.out.println("Group: " + attrs.getGId());
                System.out.println("Last modified time: " + attrs.getMTime());
                System.out.println("-------------------------------------------------");
            }*/
        } catch (Exception e) {
            // If the file does not exist, an exception will be thrown
            e.printStackTrace();
            fileExists = false;
        }

        //channelSftp.setInputStream(initialStream);
        //channelSftp.put(initialStream,remoteFilePath,ChannelSftp.OVERWRITE);
        channelSftp.disconnect();
        session.disconnect();
        System.out.println("Disconnect from Sftp");
        return fileExists;
    }

    public Resource getSourceResource(String fileName) throws SftpException, IOException, JSchException {
        String remoteFilePath = sftpPath + "/" + fileName;
        System.out.println("Path that has been Accessed --"+remoteFilePath);

        channelSftp.cd(sftpPath);
        System.out.println("Changed Path that has been Accessed --"+channelSftp.getSession().getUserName());
        try{
            if (fileExists(channelSftp, remoteFilePath)) {
                InputStream inputStream = channelSftp.get(remoteFilePath);
                File tempFile = Files.createTempFile("sftp-", ".tmp").toFile();
                try (FileOutputStream fos = new FileOutputStream(tempFile)) {
                    byte[] buffer = new byte[1024];
                    int bytesRead;
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        fos.write(buffer, 0, bytesRead);
                    }
                } finally {
                    inputStream.close(); // Ensure SFTP input stream is closed after writing to temp file
                }

                // Log that the file has been created and return the FileSystemResource
                System.out.println("Temporary file created at: " + tempFile.getAbsolutePath());
                return new FileSystemResource(tempFile);

            } else {
                throw new FileNotFoundException("File not found at path: " + remoteFilePath);
            }
        }
        catch (SftpException sftpException)
        {
            sftpException.printStackTrace();
            throw sftpException;
        }
        catch (Exception e) {
            System.out.println("Error checking InputStream state: " + e.getMessage());
            e.printStackTrace();

        throw e;
    } finally {
        if (channelSftp != null) {
            channelSftp.disconnect();
        }
        if (session != null) {
            session.disconnect();
        }
    }
    }
    private boolean fileExists(ChannelSftp channelSftp, String path) {
        try {
            SftpATTRS attrs = channelSftp.stat(path);
            return attrs != null;
        } catch (SftpException e) {
            return false;  // File does not exist
        }
    }
    public String readContent(InputStream inputStream) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line).append(System.lineSeparator());
            }
        }
        return content.toString();
    }
}
