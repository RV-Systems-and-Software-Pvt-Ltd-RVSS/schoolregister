package com.rvss.schoolregister.mailservices;


import com.rvss.schoolregister.beans.EmailDetails;


import java.util.concurrent.CompletableFuture;

public interface EmailService {
    void sendSimpleMail(EmailDetails details);
    CompletableFuture<String> sendSimpleMailWithAttachments(EmailDetails details);
}
