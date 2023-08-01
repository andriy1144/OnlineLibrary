package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.BookResponce;
import com.LibraryCom.OnlineLibrary.Models.ResponcesEntities.LibraryResponse;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.BookRepo;
import com.LibraryCom.OnlineLibrary.Repositories.ResponcesRepo.BookResponceRepo;
import com.LibraryCom.OnlineLibrary.Repositories.ResponcesRepo.LibraryResponseRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResponseService {

    //Required variables
    private final LibraryResponseRepo libraryResponseRepo;
    private final BookResponceRepo bookResponceRepo;
    private final BookRepo bookRepo;

    //saveResponses
    public boolean saveResponse(LibraryResponse libraryResponse, User user) {
        /* Every user can submit only one response about Library and Books*/
        //So we check did user already have response
        LibraryResponse userResponse = libraryResponseRepo.findResponseByUser(user);

        if(userResponse != null){
            //If user has already left his response to library
            return false;
        }

        //Else generate responce
        libraryResponse.setUser(user);
        libraryResponseRepo.save(libraryResponse);

        log.info("--New response by user with id : {} and username :{}",user.getId(),user.getUsername());
        return true;
    }
//    public boolean saveResponse(BookResponce bookResponce, User user, Book book) {
//        /* Every user can submit only one responce about Library and Books*/
//        //So we check did user already have response
//
//        //Check if user has been already send Responce to the book
//        BookResponce userbookResponce = bookResponceRepo.findBookResponceByUserAndBook(user,book);
//
//        System.out.println(userbookResponce);
//        if(userbookResponce != null){
//            return false;
//        }
//
//        bookResponce.setUser(user);
//        bookResponce.setBook(book);
//
//        bookResponceRepo.save(bookResponce);
//
//        log.info("New book responce to book name : {}, by user email :{}",book.getName(),user.getEmail());
//        return true;
//    }

    public List<LibraryResponse> getAllResponces(){
        return libraryResponseRepo.findAll();
    }
    public Double calculateGeneralRate(){
        List<LibraryResponse> allResponces = libraryResponseRepo.findAll();
        double sum  = allResponces.stream().mapToDouble(LibraryResponse::getUserRate).sum();
        return sum/allResponces.size();
    }
}
