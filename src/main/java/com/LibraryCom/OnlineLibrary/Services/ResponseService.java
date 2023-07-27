package com.LibraryCom.OnlineLibrary.Services;

import com.LibraryCom.OnlineLibrary.Models.Book;
import com.LibraryCom.OnlineLibrary.Models.Response;
import com.LibraryCom.OnlineLibrary.Models.User;
import com.LibraryCom.OnlineLibrary.Repositories.BookRepo;
import com.LibraryCom.OnlineLibrary.Repositories.ResponseRepo;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class ResponseService {

    //Required varasables
    private final ResponseRepo responseRepo;
    private final BookRepo bookRepo;

    //saveResponses
    public boolean saveResponse(Response response, User user) {
        /* Every user can submit only one responce about Library and Books*/
        //So we check did user already have response
        Response userResponse = responseRepo.findResponseByBookResponceFalseAndUser(user);

        if(userResponse != null){
            return false;
        }
        response.setUser(user);

        response.setBookResponce(false);
        responseRepo.save(response);

        log.info("--New response by user with id : {} and username :{}",user.getId(),user.getUsername());
        return true;
    }
    public boolean saveResponse(Response response, User user, Book book) {
        /* Every user can submit only one responce about Library and Books*/
        //So we check did user already have response
        Response userResponse = responseRepo.findResponseByBookResponceTrueAndUser(user);

        if(userResponse != null){
            return false;
        }
        response.setUser(user);

        response.setBookResponce(true);
        responseRepo.save(response);

        book.getResponseSet().add(response);
        bookRepo.save(book);

        log.info("--New response by user with id : {} and username :{} for book with name: {}",user.getId(),user.getUsername(),book.getName());
        return true;
    }
    public List<Response> getAllResponces(){
        return responseRepo.findResponsesByBookResponceFalse();
    }
    public Double calculateGeneralRate(){
        List<Response> allResponces = responseRepo.findResponsesByBookResponceFalse();
        double sum  = allResponces.stream().mapToDouble(Response::getUserRate).sum();
        return sum/allResponces.size();
    }
}
