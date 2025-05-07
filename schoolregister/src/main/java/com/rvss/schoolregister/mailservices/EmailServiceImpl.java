package com.rvss.schoolregister.mailservices;

import com.rvss.schoolregister.beans.EmailDetails;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailAuthenticationException;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.util.concurrent.CompletableFuture;

@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;
    @Value("${spring.mail.username}")
    private String sender;
    @Async
    public void sendSimpleMail(EmailDetails details) {

        // Try block to check for exceptions

        try {
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            try{
                 javaMailSender.send(mailMessage);
                 System.out.println(" MailSend Success");
            }catch (MailAuthenticationException mailAuthenticationException){
                //completableFuture.complete(mailAuthenticationException.getStackTrace().toString());
                System.out.println(" MailSend Not Success");
            }catch (MailException mailException){
                //completableFuture.complete(mailException.getStackTrace().toString());
                System.out.println(" MailSend Not Success");
            }

        }
        catch (Exception e) {
            //completableFuture.complete("there is a Problem");
            System.out.println(" MailSend Not Success");
        }


    }

    @Override
    public CompletableFuture<String> sendSimpleMailWithAttachments(EmailDetails details) {
        {
            // Creating a mime message
            CompletableFuture<String> completableFuture = null;
            MimeMessageHelper mimeMessageHelper;
            MimeMessage mimeMessage
                    = javaMailSender.createMimeMessage();

            try {

                // Setting multipart as true for attachments to
                // be send
                // mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
                mimeMessageHelper.setFrom(sender);
                mimeMessageHelper.setTo(details.getRecipient());
                mimeMessageHelper.setText(details.getMsgBody());
                mimeMessageHelper.setSubject(
                        details.getSubject());

                // Adding the attachment
                FileSystemResource file
                        = new FileSystemResource(
                        new File(details.getAttachment()));

                mimeMessageHelper.addAttachment(
                        file.getFilename(), file);

                // Sending the mail
                javaMailSender.send(mimeMessage);

            }

            // Catch block to handle MessagingException
            catch (jakarta.mail.MessagingException e) {

                // Display message when exception occurred
                completableFuture.complete("there is a Problem");
            }
            completableFuture.complete("Successfully Send");
            return completableFuture;
        }
    }
}
