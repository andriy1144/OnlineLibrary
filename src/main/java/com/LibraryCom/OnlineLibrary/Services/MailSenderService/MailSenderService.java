package com.LibraryCom.OnlineLibrary.Services.MailSenderService;


import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.User;
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
        message.setSubject("Активація акаунту");
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

    @Async
    public void sendWarnMailMessage(Book book){
        User user = book.getUserTaker();

        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject("Попередження щодо терміну");
        message.setFrom("andrijmurgan44@gmail.com");
        message.setTo(user.getEmail());


        //Generate text to send
        String text = "Шановний %s ви орендовували у нас книгу %s. \n " +
                "Надіємось вона вам подобається \n " +
                "Не забудьте її вернути через %s діб";

        message.setText(text.formatted(user.getName(),book.getName(),book.getDaysLeft()));

        try {
            javaMailSender.send(message);
            log.info("--Message sent succesfully, to user with email : {}--",user.getEmail());
        }catch (Exception e){
            log.error("-- Something went wrong while sending message --");
            e.printStackTrace();
        }
    }
}
