package com.LibraryCom.OnlineLibrary.FunctionalClasses;


import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Repositories.BookRepo;
import com.LibraryCom.OnlineLibrary.Services.MailSenderService.MailSenderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.*;

@AllArgsConstructor
@Component
@Slf4j
public class CheckReturnBook{

    private final BookRepo bookRepo;
    private final MailSenderService mailSenderService;

    @Scheduled(fixedRate = 86_400_000)//86_400_000  - one day in ms:)
    public void CheckBookTimeLeft(){

        log.info("--Checking Started --");

        List<Book> listOfBooks = bookRepo.findAll();

        //Iterate all books
        for(Book book : listOfBooks){
            if(book.getDaysLeft() == null
                    || book.getDaysLeft() < 0) continue;

            book.setDaysLeft(book.getDaysLeft() - 1);
            //if book has already taked for 30 days by some user
            if(book.getDaysLeft() == 0) {
                //Set it time min
                book.setDaysLeft(-1L);
            } else if (book.getDaysLeft() == 15 || book.getDaysLeft() == 5) {
                Runnable sendingThread = () -> mailSenderService.sendWarnMailMessage(book);
                Thread t = new Thread(sendingThread,"SendingMail");
                t.start();
                log.info("--Thread Launched--");
            }
            bookRepo.save(book);
        }
        log.info("--Checking Finish --");
    }
}
