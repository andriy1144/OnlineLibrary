package com.LibraryCom.OnlineLibrary.Services.MailSenderService;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class MailSenderService {

    //Java mail sender
    @Autowired
    private JavaMailSender javaMailSender;

    @Async
    public void sendMailMessage(String to,String text){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Account activation");
        message.setFrom("andrijmurgan44@gmail.com");
        message.setTo(to);
        message.setText(text);

        try {
            javaMailSender.send(message);
            log.info("--Message sent succesfully, to user with email : {}--",to);
        }catch (Exception e){
            log.error("-- Something went wrong while sending message --");
            e.printStackTrace();
        }
    }
}
